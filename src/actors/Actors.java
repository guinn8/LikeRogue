package actors;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import main.Core;

public abstract class Actors {

	public static final int MOVERES = 1;
	private int deltaX = 0;
	private int deltaY = 0;
	
	public abstract Bounds getBounds();
	protected abstract ImageView getImageView();
	
	
	private int health;
	private int damage;
	
	Actors( int setHealth, int setDamage){
		damage=setDamage;
		health=setHealth;
	}

	/**
	 * 
	 */
	public  void checkAlive() {
		if (this.getHealth() <= 0) {
				Core.removeSolid(this.getImageView());
				this.getImageView().setLayoutX(-10000);
				this.getImageView().setLayoutY(-10000);
		}	
	}
	
	/**
	 *  
	 */
	public void move() {
		
		for (int i = 0; i < 10; i++) {
			if (Core.check(this)) {
				getImageView().setLayoutY(getImageView().getLayoutY() + deltaY);
				getImageView().setLayoutX(getImageView().getLayoutX() + deltaX);
			}
		}
		setDelta(0,0);
	}

	public void setDelta(int setDeltaX,int setDeltaY) {
		deltaX=setDeltaX;
		deltaY=setDeltaY;
	}

	public int getHealth() {
		return health;
	}
	
	public void setHealth(int setHealth) {
		health=setHealth;
	}
	
	public int getDamage() {
		return damage;
	}
	public void setDamage(int setDamage) {
		damage=setDamage;
	}
	
	public int getDeltaX() {
		return deltaX;
	}
	public int getDeltaY() {
		return deltaY;
	}
}
