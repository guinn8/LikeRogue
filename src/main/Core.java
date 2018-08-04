package main;

import javafx.scene.layout.*;
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
public abstract class Core extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public static Pane layout = new Pane();// Public Awareness

	public static Map map1 = new Map();// Public Awareness
	public static Inventory inventory = new Inventory();// Public Awareness
	private static Player player1 = new Player(75,75,10,5);
	private static Enemy enemy1 = new Enemy(400,400,10,2);
	private MyCanvas mCanvas=new MyCanvas(WIDTH,HEIGHT);
	private static final int WIDTH=600;
	private static final int HEIGHT=650;
	
	private boolean attack = false;


	@Override
	public void start(Stage stage) throws InterruptedException {

		stage.setTitle("Demo 2 player Fight");
		Scene scene = new Scene(layout, WIDTH, HEIGHT);
		stage.setScene(scene);
		layout.getChildren().add(mCanvas);
		map1.createMap("void");

		stage.show();

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

			if (e.getCode() == KeyCode.H) {
				if (inventory.getHealthbag().isVisible() == true) {
					getPlayer1().setHealth(10);
					inventory.getHealthbag().setVisible(false);
					System.out.println("pHealth " + getPlayer1().getHealth());
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
			@Override
			public void handle(long arg0) {

				getPlayer1().getDamageView().setLayoutX(-1000);//todo change so this lives in player
				getPlayer1().getDamageView().setLayoutY(-1000);
				
				player1.move();

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
	 * @param player1 the player1 to set
	 */
	public static void setPlayer1(Player player1) {
		Core.player1 = player1;
	}

	/**
	 * @return the enemy1
	 */
	public static Enemy getEnemy1() {
		return enemy1;
	}

	/**
	 * @param enemy1 the enemy1 to set
	 */
	public static void setEnemy1(Enemy enemy1) {
		Core.enemy1 = enemy1;
	}
}