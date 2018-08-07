package actors;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import main.Core;

public abstract class Actors {

	private static int moveRes = 1;
	private double deltaX = 0;
	private double deltaY = 0;
	

	private int health;
	private int damage;
	
	
	
	
	Actors( int setHealth, int setDamage){
		damage=setDamage;
		health=setHealth;
	}

	
	public static int getMoveRes() {
		return moveRes;
	}
	

	public void setDeltaX(double setDeltaX) {
		deltaX=setDeltaX;
	}
	
	
	public void setDeltaY(double setDeltaY) {
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
	
	public abstract Bounds getBounds();
	public abstract ImageView getImageView();

	
	/**
	 * 
	 */

	
	/**
	 * overload of check alive
	 * @param a
	 */
	public  void checkAlive() {
		if (this.getHealth() <= 0) {
				Core.solid.getChildren().remove(this.getImageView());
				this.getImageView().setLayoutX(-10000);
				this.getImageView().setLayoutY(-10000);
		}	
	}


	
	/**
	 * @return 
	 * 
	 */
	public void move() {
		
		for (int i = 0; i < 10; i++) {
			if (Core.check(this)) {
				getImageView().setLayoutY(getImageView().getLayoutY() + deltaY);
				getImageView().setLayoutX(getImageView().getLayoutX() + deltaX);
				
			}
		}
	}
	
	public boolean move(Actors actor,int xDir,int yDir) {
		deltaX=xDir;
		deltaY=yDir;
		boolean canMove=true;
		for (int i = 0; i < 10; i++) {
			if (Core.check(actor)) {
				getImageView().setLayoutY(getImageView().getLayoutY() + yDir);
				getImageView().setLayoutX(getImageView().getLayoutX() + xDir);
				canMove=false;			
			}
		}
		return canMove;
	}


	public double getDeltaX() {
		return deltaX;
	}


	public double getDeltaY() {
		return deltaY;
	}
}
