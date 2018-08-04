package actors;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.*;

//Eric's changes: Added getter and setter to Imageview enemy, hostileG.
public class Enemy extends Actors {
	private Group hostiles = new Group();
	private Image enemySprite = new Image("file:res/sprites/enemy/frown.png");
	private ImageView enemy = new ImageView();

	public Enemy(int setX, int setY, int setHealth, int setDamage) {
		super(setX, setY, setHealth, setDamage);
		getEnemy().setLayoutX(setX);
		getEnemy().setLayoutY(setY);
		getEnemy().setImage(enemySprite);
		getHostileG().getChildren().add(getEnemy());
		Core.layout.getChildren().add(getHostileG());

	}

	public ImageView getEnemy() {
		return enemy;
	}

	public void setEnemy(ImageView enemy) {
		this.enemy = enemy;
	}

	public Group getHostileG() {
		return hostiles;
	}

	public void setHostileG(Group hostileG) {
		this.hostiles = hostileG;
	}

}
