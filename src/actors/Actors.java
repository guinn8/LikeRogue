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



	
	Actors( int setHealth, int setDamage, int setW, int setH, int setVOFF,int setFrames){
		damage=setDamage;
		health=setHealth;
		W=setW;
		H=setH;
		setW(setW);
		setH(setH);
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
	public int dir = 0;
	public int  move(){
		if (deltaX==0&&deltaY==0)return dir;
		
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
			 
				
				getImageView().setLayoutY(getImageView().getLayoutY() + getDeltaY());
				getImageView().setLayoutX(getImageView().getLayoutX() + getDeltaX());
			
		}
		animate(dir);
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
		
		//if (this instanceof Enemy)System.out.println("counter"+animCounter*W);
		
		
		Rectangle2D anim= new Rectangle2D(getW()*animCounter, r*getH(), getW(), getH());
		
	
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
