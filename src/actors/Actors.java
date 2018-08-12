package actors;

import java.io.FileNotFoundException;

import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import main.Core;
/**
 * This abstract class deals with the state of our player and enemy sprites. It also handles the animations
 * for the player and enemy movement.
 * 
 * @author Eric Zhang
 * @author Gavin Guinn
 * @Author Johnny Meng
 *
 */
public abstract class Actors {
	private int frames;
	public static final int MOVERES = 1;
	private double lastX;
	private double lastY;
	private double deltaX = 0;
	private double deltaY = 0;
	private int health;
	private int damage;
	private int W;
	private int H;
	private int HOFF;
	private int VOFF;
	private int imageID;

<<<<<<< HEAD
	
	Actors( int setHealth, int setDamage, int setW, int setH, int setVOFF,int setFrames){
=======
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
>>>>>>> 3ee7dda9097c282de7c9eb7c03f033d532fd32e1
		damage=setDamage;
		health=setHealth;
		setW(setW);
		setH(setH);
		frames=setFrames;
		VOFF=setVOFF;
	}

	public abstract Bounds getBounds();
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
	
	int dir = 0;
	/**
	 * This method handles the animated movement of the player and enemy.
	 * @return a integer that will determine what direction the player will be going.
	 */
<<<<<<< HEAD
	int dir = 0;
	public int  move(){
		if (deltaX==0&&deltaY==0)return dir;
=======
	public int move(){
>>>>>>> 3ee7dda9097c282de7c9eb7c03f033d532fd32e1
		
		for (int i = 0; i < 10; i++) {
			
		
			//left
			if(getDeltaX()<0 && getDeltaY()>getDeltaX()) {
				dir=1;
				//animate(dir);
				//if (this instanceof Enemy)System.out.println("left");
			}
			//right
			else if(getDeltaX()>0 && getDeltaY()<getDeltaX()) {
				dir=2;
				//animate(dir);
			}
			//up
			else if(getDeltaY()>0 && getDeltaX()<getDeltaY()) {
				dir=0;
				//animate(dir);
			}
			//down
			else if(getDeltaY()<0 && getDeltaX()>getDeltaY()) {
				dir=3;
				
			}
			if (Core.check(this)) {
				
				getImageView().setLayoutY(getImageView().getLayoutY() + getDeltaY());
				getImageView().setLayoutX(getImageView().getLayoutX() + getDeltaX());
			}
		}
		animate(dir);
		setDelta(0,0);
		
		
		return dir;
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
	 * a setter for health.
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
	 * A getter for LastX
	 * @return the lastX
	 */
	public double getLastX() {
		return lastX;
	}

	/**
	 * A setter for LastX
	 * @param lastX the lastX to set
	 */
	public void setLastX(double lastX) {
		this.lastX = lastX;
	}

	/**
	 * A getter for LastY
	 * @return the lastY
	 */
	public double getLastY() {
		return lastY;
	}

	/**
	 * A setter for LastY
	 * @param lastY the lastY to set
	 */
	public void setLastY(double lastY) {
		this.lastY = lastY;
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
	int animCounter=0;
	
	/**
	 * This method creates the animations for player and enemy.
	 * @param r the direction integer from move()
	 */
	public void animate(int r) {
		
		//if (this instanceof Enemy)System.out.println("counter"+animCounter*W);
		
		
		Rectangle2D anim= new Rectangle2D(getW()*animCounter+HOFF, r*getH(), getW(), getH());
		
	
		this.getImageView().setViewport(anim);
		animCounter++;
		
		if (animCounter==frames) {
			animCounter=0;
			
		}
	}

	/**
	 * @return the w
	 */
	public int getW() {
		return W;
	}

	/**
	 * @param w the w to set
	 */
	public void setW(int w) {
		W = w;
	}

	/**
	 * @return the h
	 */
	public int getH() {
		return H;
	}

	/**
	 * @param h the h to set
	 */
	public void setH(int h) {
		H = h;
	}
	

	
	
}
