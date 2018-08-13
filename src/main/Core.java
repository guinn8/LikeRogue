package main;

import javafx.scene.layout.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

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


public  class Core extends Application {
	/**
	 * Main method. Launches the game 
	 */
	public static void main(String[] args) {
		
		launch(args);
	}
	
	static ArrayList<Enemy>enemyArray= new ArrayList<Enemy>();
	private static StartMenu start= new StartMenu();
	private static  Feedbackscreen end;
	public static int mapNum;
	private static int hitCount=0;
	private static Pane layout = new Pane();
	
	private static boolean running=true;
	private static Group solid= new Group();

	private File map0=new File("res/layouts/map0.txt");
	private File map1=new File("res/layouts/map1.txt");
	private File map2=new File("res/layouts/map2.txt");
	private File map3=new File("res/layouts/map3.txt");
	
	private static File save= new File("res/save.txt");
	
	public static Map[] progress = new Map[4];
	
	private Image floorImage =new Image("file:res/sprites/map/floor.png");
	private BackgroundSize backSize = new BackgroundSize(10000, 100000, true, true, true, true);
	private BackgroundImage floor = new BackgroundImage(floorImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,backSize);
	private Background background= new Background(floor);
	
	private static Inventory inventory = new Inventory();
	private static Player player1 = new Player(10000,1);
	
	public static final int WIDTH=600;
	public static final int HEIGHT=680;
	
	private static boolean attack = false;
	private static Scene scene = new Scene(layout, WIDTH, HEIGHT);
	static Pane endlayout;
	Stage stage= new Stage();

	/**
         * This starts the window and initializes the game. This has all the core game mechanics and takes in
         * player movement, attack and sets the game loop.
         * @param stage the area that the map is created on.
         * @throws InterruptedException
         * @throws FileNotFoundException
         */
	public void start(Stage stage) throws InterruptedException, FileNotFoundException {
		
		stage.setResizable(false);
		progress[0]= new Map(map0);
		progress[1]= new Map(map1);
		progress[2]= new Map(map2);
		progress[3]= new Map(map3);
		    
		layout.setBackground(background);
		
		
		Pane startLayout=start.start();
		
	    scene.setRoot(startLayout);
    	
		stage.setScene(scene);
		
		
		
		

		stage.show();
		
		
		stage.setOnCloseRequest((WindowEvent e1)->{
		
			
			try {
				PrintWriter writer = new PrintWriter(getSave());
				writer.println(mapNum);
			
				writer.close();
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});

		scene.setOnKeyPressed(e -> {
			
			if (e.getCode() == KeyCode.D) {
				player1.setDelta(Actors.MOVERES,0);
				player1.setPlayerRight();
			}

			else if (e.getCode() == KeyCode.A) {
				player1.setDelta(-Actors.MOVERES,0);
				player1.setPlayerLeft();				
			}

			else if (e.getCode() == KeyCode.S) {
				player1.setDelta(0, Actors.MOVERES);
				player1.setPlayerDown();
			}

			else if (e.getCode() == KeyCode.W) {
				player1.setDelta(0, -Actors.MOVERES);
				player1.setPlayerUp();
			}
			
			else if (e.getCode() == KeyCode.H) {
				if (inventory.getHealthVis() == true) {
					player1.setHealth(10);
					inventory.setHealthVis(false);
				}
			}	
			
			else if (e.getCode() == KeyCode.SPACE) {
				attack=true;
			}
		});

		AnimationTimer gameLoop = new AnimationTimer() {
			
			int timer;
			@Override
			public void handle(long arg0) {
				if (running==true) {
					timer++;
	
					
					
	
			checkEnemys();
					
					
	
					
					player1.resetDamage();
					
					
					if (timer%5==0) {
						
						
						moveEnemys(player1.getX(),player1.getY());
						player1.drawHealthBar() ;
					
					}
					
					player1.setLastX(player1.getX());
					player1.setLastY(player1.getY());
					
					if(player1.getHealth()<=0) {
						end= new Feedbackscreen(player1.getDamage(),"You Lose");
						endlayout=end.end();
						scene.setRoot(endlayout);
						stage.setScene(scene);
				}
					
					int dir=player1.move();
					if (timer%15==0) {
						if(attack==true) {
							player1.attack(dir);
							attack=false;
						}
					}
					if (timer==1000)timer=0;
					}
			}
		}; gameLoop.start();}
		
	
	/**
	 * check handles the collision detection
	 * 
	 * @param xDelt distance to check ahead in the x direction
	 * @param yDelt distance to precheck in the y direction
	 * @return false if solid object in the way
	 * @throws FileNotFoundException 
	 **/
	private static boolean check(Actors actor) {
		
	
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
						
						if(eCheck((ImageView)object, (Enemy) actor)==true) {
						
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
		hitCount++;
		if (hitCount==30) {
			actor1.setHealth(actor1.getHealth()-actor2.getDamage());
			
			hitCount=0;
		}
		actor1.checkAlive();
		actor2.checkAlive();
	}
	
	/**
	 * This advances the player to the next map when called.
	 */
	private static void nextMap() {
		mapNum++;
		
		if(mapNum<progress.length) {	
			progress[mapNum].createMap();
			player1.teleport(progress[mapNum].getPX(), progress[mapNum].getPY());			
		}
	
		else {
			mapNum=3;
			end= new Feedbackscreen(player1.getDamage(),"You Win");
			endlayout=end.end();
			running=false;
			scene.setRoot(endlayout);
		}
	progress[mapNum-1].removeMap();  
		
	}
	
	/**
	 * This adds objects that you don't collide with (background, health bar)
	 * @param n is the node that will be added.
	 */
	public static void addLayout(Node n) {
		layout.getChildren().add(n);
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
	 *
	 */
	public static void setToMain() {
		scene.setRoot(layout);
	}


	/**
	 * Getter for save.
	 * @return the save
	 */
	public static File getSave() {
		return save;
	}
	/**
	 *This is the random number generator which determiens what a player gets out of a chest.
	 */
	private static void chestRoll() {
		int roll = (int) (Math.ceil(Math.random() * 2));
		
		
		
		if (roll== 1) {
			
			if(player1.getDamage()==1){
				
			inventory.setSwordVis(true);
			player1.setDamage(3);}
			else if(player1.getDamage()==3) {
				inventory.setSwordVis(false);
				inventory.setSword2Vis(true);
				player1.setDamage(4);
			}else if(player1.getDamage()==4) {
				inventory.setSword2Vis(false);
				inventory.setSword3Vis(true);
				player1.setDamage(5);
			}else if(player1.getDamage()==5) {
				inventory.setSword3Vis(false);
				inventory.setSword4Vis(true);
				player1.setDamage(10);
			}
		}
		else if (roll == 2) {
			inventory.setHealthVis(true);
		}
	}

	private static void createMap(int num) {
		mapNum=num;
		enemyArray=progress[num].createMap();
		player1.teleport(progress[num].getPX(), progress[num].getPY());
		player1.setLastX(progress[num].getPX());
		player1.setLastY(progress[num].getPY());
		layout.getChildren().add(solid);
	}
	
	public static void setMap(int n) {
		createMap(n);
		
	}
	public void moveEnemys(double pX, double pY) {
		for (Enemy e: enemyArray) {
			if (e.checkAlive()==true)e.move(pX,pY);
			
		}
	}
	public void checkEnemys() {
		for (Enemy e: enemyArray) Core.check(e);
			
	}
	
	
	
	public static boolean eCheck(ImageView i, Enemy en) {
		for (Enemy e: enemyArray) {
			if(e==en)break;
			if(i==e.getImageView()) {
				return true;
			}
		}
		return false;
		
		
	}
}
