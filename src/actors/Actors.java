package actors;

import javafx.geometry.Bounds;
import javafx.scene.Group;
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
	
	
	public void checkAlive() {
		if (health <= 0) {
			System.out.println("ran");
			getGroup().getChildren().remove(getImageView());
			getImageView().setLayoutX(-10000);
			getImageView().setLayoutY(-10000);
		}
	}
	
	public abstract Bounds getBounds();
	public abstract ImageView getImageView();
	public abstract Group getGroup();
	
	/**
	 * check handles the collision detection
	 * 
	 * @param xDelt distance to check ahead in the x direction
	 * @param yDelt distance to precheck in the y direction
	 * @return false if solid object in the way
	 **/
	public boolean check( Actors actor) {
	
		/**
		 * handles enemy/player collision 
		 */
		if(actor instanceof Player) {
			for (Node object : Core.getEnemy1().getGroup().getChildren()) {
			
				if (object.getBoundsInParent().intersects(getBounds().getMinX() + deltaX, getBounds().getMinY() + deltaY, 
					getBounds().getWidth(), getBounds().getHeight())){
		
					hitCount++;
					
					if (hitCount==100) {
						Core.getEnemy1().setHealth(Core.getEnemy1().getHealth()-Core.getPlayer1().getDamage());
						Core.getPlayer1().setHealth(Core.getPlayer1().getHealth()-1);
						hitCount=0;
					}
		
					
					checkAlive();

					return false;
				}
			}
		}
		
		if(actor instanceof Enemy) {
			if (Core.getPlayer1().getBounds().intersects(getBounds().getMinX() + deltaX, getBounds().getMinY() + deltaY,
					getBounds().getWidth(), getBounds().getHeight())) {
						hitCount++;
						if (hitCount==100) {
							Core.getEnemy1().setHealth(Core.getEnemy1().getHealth()-Core.getPlayer1().getDamage());
							Core.getPlayer1().setHealth(Core.getPlayer1().getHealth()-1);
							hitCount=0;
						}
						
						checkAlive();
						return false;
			}
		}
		
		/**
		 * handles wall collision
		 */
		for (Node object : Core.map1.getWalls().getChildren()) {
			if (object.getBoundsInParent().intersects(getBounds().getMinX() + deltaX, getBounds().getMinY() + deltaY,
				getBounds().getWidth(), getBounds().getHeight())) {		
					return false;
				}
		}
		
		/**
		 * handles chest collision
		 */
		for (Node chest : Core.map1.getChests().getChildren()) {
			if (chest.getBoundsInParent().intersects(getBounds().getMinX() + deltaX, getBounds().getMinY() + deltaY,
				getBounds().getWidth(), getBounds().getHeight())) {
				
				Core.map1.getChests().getChildren().remove(chest);

				if (Core.inventory.getchestchose() == 1) {
					Core.inventory.getSworda().setVisible(true);
					Core.getPlayer1().setDamage(3);
				}
				
				if (Core.inventory.getchestchose() == 2) {
					Core.inventory.getHealthbag().setVisible(true);
				}
				return false;
			}
		}
		
		for (Node chest : Core.map1.getFins().getChildren()) {
			if (chest.getBoundsInParent().intersects(getBounds().getMinX() + deltaX, getBounds().getMinY() + deltaY,
					getBounds().getWidth(), getBounds().getHeight())) {
			}
		}
		
	return true;
	}
	
	/**
	 * 
	 */
	public boolean move(Actors actor) {
		boolean canMove=true;
		for (int i = 0; i < 10; i++) {
			if (check(actor)) {
				getImageView().setLayoutY(getImageView().getLayoutY() + deltaY);
				getImageView().setLayoutX(getImageView().getLayoutX() + deltaX);
				canMove=false;
			}
		}
		return canMove;
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
