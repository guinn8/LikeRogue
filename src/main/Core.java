package main;

import javafx.scene.layout.*;

import java.io.FileNotFoundException;

import actors.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
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
	
	
	
	public static Pane layout = new Pane();// Public Awareness
	
	public static Group solid= new Group();

	public static Map map1 = new Map();
	public static Map map2 = new Map();
	public static Map map3 = new Map();
	public static Map map4 = new Map();
	
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
				getPlayer1().getPlayer().setImage(getPlayer1().getPlayerRight());
				getPlayer1().setDeltaX(Actors.getMoveRes()); 
				getPlayer1().setDeltaY(0);
			}

			if (e.getCode() == KeyCode.A) {
				getPlayer1().getPlayer().setImage(getPlayer1().getPlayerLeft());
				getPlayer1().setDeltaX(-Actors.getMoveRes()); 
				getPlayer1().setDeltaY(0);
			}

			if (e.getCode() == KeyCode.S) {
				getPlayer1().setDeltaY(Actors.getMoveRes());
				getPlayer1().setDeltaX(0);
				getPlayer1().getPlayer().setImage(getPlayer1().getPlayerDown());
			}

			if (e.getCode() == KeyCode.W) {
				getPlayer1().setDeltaY(-Actors.getMoveRes());
				getPlayer1().setDeltaX(0);
				getPlayer1().getPlayer().setImage(getPlayer1().getPlayerUp());
			}
			
			if(getEnemy1().getHealth() <= 0 || getPlayer1().getHealth() <= 0){
				Core.layout.getChildren().remove(mCanvas);
				MyCanvas mCanvas2 = new MyCanvas(WIDTH, HEIGHT);
				Core.layout.getChildren().add(mCanvas2);
		
				if (e.getCode() == KeyCode.H) {
					if (getInventory().getHealthbag().isVisible() == true) {
						getPlayer1().setHealth(10);
						getInventory().getHealthbag().setVisible(false);
						Core.layout.getChildren().remove(mCanvas2);
						MyCanvas mCanvas3 = new MyCanvas(WIDTH, HEIGHT);
						Core.layout.getChildren().add(mCanvas3);
						System.out.println("pHealth " + getPlayer1().getHealth());
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
				
				if (counter%10==0) {
					player1.tryAttack();
					enemy1.move();
				}
				player1.setDeltaX(0);
				player1.setDeltaY(0);
				
				if (counter==1000)counter=0;
			}
		}; animator.start();
	}

	/**
	 * @return the player1
	 */
	public static Player getPlayer1() {
		return player1;
	}

	/**
	 * @return the enemy1
	 */
	public static Enemy getEnemy1() {
		return enemy1;
	}

	/**
	 * @return the inventory
	 */
	public static Inventory getInventory() {
		return inventory;
	}

	/**
	 * @param inventory the inventory to set
	 */
	public static void setInventory(Inventory inventory) {
		Core.inventory = inventory;
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
}