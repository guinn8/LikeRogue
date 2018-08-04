package main;

import javafx.scene.layout.*;
import actors.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Bounds;
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
public class Core extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public static Pane layout = new Pane();// PublicAwareness

	private Map map1 = new Map();
	private Inventory inventory = new Inventory();
	public static Player player1 = new Player(75,75,10,5);
	public static Enemy enemy1 = new Enemy(400,400,10,2);
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
				player1.player.setImage(player1.getPlayerRight());
				player1.setDeltaX(Actors.getMoveRes()); 
				player1.setDeltaY(0);
			}

			if (e.getCode() == KeyCode.A) {
				player1.player.setImage(player1.getPlayerLeft());
				player1.setDeltaX(-Actors.getMoveRes()); 
				player1.setDeltaY(0);
			}

			if (e.getCode() == KeyCode.S) {
				player1.setDeltaY(Actors.getMoveRes());
				player1.setDeltaX(0);
				player1.player.setImage(player1.getPlayerDown());
			}

			if (e.getCode() == KeyCode.W) {
				player1.setDeltaY(-Actors.getMoveRes());
				player1.setDeltaX(0);
				player1.player.setImage(player1.getPlayerUp());
			}

			if (e.getCode() == KeyCode.H) {
				if (inventory.getHealthbag().isVisible() == true) {
					player1.setHealth(10);
					inventory.getHealthbag().setVisible(false);
					System.out.println("pHealth " + player1.getHealth());
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

				player1.getDamageView().setLayoutX(-1000);
				player1.getDamageView().setLayoutY(-1000);

				for (int i = 0; i < 10; i++) {
					if (check(player1.getDeltaX(), player1.getDeltaY()) == true) {
						player1.player.setLayoutY(player1.player.getLayoutY() + player1.getDeltaY());
						player1.player.setLayoutX(player1.player.getLayoutX() + player1.getDeltaX());
					}
				}

				player1.setDeltaX(0);
				player1.setDeltaY(0);

				if (attack == true) {
					if (player1.player.getImage() == player1.getPlayerUp()) {
						player1.getDamageView().setLayoutX(player1.player.getLayoutX() - 20);
						player1.getDamageView().setLayoutY(player1.player.getLayoutY() - 75);
					}

					if (player1.player.getImage() == player1.getPlayerDown()) {
						player1.getDamageView().setLayoutX(player1.player.getLayoutX() - 15);
						player1.getDamageView().setLayoutY(player1.player.getLayoutY() + 50);
					}

					if (player1.player.getImage() == player1.getPlayerLeft()) {
						player1.getDamageView().setLayoutX(player1.player.getLayoutX() - 75);
						player1.getDamageView().setLayoutY(player1.player.getLayoutY() - 15);
					}

					if (player1.player.getImage() == player1.getPlayerRight()) {
						player1.getDamageView().setLayoutX(player1.player.getLayoutX() + 50);
						player1.getDamageView().setLayoutY(player1.player.getLayoutY() - 10);
					}

					if (player1.getDamageView().getBoundsInParent().intersects(enemy1.getEnemy().getBoundsInParent())) {
						System.out.println("Enemy Health " + enemy1.getHealth());
						System.out.println("Player Health " + player1.getHealth());
						System.out.println();
						
						enemy1.setHealth(enemy1.getHealth()-player1.getDamage());
						
						if (enemy1.getHealth() <= 0) {
							layout.getChildren().remove(enemy1.getEnemy());
							enemy1.getEnemy().setLayoutX(-10000);
							enemy1.getEnemy().setLayoutY(-10000);
						}
					}

					attack = false;
				}
			}
		};
		animator.start();
	}

	/**
	 * check handles the collision detection
	 * 
	 * @param xDelt distance to check ahead in the x direction
	 * @param yDelt distance to precheck in the y direction
	 * @return false if solid object in the way
	 */
	public boolean check(double xDelt, double yDelt) {
		Bounds pBound = player1.player.getBoundsInParent();

		for (Node object : enemy1.getHostileG().getChildren()) {

			if (object.getBoundsInParent().intersects(pBound.getMinX() + xDelt, pBound.getMinY() + yDelt,
				pBound.getWidth(), pBound.getHeight())) {
				
				enemy1.setHealth(enemy1.getHealth()-player1.getDamage());
				player1.setHealth(player1.getHealth()-1);

				System.out.println("Enemy Health " + enemy1.getHealth());
				System.out.println("Player Health " + player1.getHealth());

				// "Deaths of the sprites"
				if (player1.getHealth() <= 0) {
					layout.getChildren().remove(player1.player);
					System.out.println("You died!");
				}

				if (enemy1.getHealth() <= 0) {
					layout.getChildren().remove(enemy1.getEnemy());
					enemy1.getEnemy().setLayoutX(-10000);
					enemy1.getEnemy().setLayoutY(-10000);
				}
				return false;
			}
		}

		for (Node object : map1.walls.getChildren()) {
			if (object.getBoundsInParent().intersects(pBound.getMinX() + xDelt, pBound.getMinY() + yDelt,
				pBound.getWidth(), pBound.getHeight()))
				return false;
		}

		for (Node chest : map1.chests.getChildren()) {
			if (chest.getBoundsInParent().intersects(pBound.getMinX() + xDelt, pBound.getMinY() + yDelt,
				pBound.getWidth(), pBound.getHeight())) {
				map1.chests.getChildren().remove(chest);

				if (inventory.getchestchose() == 1) {
					inventory.getSworda().setVisible(true);
					player1.setDamage(3);
				}
				
				if (inventory.getchestchose() == 2) {
					inventory.getHealthbag().setVisible(true);
				}

				return false;
			}
		}

		return true;
	}
}
