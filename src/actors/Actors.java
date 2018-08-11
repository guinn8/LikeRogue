package actors;

import java.io.FileNotFoundException;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import main.Core;

public abstract class Actors {

	public static final int MOVERES = 1;
	private double lastX;
	private double lastY;
	private double deltaX = 0;
	private double deltaY = 0;
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
	public  boolean checkAlive() {
		if (this.getHealth() <= 0) {
				remove();
				return false;
		}
		return true;	
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

	public void setDelta(double vX,double vY) {
		deltaX=vX;
		deltaY=vY;
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
	public double getDeltaX() {
		return deltaX;
	}
	public double getDeltaY() {
		return deltaY;
	}
	
	public void teleport(double x, double y) {
		this.getImageView().setLayoutX(x);
		this.getImageView().setLayoutY(y);
	}
	
	public void remove() {
		Core.removeSolid(this.getImageView());
		this.getImageView().setImage(null);
		this.getImageView().setFitHeight(0);
		this.getImageView().setFitWidth(0);
	}

	/**
	 * @return the lastX
	 */
	public double getLastX() {
		return lastX;
	}

	/**
	 * @param lastX the lastX to set
	 */
	public void setLastX(double lastX) {
		this.lastX = lastX;
	}

	/**
	 * @return the lastY
	 */
	public double getLastY() {
		return lastY;
	}

	/**
	 * @param lastY the lastY to set
	 */
	public void setLastY(double lastY) {
		this.lastY = lastY;
	}
	
	public double getX() {
		return this.getImageView().getLayoutX();
		
	}
	public double getY() {
		return this.getImageView().getLayoutY();
		
	}
	
	
}
