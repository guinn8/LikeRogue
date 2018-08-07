package main;

import javafx.scene.layout.*;

import java.io.FileNotFoundException;

import actors.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
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

	public static Map map1 = new Map();// Public Awareness
	/*public static Map map2 = new Map();
	public static Map map3 = new Map();
	public static Map map4 = new Map();*/
	
	public static Inventory inventory = new Inventory();// Public Awareness
	private static Player player1 = new Player(75,75,10,5);
	private static Enemy enemy1 = new Enemy(400,400,10,2);
	
	private static final int WIDTH=600;
	private static final int HEIGHT=650;
	private static MyCanvas mCanvas = new MyCanvas(WIDTH, HEIGHT);
	private boolean attack = false;

	public static Stage nStage = new Stage();
	@Override
	public void start(Stage stage) throws InterruptedException, FileNotFoundException, IllegalArgumentException {
		nStage = stage;
		nStage.setTitle("LikeRogue");
		Scene scene = new Scene(layout, WIDTH, HEIGHT);
		nStage.setScene(scene);
		layout.getChildren().add(getmCanvas());
		map1.createMap("void");

		nStage.show();

		/**
		 * this handles player input
		 */
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.D) {
				getPlayer1().player.setImage(getPlayer1().getPlayerRight());
				getPlayer1().setDeltaX(Actors.getMoveRes()); 
				getPlayer1().setDeltaY(0);
			}

			if (e.getCode() == KeyCode.A) {
				getPlayer1().player.setImage(getPlayer1().getPlayerLeft());
				getPlayer1().setDeltaX(-Actors.getMoveRes()); 
				getPlayer1().setDeltaY(0);
			}

			if (e.getCode() == KeyCode.S) {
				getPlayer1().setDeltaY(Actors.getMoveRes());
				getPlayer1().setDeltaX(0);
				getPlayer1().player.setImage(getPlayer1().getPlayerDown());
			}

			if (e.getCode() == KeyCode.W) {
				getPlayer1().setDeltaY(-Actors.getMoveRes());
				getPlayer1().setDeltaX(0);
				getPlayer1().player.setImage(getPlayer1().getPlayerUp());
			}
			if(getEnemy1().getHealth() <= 0 || getPlayer1().getHealth() <= 0){
				Core.layout.getChildren().remove(getmCanvas());
				MyCanvas mCanvas2 = new MyCanvas(WIDTH, HEIGHT);
				Core.layout.getChildren().add(mCanvas2);
		
				if (e.getCode() == KeyCode.H) {
					if (inventory.getHealthbag().isVisible() == true) {
						getPlayer1().setHealth(10);
						inventory.getHealthbag().setVisible(false);
						Core.layout.getChildren().remove(mCanvas2);
						MyCanvas mCanvas3 = new MyCanvas(WIDTH, HEIGHT);
						Core.layout.getChildren().add(mCanvas3);
						System.out.println("pHealth " + getPlayer1().getHealth());
					}
				}	
			}
			
			if (e.getCode() == KeyCode.SPACE) {
				attack = true;
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
				getPlayer1().getDamageView().setLayoutX(-1000);//todo change so this lives in player
				getPlayer1().getDamageView().setLayoutY(-1000);
				
				try {
					player1.move(player1);
				} catch (FileNotFoundException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (counter%10==0)
					try {
						enemy1.enemyMove(enemy1);
					} catch (FileNotFoundException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				getPlayer1().setDeltaX(0);
				getPlayer1().setDeltaY(0);
				
				attack=(player1.attack(attack));// this is super convoluted. comment later
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

	public static MyCanvas getmCanvas() {
		return mCanvas;
	}

	public void setmCanvas(MyCanvas mCanvas) {
		this.mCanvas = mCanvas;
	}
	public static void removeMap() throws FileNotFoundException, InterruptedException {
		layout.getChildren().remove(Map.fins);
		layout.getChildren().remove(Map.chests);
		layout.getChildren().remove(Map.walls);	
	}

}