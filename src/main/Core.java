package main;

import javafx.scene.layout.*;

import java.io.FileNotFoundException;

import actors.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

/**
 * Haven't found a way to check the boundaries of the window. ~Eric.Z Features
 * that are in here: - Movement with WASD
 *
 * @author Eric Zhang
 * @author Gavin Guinn
 */

//changed player,enemy, healthbag calls to getters
public  class Core extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	private static int hitCount=0;
	
	private static Pane layout = new Pane();// Public Awareness
	
	private static Group solid= new Group();

	private static Map map1 = new Map();
//	private static Map map2 = new Map();
//	private static Map map3 = new Map();
//	private static Map map4 = new Map();
	
	private static Inventory inventory = new Inventory();
	private static Player player1 = new Player(75,75,10,5);
	private static Enemy enemy1 = new Enemy(400,400,10,2);
	
	private static final int WIDTH=600;
	private static final int HEIGHT=650;
	private MyCanvas mCanvas = new MyCanvas(WIDTH, HEIGHT);
	private static boolean attack = false;


	@Override
	public void start(Stage stage) throws InterruptedException, FileNotFoundException {
		
		stage.setTitle("LikeRogue");
		Scene scene = new Scene(layout, WIDTH, HEIGHT);
		stage.setScene(scene);
		layout.getChildren().add(mCanvas);
		map1.createMap("void");
		
		layout.getChildren().add(solid);

		stage.show();

		/**
		 * this handles player input
		 */
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.D) {
				player1.setPlayerRight();
				player1.setDeltaX(Actors.MOVERES); 
				player1.setDeltaY(0);
			}

			if (e.getCode() == KeyCode.A) {
				player1.setPlayerLeft();
				player1.setDeltaX(-Actors.MOVERES); 
				player1.setDeltaY(0);
			}

			if (e.getCode() == KeyCode.S) {
				player1.setDeltaY(Actors.MOVERES);
				player1.setDeltaX(0);
				player1.setPlayerDown();
			}

			if (e.getCode() == KeyCode.W) {
				player1.setDeltaY(-Actors.MOVERES);
				player1.setDeltaX(0);
				player1.setPlayerUp();
			}
			
			if(enemy1.getHealth() <= 0 || player1.getHealth() <= 0){
				layout.getChildren().remove(mCanvas);
				MyCanvas mCanvas2 = new MyCanvas(WIDTH, HEIGHT);
				layout.getChildren().add(mCanvas2);
		
				if (e.getCode() == KeyCode.H) {
					if (inventory.getHealthVis() == true) {
						player1.setHealth(10);
						inventory.setHealthVis(false);
						layout.getChildren().remove(mCanvas2);
						MyCanvas mCanvas3 = new MyCanvas(WIDTH, HEIGHT);
						layout.getChildren().add(mCanvas3);
						System.out.println("pHealth " + player1.getHealth());
					}
				}	
			}
			
			if (e.getCode() == KeyCode.SPACE) {
				setAttack(true);
			}
		});

		/**
		 * this handles the main game loop
		 */
		AnimationTimer animator = new AnimationTimer() {
			int counter;
			@Override
			public void handle(long arg0) {
				counter++;
				player1.move();
				player1.tryAttack();
				if (counter%10==0) {
					enemy1.move();
				}
				player1.setDeltaX(0);
				player1.setDeltaY(0);
				
				if (counter==1000)counter=0;
			}
		}; animator.start();
	}
	
	/**
	 * check handles the collision detection
	 * 
	 * @param xDelt distance to check ahead in the x direction
	 * @param yDelt distance to precheck in the y direction
	 * @return false if solid object in the way
	 **/
	public static boolean check(Actors actor) {
	
		for (Node object : solid.getChildren()) {
			if (object.getBoundsInParent().intersects(actor.getBounds().getMinX() + actor.getDeltaX(), actor.getBounds().getMinY() + actor.getDeltaY(), 
				actor.getBounds().getWidth(), actor.getBounds().getHeight()) && object.getId() != null) {
							
				if (object.getId().equals("wall")) return false;
				
				if (actor instanceof Player) {
					if (object.getId().equals("enemy")){
						hit(actor,enemy1);
						return false;
					}
				}
				
				if(actor instanceof Enemy) {
					if (object.getId().equals("player")){
						hit(actor, player1);
						return false;
					}
				}
				
				if (object.getId().equals("chest")) {
					solid.getChildren().remove(object);
					Core.getInventory().chestRoll();
					return false;
				}
				
				if (object.getId().equals("finish")) {
					return false;
				}
				
				if (object.getId().equals("damage")) {
					
					enemy1.setHealth(enemy1.getHealth()-player1.getDamage());
					actor.checkAlive();
					return false;
				}
			}
		}
		return true;
	}

	
	public static void hit(Actors actor1, Actors actor2) {
		hitCount++;
		if (hitCount==100) {
			actor1.setHealth(actor1.getHealth()-actor2.getDamage());
			actor2.setHealth(actor2.getHealth()-actor1.getDamage());
			hitCount=0;
		}
		actor1.checkAlive();
		actor2.checkAlive();
	}

	//this block needs to be removed
	//code needs to be reformatted before that can happen
	public static int getPlayer1Health() {
		return player1.getHealth();
	}
	public static void setPlayer1Health(int health) {
		player1.setHealth(health);
	}
	
	public static void setPlayer1Damage(int damage) {
		player1.setDamage(damage);
	}
	public static int getEnemy1Health() {
		return enemy1.getHealth();
	}
	public static void setEnemy1Health(int health) {
		enemy1.setHealth(health);
	}
	//
	//end block

	/**
	 * @return the inventory
	 */
	public static Inventory getInventory() {
		return inventory;
	}


	/**
	 * @return the attack
	 */
	public static boolean isAttack() {
		return attack;
	}

	/**
	 * @param attack the attack to set
	 */
	public static void setAttack(boolean setAttack) {
		attack = setAttack;
	}

	/**
	 * @return the layout
	 */
	public static void addLayout(Node n) {
		layout.getChildren().add(n);
	}
	
	/**
	 * @return the layout
	 */
	public static void addSolid(Node n) {
		solid.getChildren().add(n);
	}
	
	public static void removeSolid(Node n) {
		solid.getChildren().remove(n);
	}
	

}