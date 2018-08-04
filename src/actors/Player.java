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



}
