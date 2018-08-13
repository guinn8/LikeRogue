package actors;

import java.io.FileNotFoundException;

import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import main.Core;

interface GameplayInterFace{
	public abstract boolean attack(int dir);
	public abstract void drawHealthBar();
	public abstract  void resetDamage(); 
}
public abstract class Actors implements GameplayInterFace {
	private static int frames;
	public static final int MOVERES = 1;
	private double lastX;
	private double lastY;
	private double deltaX = 0;
	private double deltaY = 0;
	private int health;
	private int damage;
	protected int W;
	protected int H;
	protected int OFF;

	
	Actors( int setHealth, int setDamage, int setW, int setH, int setOFF,int setFrames){
		damage=setDamage;
		health=setHealth;
		W=setW;
		H=setH;
		frames=setFrames;
	}

	public abstract Bounds getBounds();
	protected abstract ImageView getImageView();
	
	/**
	 * 
	 */
	public  boolean checkAlive() {
		if (this.getHealth() <= 0) {
			this.remove();
			return false;
		}
		return true;	
	}
	
	/**
	 * @throws FileNotFoundException 
	 *  
	 */
	int dir = 0;
	public int  move(){
		
		for (int i = 0; i < 10; i++) {
			//left
			if(getDeltaX()<0) {
				dir=1;
				animate(dir);
			}
			//right
			if(getDeltaX()>0) {
				dir=2;
				animate(dir);
			}
			//up
			if(getDeltaY()>0) {
				dir=0;
				animate(dir);
			}
			//down
			if(getDeltaY()<0) {
				dir=3;
				animate(dir);
			}
			if (Core.check(this)) {
				
				getImageView().setLayoutY(getImageView().getLayoutY() + getDeltaY());
				getImageView().setLayoutX(getImageView().getLayoutX() + getDeltaX());
			}
		}
		setDelta(0,0);
		
		return dir;
	}

	public void setDelta(double vX,double vY) {
		deltaX=vX;
		deltaY=vY;
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
		if(setDamage>=0&&setDamage<=10)damage=setDamage;
		
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
		this.getImageView().setLayoutX(-100);
		this.getImageView().setLayoutY(-100);
		
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
	int animCounter=0;
	public void animate(int r) {
		
		Rectangle2D anim= new Rectangle2D(W*animCounter+OFF, r*H, W, H);
		
	
		this.getImageView().setViewport(anim);
		
		animCounter++;
		if (animCounter==frames-1)animCounter=0;
	}

	  
}

