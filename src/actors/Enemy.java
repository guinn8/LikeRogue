package actors;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.*;

public class Enemy extends Actors {
	
	private Image enemySprite = new Image("file:res/sprites/enemy/frown.png");
	private ImageView enemy = new ImageView();
	
	boolean right=true;
	
	public Enemy(int setX, int setY, int setHealth, int setDamage) {
		super(setHealth, setDamage);
		enemy.setLayoutX(setX);
		enemy.setLayoutY(setY);
		enemy.setImage(enemySprite);
		Core.addSolid(enemy);

		enemy.setId("enemy");
	}


	public void move() {
			
		if (right==true) {
			this.setDelta(-MOVERES, 0);
			if (Core.check(this)==false)right=false;
			super.move();
				 
		}
		
		else if (right==false) {
			
			this.setDelta(MOVERES, 0);
			if (Core.check(this)==false)right=true;
			super.move();
			
		}
	}

	@Override
	public Bounds getBounds() {
		return enemy.getBoundsInParent();
	}

	@Override
	protected ImageView getImageView() {
		return enemy;
	}
}
