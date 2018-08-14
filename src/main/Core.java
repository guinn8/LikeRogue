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

public class Core extends Application implements Menus, GameGUI {
	/**
	 * Main method. Launches the game 
	 */
	public static void main(String[] args) {
		launch(args);
	}
	//width and height of the window
	public static final int WIDTH=590;
	public static final int HEIGHT=670;
	
	private static Pane gameScreen = new Pane(GameGUI.Inventory());//this pane manages heads-up interface items like healthbar and inventory bar
	private static boolean isRunning=true;//this boolean stops the game loop if an end state is reached
	private static Group solid= new Group();//this is the group of all objects that are solid(walls, character, enemys)
	private static File save= new File("res/save.txt");//this is the file the current map number is saved to
	private static Map[] mapArray = new Map[4];//the game progresses map to map in order through the map array 
	private static Player player1 = new Player(10,1);
	private static Scene root;// the scene on which all gui elements are drawn on

	/**
     * This starts the window and initializes the game. This has all the core game mechanics and takes in
     * player movement, attack and sets the game loop.
     * @param stage the area that the map is created on.
     * @throws InterruptedException
     * @throws FileNotFoundException
     */
	public void start(Stage stage) throws InterruptedException, FileNotFoundException {
		stage.setResizable(false);
		
		//This block populates the array of maps with instances Map
		mapArray[0]= new Map(new File("res/layouts/map0.txt"));
		mapArray[1]= new Map(new File("res/layouts/map1.txt"));
		mapArray[2]= new Map(new File("res/layouts/map2.txt"));
		mapArray[3]= new Map(new File("res/layouts/map3.txt"));
		
		//this ridiculous statement sets the background 
		gameScreen.setBackground(
				new Background(
					new BackgroundImage(
						new Image("file:res/sprites/map/floor.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
							new BackgroundSize(10000, 100000, true, true, true, true))));
		
		//Initializes the root to the start menu
		root = new Scene(Menus.start(), WIDTH, HEIGHT);
		stage.setScene(root);
		stage.show();
		
		
		
		//This event listener saves the current map to a text file on close
		stage.setOnCloseRequest((WindowEvent e1)->{
			try {
				PrintWriter writer = new PrintWriter(save);
				writer.println(Map.getMapNum());
				writer.close();
			} catch (FileNotFoundException e2) {
				System.out.println("file save failed");
			}
		});
		
		
		//this event listener handles keyboard input and prompts the correct response
		//setDelta() sets the amounts the player should move on the next loop of the game cycle
		root.setOnKeyPressed(e -> {
			//right
			if (e.getCode() == KeyCode.D) {
				player1.setDelta(Actors.MOVERES,0);
			}
			
			//left
			else if (e.getCode() == KeyCode.A) {
				player1.setDelta(-Actors.MOVERES,0);	
			}
			
			//down
			else if (e.getCode() == KeyCode.S) {
				player1.setDelta(0, Actors.MOVERES);;
			}
			
			//up
			else if (e.getCode() == KeyCode.W) {
				player1.setDelta(0, -Actors.MOVERES);
			}
			
			//use the healthpack
			else if (e.getCode() == KeyCode.H) {
				if (GameGUI.getHealthVis() == true) {
					player1.setHealth(10);
					GameGUI.setHealthVis(false);
				}
			}
			//attack
			else if (e.getCode() == KeyCode.SPACE) {
				player1.setAttacking(true);
			}
		});
		
		
		//this animation timer makes up main loop which makes sure events are responded to promptly
		AnimationTimer gameLoop = new AnimationTimer() {
			
			//this timer allows for events to be timed to happen only after a certain inverval
			int timer;
			@Override
			public void handle(long arg0) {
				if (isRunning==true) {
					timer++;
		
					player1.resetHitMarker();//sets the players hit marker off the map every frame
					player1.move();//moves the player according the the values set by keyboard input
					
					if (timer%5==0) {					
						mapArray[Map.getMapNum()].moveEnemys();//loops through the current maps enemy array and moves all the enemies											
						GameGUI.drawHealthBar(player1.getHealth());//refreshes healthbar to players current health
						
						//this block handles the player dying
						if(player1.getHealth()<=0) {
							isRunning=false;
							root.setRoot(Menus.end(player1.getDamage(),"You Lose"));// sets the page to the end screen
						}
					}
					
					//this block handles the player attacking 
					if (timer%15==0) {
						if(player1.isAttacking()==true) {
							player1.attack();//sets the hit marker to adjancent to the player on screen
							mapArray[Map.getMapNum()].checkEnemys();// checks to see if enemies are hit
							player1.setAttacking(false);//removes the attack flag
						}
					}

					if (timer==1000)timer=0;// loops timer so it doesnt overflow
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
		
		for (Node object : solid.getChildren()) {//loops through every solid object
			
			//this monster if statement checks if the actor being checked intersects with any solid object
			if (object.getBoundsInParent().intersects(actor.getBounds().getMinX() + actor.getDeltaX(), actor.getBounds().getMinY() + actor.getDeltaY(), 
				actor.getBounds().getWidth(), actor.getBounds().getHeight()) && object.getId() != null) {
							
				if (object.getId().equals("wall")) return false;
				
				//player cannot move through enemy
			
				if (object.getId().equals("enemy")&&actor instanceof Player)return false;
				
				//enemy cannot move through player
				if (object.getId().equals("player")&&actor instanceof Enemy){
					hit(player1,actor);// damage is done to the player if a intersection is detected
					return false;
				}
				
				
				if (object.getId().equals("chest")) {
					solid.getChildren().remove(object);// removes the chest if intersection detected
					chestRoll();//random roll for a drop from the chest
					return false;
				}
			
					
				if (object.getId().equals("finish")) {
					if  (actor instanceof Player)nextMap();// if the player intersects the finish marker the map is set to next in the array
					return false;
					
				}
				
				// intersecting the players hitmarker
				if (object.getId().equals("damage")) {
					actor.setHealth(actor.getHealth()-player1.getDamage());// damages the enemy
					actor.checkAlive();
					return false;
				}
				//checks for enemy enemy collision, enemy cannot move through enemy
				if (object.getId().equals("enemy")&&actor instanceof Enemy){
					if(mapArray[Map.getMapNum()].enemyEnemyCollis((ImageView)object, (Enemy) actor)==true) {// loops through every object in the enemy array checking for collisions
						return false;
					}
				}	
			}
		}
	//return true if no intersection detected	
	return true;
	}

	
	/**
	 * Takes away health of actor1 if they collide with actor2 and then checks if they are alive.
	 * @param actor1 can be the player or enemy.
	 * @param actor2 can be the enemy or damage.
	 */
	private static void hit(Actors actor1, Actors actor2) {
		actor1.setHitCount(actor1.getHitCount()+1);
		
		if (actor1.getHitCount()==15) {// checks the number of intersctions, this makes sure the enemy doesnt instantly kill the player
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
		Map.setMapNum(Map.getMapNum()+1);//increments the mapnumber
		if(Map.getMapNum()<mapArray.length) {	
			mapArray[Map.getMapNum()].createMap();// creates the next map
			player1.teleport(mapArray[Map.getMapNum()].getPX(), mapArray[Map.getMapNum()].getPY());// moves the player to a spot indicated by the map object	
		}
	
		else {//hit the finish marker on the last map
			isRunning=false;
			root.setRoot(Menus.end(player1.getDamage(),"You Win"));//sets the layout to the end menu
		}
	mapArray[Map.getMapNum()-1].removeMap();  //removes the last map	
	}

	/**
	 * This is the random number generator which determiens what a player gets out of a chest.
	 */
	private static void chestRoll() {
		int roll = (int) (Math.ceil(Math.random() * 2));
		//this block handles swords
		if (roll== 1 && player1.getDamage()!=10) {// if the player has the best sword the chest will always return a healthpack
			
			// this block increments swords to the next best
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
		//handles healthpacks
		else if(GameGUI.getHealthVis()!=true) {
			GameGUI.setHealthVis(true);
		}
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
	 * method sets the current map to the number passed to it
	 * @param num mapNum to set
	 */
	public static void setMap(int num) {
		Map.setMapNum(num);
		mapArray[num].createMap();
		player1.teleport(mapArray[num].getPX(), mapArray[num].getPY());
		gameScreen.getChildren().add(solid);
	}
	
	
	/**
	 * gets the players x position
	 * @return player x position
	 */
	public static double getPlayerX() {
		return player1.getX();
	}
	
	/**
	 * gets the players y position
	 * @return player y position
	 */
	public static double getPlayerY() {
		return player1.getY();
	}
}
