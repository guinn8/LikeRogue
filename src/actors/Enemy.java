package actors;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.*;

//Eric's changes: Added getter and setter to Imageview enemy, hostileG.
public class Enemy extends Actors {
	private Group hostiles = new Group();
	private Image enemySprite = new Image("file:res/sprites/enemy/frown.png");
	private ImageView enemy = new ImageView();
	boolean right=true;

	public Enemy(int setX, int setY, int setHealth, int setDamage) {
		super(setHealth, setDamage);
		getEnemy().setLayoutX(setX);
		getEnemy().setLayoutY(setY);
		getEnemy().setImage(enemySprite);
		hostiles.getChildren().add(enemy);
		Core.layout.getChildren().add(hostiles);
	}

	public ImageView getEnemy() {
		return enemy;
	}


	@Override
	public Bounds getBounds() {
		return enemy.getBoundsInParent();
	}

	@Override
	public ImageView getImageView() {
		return enemy;
	}

	public void enemyMove(Enemy e) {
			
		if (right==true) {
			 move(e,-1,0);
			 if (move(e,-1,0)==true) right=false;	 
		}
		
		else if (right==false) {
			move(e,1,0);
			if (move(e,1,0)==true) right=true;	
		}
	}

	@Override
	public Group getGroup() {
		return hostiles;
	}

}
