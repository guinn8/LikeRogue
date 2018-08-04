package actors;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.*;

//Erics changes: added getters and setters for every instance variables.
public class Player extends Actors {
	private ImageView damageView = new ImageView();
	public ImageView player = new ImageView(); // PublicAwareness
	
	private Image playerRight = new Image("file:data/player/linkRight.png");
	private Image playerLeft = new Image("file:data/player/linkLeft.png");
	private Image playerDown = new Image("file:data/player/linkDown.png");
	private Image playerUp = new Image("file:data/player/linkUp.png");
	private Image damage = new Image("file:data/player/damage.png");

	public Player(int setX, int setY, int setHealth, int setDamage) {
		
		super(setX, setY, setHealth, setDamage);
		
		getDamageView().setImage(damage);

		player.setLayoutX(setX);
		player.setLayoutY(setY);
		player.setImage(playerDown);

		Core.layout.getChildren().add(player);
		Core.layout.getChildren().add(getDamageView());
	}

	public Image getPlayerRight() {
		return playerRight;
	}

	public void setPlayerRight(Image playerRight) {
		this.playerRight = playerRight;
	}

	public Image getPlayerLeft() {
		return playerLeft;
	}

	public void setPlayerLeft(Image playerLeft) {
		this.playerLeft = playerLeft;
	}

	public Image getPlayerDown() {
		return playerDown;
	}

	public void setPlayerDown(Image playerDown) {
		this.playerDown = playerDown;
	}

	public Image getPlayerUp() {
		return playerUp;
	}

	public void setPlayerUp(Image playerUp) {
		this.playerUp = playerUp;
	}

	public ImageView getDamageView() {
		return damageView;
	}

	public void setDamageView(ImageView damageView) {
		this.damageView = damageView;
	}
	
	public void attack() {
		if (Core.getPlayer1().player.getImage() == Core.getPlayer1().getPlayerUp()) {
			Core.getPlayer1().getDamageView().setLayoutX(Core.getPlayer1().player.getLayoutX() - 20);
			Core.getPlayer1().getDamageView().setLayoutY(Core.getPlayer1().player.getLayoutY() - 75);
		}

		if (Core.getPlayer1().player.getImage() == Core.getPlayer1().getPlayerDown()) {
			Core.getPlayer1().getDamageView().setLayoutX(Core.getPlayer1().player.getLayoutX() - 15);
			Core.getPlayer1().getDamageView().setLayoutY(Core.getPlayer1().player.getLayoutY() + 50);
		}

		if (Core.getPlayer1().player.getImage() == Core.getPlayer1().getPlayerLeft()) {
			Core.getPlayer1().getDamageView().setLayoutX(Core.getPlayer1().player.getLayoutX() - 75);
			Core.getPlayer1().getDamageView().setLayoutY(Core.getPlayer1().player.getLayoutY() - 15);
		}

		if (Core.getPlayer1().player.getImage() == Core.getPlayer1().getPlayerRight()) {
			Core.getPlayer1().getDamageView().setLayoutX(Core.getPlayer1().player.getLayoutX() + 50);
			Core.getPlayer1().getDamageView().setLayoutY(Core.getPlayer1().player.getLayoutY() - 10);
		}

		if (Core.getPlayer1().getDamageView().getBoundsInParent().intersects(Core.getEnemy1().getEnemy().getBoundsInParent())) {
			System.out.println("Enemy Health " + Core.getEnemy1().getHealth());
			System.out.println("Player Health " + Core.getPlayer1().getHealth());
			System.out.println();
			
			Core.getEnemy1().setHealth(Core.getEnemy1().getHealth()-Core.getPlayer1().getDamage());
			
			if (Core.getEnemy1().getHealth() <= 0) {
				Core.layout.getChildren().remove(Core.getEnemy1().getEnemy());
				Core.getEnemy1().getEnemy().setLayoutX(-10000);
				Core.getEnemy1().getEnemy().setLayoutY(-10000);
			}
		}

	}



}
