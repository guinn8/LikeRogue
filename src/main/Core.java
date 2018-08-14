package main;

import javafx.scene.layout.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import actors.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.input.KeyCode;

/**
 * This is the 'heart' of the game. It creates the window and has all the core features.
 * It calls all the constructors from the other classes and essentially runs the game.
 *
 * @author Eric Zhang
 * @author Gavin Guinn
 */
interface coreInter{
	
}
public class Core extends Application implements Menus, GameGUI {
	/**
	 * Main method. Launches the game 
	 */
	public static void main(String[] args) {
		launch(args);
	}
	public static final int WIDTH=590;
	public static final int HEIGHT=670;
	private static Pane gameScreen = new Pane(GameGUI.Inventory());
	private static boolean isRunning=true;
	private static Group solid= new Group();
	private static File save= new File("res/save.txt");
	
	private static Map[] mapArray = new Map[4];
	private static Player player1 = new Player(10,1);
	private static Scene root;

	/**
         * This starts the window and initializes the game. This has all the core game mechanics and takes in
         * player movement, attack and sets the game loop.
         * @param stage the area that the map is created on.
         * @throws InterruptedException
         * @throws FileNotFoundException
         */
	public void start(Stage stage) throws InterruptedException, FileNotFoundException {
		stage.setResizable(false);
		mapArray[0]= new Map(new File("res/layouts/map0.txt"));
		mapArray[1]= new Map(new File("res/layouts/map1.txt"));
		mapArray[2]= new Map(new File("res/layouts/map2.txt"));
		mapArray[3]= new Map(new File("res/layouts/map3.txt"));
		
		gameScreen.setBackground(
				new Background(
					new BackgroundImage(
						new Image("file:res/sprites/map/floor.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
							new BackgroundSize(10000, 100000, true, true, true, true))));
		
		root = new Scene(Menus.start(), WIDTH, HEIGHT);
		stage.setScene(root);
		stage.show();
		
		
		/**
		 * 
		 */
		stage.setOnCloseRequest((WindowEvent e1)->{
			try {
				PrintWriter writer = new PrintWriter(save);
				writer.println(Map.getMapNum());
				writer.close();
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
		});
		
		
		/**
		 * 
		 */
		root.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.D) {
				player1.setDelta(Actors.MOVERES,0);
			}

			else if (e.getCode() == KeyCode.A) {
				player1.setDelta(-Actors.MOVERES,0);	
			}

			else if (e.getCode() == KeyCode.S) {
				player1.setDelta(0, Actors.MOVERES);;
			}

			else if (e.getCode() == KeyCode.W) {
				player1.setDelta(0, -Actors.MOVERES);
			
			}
			
			else if (e.getCode() == KeyCode.H) {
				if (GameGUI.getHealthVis() == true) {
					player1.setHealth(10);
					GameGUI.setHealthVis(false);
				}
			}	
			
			else if (e.getCode() == KeyCode.SPACE) {
				player1.setAttacking(true);
			}
		});
		
		
		/**
		 * 
		 */
		AnimationTimer gameLoop = new AnimationTimer() {
			int timer;
			@Override
			public void handle(long arg0) {
				if (isRunning==true) {
					timer++;
					player1.resetDamage();
					player1.move();
					
					if (timer%5==0) {
						mapArray[Map.getMapNum()].moveEnemys();
						GameGUI.drawHealthBar(player1.getHealth()) ;
						if(player1.getHealth()<=0) {
							Pane endlayout=Menus.end(player1.getDamage(),"You Lose");
							isRunning=false;
							root.setRoot(endlayout);
						}
					}
				
					if (timer%15==0) {
						if(player1.isAttacking()==true) {
							player1.attack();
							mapArray[Map.getMapNum()].checkEnemys();
							player1.setAttacking(false);
						}
					}

					if (timer==1000)timer=0;
				}
			}
		}; gameLoop.start();
	}
		
	
	/**
	 * check handles the collision detection
	 * 
	 * @param xDelt distance to check ahead in the x direction
	 * @param yDelt distance to precheck in the y direction
	 * @return false if solid object in the way
	 * @throws FileNotFoundException 
	 **/
	public static boolean checkCollision(Actors actor) {
		for (Node object : solid.getChildren()) {
			if (object.getBoundsInParent().intersects(actor.getBounds().getMinX() + actor.getDeltaX(), actor.getBounds().getMinY() + actor.getDeltaY(), 
				actor.getBounds().getWidth(), actor.getBounds().getHeight()) && object.getId() != null) {
							
				if (object.getId().equals("wall")) return false;
				
				if  (actor instanceof Player) {
					if (object.getId().equals("enemy")){
						return false;
					}
				}
				
				if(actor instanceof Enemy) {
					if (object.getId().equals("player")){
						hit( player1,actor);
						return false;
					}
				}
				
				if (object.getId().equals("chest")) {
					solid.getChildren().remove(object);
					chestRoll();
						return false;
				}
			
					
				if (object.getId().equals("finish")) {
					if  (actor instanceof Player)nextMap();
					return false;
					
				}
				
				if (object.getId().equals("damage")) {
					
					actor.setHealth(actor.getHealth()-player1.getDamage());
						
					actor.checkAlive();
					return false;
				}
					
				if(actor instanceof Enemy) {
					if (object.getId().equals("enemy")){
						if(mapArray[Map.getMapNum()].eCheck((ImageView)object, (Enemy) actor)==true) {
							return false;
						
						}
					}
				}	
			}
		}
	return true;
	}

	
	/**
	 * Takes away health of actor1 if they collide with actor2 and then checks if they are alive.
	 * @param actor1 can be the player or enemy.
	 * @param actor2 can be the enemy or damage.
	 */
	private static void hit(Actors actor1, Actors actor2) {
		actor1.setHitCount(actor1.getHitCount()+1);
		if (actor1.getHitCount()==15) {
			actor1.setHealth(actor1.getHealth()-actor2.getDamage());
			
			actor1.setHitCount(0);
		}
		actor1.checkAlive();
		actor2.checkAlive();
	}
	
	/**
	 * This advances the player to the next map when called.
	 */
	private static void nextMap() {
		Map.setMapNum(Map.getMapNum()+1);
		if(Map.getMapNum()<mapArray.length) {	
			mapArray[Map.getMapNum()].createMap();
			player1.teleport(mapArray[Map.getMapNum()].getPX(), mapArray[Map.getMapNum()].getPY());			
		}
	
		else {
			Map.setMapNum(3);
			Pane endlayout=Menus.end(player1.getDamage(),"You Win");
			isRunning=false;
			root.setRoot(endlayout);
		}
		
	mapArray[Map.getMapNum()-1].removeMap();  
		
	}

	
	/**
	 * Adds walls and enemies and other objects that you can collide with.
	 * @param n
	 */
	public static void addSolid(Node n) {
		solid.getChildren().add(n);
	}
	
	/**
	 * This removes objects that you can collide with (enemies).
	 * @param n represents node being removed.
	 */
	public static void removeSolid(Node n) {
		solid.getChildren().remove(n);
	}

	/**
	 * Sets the map. Used when the player transitions from the start screen to the gameplay.
	 */
	public static void setToMain() {
		setMap(0);
		root.setRoot(gameScreen);
	}


	/**
	 * Getter for save.
	 * @return the save
	 */
	public static File getSave() {
		return save;
	}
	
	
	/**
	 * This is the random number generator which determiens what a player gets out of a chest.
	 */
	private static void chestRoll() {
		int roll = (int) (Math.ceil(Math.random() * 2));
		if (roll== 1 && player1.getDamage()!=10) {
			if(player1.getDamage()==1){
				GameGUI.setSwordVis(true);
				player1.setDamage(3);
			}
			else if(player1.getDamage()==3) {
				GameGUI.setSwordVis(false);
				GameGUI.setSword2Vis(true);
				player1.setDamage(4);
			}
			else if(player1.getDamage()==4) {
				GameGUI.setSword2Vis(false);
				GameGUI.setSword3Vis(true);
				player1.setDamage(5);
			}
			else if(player1.getDamage()==5) {
				GameGUI.setSword3Vis(false);
				GameGUI.setSword4Vis(true);
				player1.setDamage(10);
			}
		}
		
		else if(GameGUI.getHealthVis()!=true) {
			GameGUI.setHealthVis(true);
		}
		
	}
	
	
	
	/**
	 *
	 */
	public static void setMap(int num) {
		
		Map.setMapNum(num);
		mapArray[num].createMap();
		player1.teleport(mapArray[num].getPX(), mapArray[num].getPY());
		
		gameScreen.getChildren().add(solid);
	}
	
	public static double getPlayerX() {
		return player1.getX();
	}

	public static double getPlayerY() {
		return player1.getY();
		
	}
}
