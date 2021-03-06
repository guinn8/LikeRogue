package actors;

import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import main.Core;


/**
 * This interface has methods that affect the state of the player and/or enemy in some way.
 *
 */
interface GameplayInterFace{	
	public abstract  void resetHitMarker(); 

}


  /**
 * This abstract class deals with the state of our player and enemy sprites. It also handles the animations
 * for the player and enemy movement.
 * 
 * @author Eric Zhang
 * @author Gavin Guinn
 * @Author Johnny Meng
 *
 */
public abstract class Actors implements GameplayInterFace {
	private  int frames;	
	public static final int MOVERES = 1;

	private double deltaX = 0;
	private double deltaY = 0;
	private int health;
	private int damage;

	private int W;
	private int H;
	private int direction;
	private int hitCount;
	private int animCounter=0;
  
	
	/**
	 * This is the super constructor for Actors. It is used in player and enemy when creating the characters.
	 * @param setHealth
	 * @param setDamage
	 * @param setW
	 * @param setH
	 * @param setOFF
	 * @param setFrames
	 */
	Actors( int setHealth, int setDamage, int setW, int setH, int setOFF,int setFrames){
		damage=setDamage;
		health=setHealth;
		W=setW;
		H=setH;
		frames=setFrames;
	}
	
	
	/**
	 * abstract method for getting the boundaries of an object
	 * @return the boundaries of an object
	 */
	public abstract Bounds getBounds();	
	 
	
	/**
	 * abstract method for getting the ImageView of an object.
	 * @return the ImageView.
	 */
	protected abstract ImageView getImageView();	
	
	
	/**
	 * This method evaluates the state of the player and enemy. It'll check their health and see if they should be dead
	 * @return a boolean that returns true if the player/enemy is still alive and false otherwise.
	 */
	public  boolean checkAlive() {
		if (this.getHealth() <= 0) {
			this.remove();
			return false;
		}
		return true;	
	}	
	  

  /**
	 * This method handles the animated movement of the player and enemy.
	 * @return a integer that will determine what direction the player will be going.
	 */
	public  void move(){
		if (deltaX==0&&deltaY==0) return;
		for (int i = 0; i < 10; i++) {
      
			//left
			if(getDeltaX()<0 && getDeltaY()>getDeltaX()) {
				setDirection(1);		
			}
      
			//right
			else if(getDeltaX()>0 && getDeltaY()<getDeltaX()) {
				setDirection(2);
			}
      
			//up
			else if(getDeltaY()>0 && getDeltaX()<getDeltaY()) {
				setDirection(0);
			}
      
			//down
			else if(getDeltaY()<0 && getDeltaX()>getDeltaY()) {
				setDirection(3);
			}
  
			if (Core.checkCollision(this)) {			
				getImageView().setLayoutY(getImageView().getLayoutY() + getDeltaY());
				getImageView().setLayoutX(getImageView().getLayoutX() + getDeltaX());
			}
		}
		 animate(getDirection());
		 setDelta(0,0);
	}

	
	/**
	 * A setter for delta.
	 * @param vX
	 * @param vY
	 */
	public void setDelta(double vX,double vY) {
		deltaX=vX;
		deltaY=vY;
	}
	
	
	/**
	 * a getter for health.
	 * @return a integer that represents health.
	 */
	public int getHealth() {
		return health;
	}

	
	/**
	 * A setter for health.
	 * @param setHealth
	 */
	public void setHealth(int setHealth) {	
		health=setHealth;
	}

	
	/**
	 * A getter for damage.
	 * @return an integer that represents damage.
	 */
	public int getDamage() {
		return damage;
	}
	

	/**
	 * A setter for damage.
	 * @param setDamage
	 */
	public void setDamage(int setDamage) {
		if(setDamage>=0&&setDamage<=10)damage=setDamage;
	}
	

	/**
	 * A getter for deltaX (X-coordinate)
	 * @return a double that represents deltaX/x-coordinates
	 */
	public double getDeltaX() {
		return deltaX;
	}
	
	
	/**
	 * A getter for deltaY (Y-coordinate)
	 * @return a double that represents deltaY/y-coordinates
	 */
	public double getDeltaY() {
		return deltaY;
	}	
	
	
	/**
	 * This method will teleport a sprite to a set of coordinates.
	 * @param x the X-Coordinate
	 * @param y the Y-Coordinate
	 */
	public void teleport(double x, double y) {
		this.getImageView().setLayoutX(x);
		this.getImageView().setLayoutY(y);
	}
	
	
	/**
	 * This method will remove a sprite from the canvas.
	 */
	public void remove() {
		Core.removeSolid(this.getImageView());
		this.getImageView().setImage(null);
		this.getImageView().setLayoutX(-100);
		this.getImageView().setLayoutY(-100);		
	}

  
	/**
	 * A getter for X
	 * @return the current x-coordinate
	 */
	public double getX() {
		return this.getImageView().getLayoutX();
	}
	
  
	/**
	 * A getter for y
	 * @return the current y-coordinate
	 */
	public double getY() {
		return this.getImageView().getLayoutY();	
	}
	
  
	/**
	 * This method creates the animations for player and enemy.
	 * @param r the direction integer from move()
	 */
	public void animate(int r) {	
		Rectangle2D anim= new Rectangle2D(getW()*animCounter, r*getH(), getW(), getH());
		this.getImageView().setViewport(anim);
		animCounter++;
		if (animCounter==frames-1)animCounter=0;
	}

	
	/**getmethod
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}

	
	/**setmethod
	 * @param direction the direction to set
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	
	/**get method
	 * @return the hitCount
	 */
	public int getHitCount() {
		return hitCount;
	}

	
    /**setmethod
	 * @param hitCount the hitCount to set
	 */
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
	
	
	/**
	 * get method 
	 * @return W
	 */
	public int getW() {
		return W;
	}
	
	
	/**
	 * get method
	 * @return H
	 */
	public int getH() {
		return H;
	}
		
}
