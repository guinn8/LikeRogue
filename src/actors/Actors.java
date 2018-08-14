package actors;



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


	
	Actors( int setHealth, int setDamage, int setW, int setH, int setVOFF,int setFrames){
		damage=setDamage;
		health=setHealth;
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
	 * @return 
	 * @throws FileNotFoundException 
	 *  
	 */
	
	public  void move(){
		if (deltaX==0&&deltaY==0) return;
		
		for (int i = 0; i < 10; i++) {
			
		
			//left
			if(getDeltaX()<0 && getDeltaY()>getDeltaX()) {
				setDirection(1);
				//animate(dir);
				//if (this instanceof Enemy)System.out.println("left");
			}
			//right
			else if(getDeltaX()>0 && getDeltaY()<getDeltaX()) {
				setDirection(2);
				//animate(dir);
			}
			//up
			else if(getDeltaY()>0 && getDeltaX()<getDeltaY()) {
				setDirection(0);
				//animate(dir);
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

	/**
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * @return the hitCount
	 */
	public int getHitCount() {
		return hitCount;
	}

	/**
	 * @param hitCount the hitCount to set
	 */
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
	

	
	
}
