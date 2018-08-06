package actors;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import main.Core;

public abstract class Actors {
	private static int moveRes = 1;
	private double deltaX = 0;
	private double deltaY = 0;
	

	private int health;
	private int damage;
	
	private int hitCount=0;
	
	
	Actors( int setHealth, int setDamage){
		damage=setDamage;
		health=setHealth;
	}

	
	public static int getMoveRes() {
		return moveRes;
	}
	
	/**
	 * overload of check alive
	 * @param a
	 */
	public static void checkAlive(Actors a) {
		if (a.getHealth() <= 0) {
				Core.solid.getChildren().remove(a.getImageView());
				a.getImageView().setLayoutX(-10000);
				a.getImageView().setLayoutY(-10000);
		}	
	}
	
	public void checkAlive() {
		if (health <= 0) {
			Core.solid.getChildren().remove(getImageView());
			getImageView().setLayoutX(-10000);
			getImageView().setLayoutY(-10000);
		}
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
	 * check handles the collision detection
	 * 
	 * @param xDelt distance to check ahead in the x direction
	 * @param yDelt distance to precheck in the y direction
	 * @return false if solid object in the way
	 **/
	public boolean check( Actors actor) {
	
		for (Node object : Core.solid.getChildren()) {
			if (object.getBoundsInParent().intersects(getBounds().getMinX() + deltaX, getBounds().getMinY() + deltaY, 
				getBounds().getWidth(), getBounds().getHeight()) && object.getId() != null) {
							
				if (object.getId().equals("wall")) return false;
				
				if (actor instanceof Player) {
					if (object.getId().equals("enemy")){
						hit();
						return false;
					}
				}
				
				if(actor instanceof Enemy) {
					if (object.getId().equals("player")){
						hit();
						return false;
					}
				}
				
				if (object.getId().equals("chest")) {
					Core.solid.getChildren().remove(object);
					Core.getInventory().chestRoll();
					return false;
				}
				
				if (object.getId().equals("finish")) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 */
	public void hit() {
		hitCount++;
		if (hitCount==100) {
			Core.getEnemy1().setHealth(Core.getEnemy1().getHealth()-Core.getPlayer1().getDamage());
			Core.getPlayer1().setHealth(Core.getPlayer1().getHealth()-1);
			hitCount=0;
		}
		checkAlive();
	}

	
	/**
	 * @return 
	 * 
	 */
	public void move() {
		
		for (int i = 0; i < 10; i++) {
			if (check(this)) {
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
			if (check(actor)) {
				getImageView().setLayoutY(getImageView().getLayoutY() + yDir);
				getImageView().setLayoutX(getImageView().getLayoutX() + xDir);
				canMove=false;			
			}
		}
		return canMove;
	}
}
