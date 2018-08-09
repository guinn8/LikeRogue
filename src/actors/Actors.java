package actors;

import java.io.FileNotFoundException;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import main.Core;

public abstract class Actors {

	public static final int MOVERES = 1;
	private int deltaX = 0;
	private int deltaY = 0;
	private int health;
	private int damage;
	
	Actors( int setHealth, int setDamage){
		damage=setDamage;
		health=setHealth;
	}

	public abstract Bounds getBounds();
	protected abstract ImageView getImageView();
	
	/**
	 * 
	 */
	public  void checkAlive() {
		if (this.getHealth() <= 0) {
				remove();
		}	
	}
	
	/**
	 * @throws FileNotFoundException 
	 *  
	 */
	public void move(){
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
		if(setHealth>=0&&setHealth<=10)
		health=setHealth;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int setDamage) {
		if(setDamage>=0&&setDamage<=10)
		damage=setDamage;
	}
	public int getDeltaX() {
		return deltaX;
	}
	public int getDeltaY() {
		return deltaY;
	}
	
	public void teleport(double x, double y) {
		this.getImageView().setX(x);
		this.getImageView().setY(y);
	}
	
	public void remove() {
		Core.removeSolid(this.getImageView());
		this.getImageView().setImage(null);
		this.getImageView().setFitHeight(0);
		this.getImageView().setFitWidth(0);
	}
	
}
