package actors;


import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.*;
//Credits to user kemono for the enemy sprite https://opengameart.org/content/monster-sprites

/**
 * This class deals primarily with the enemy movement and state.
 * @author Eric Zhang
 * @author Gavin Guinn
 * @Author Johnny Meng
 *
 */
public class Enemy extends Actors {
	private Image enemySprite = new Image("file:res/sprites/enemy/skellysprite.png");
	private ImageView enemy = new ImageView(enemySprite);
	
	private Rectangle2D enemyport= new Rectangle2D(0, 0, getW(), getH());
	

	/**
	 * This is the constructor for enemy.
	 * @param setX Initial x-coordinate
	 * @param setY Initial y-coordinate
	 * @param setHealth Initial  health
	 * @param setDamage Initial Damage
	 */
	public Enemy(int setX, int setY, int setHealth, int setDamage) {
		super(setHealth, setDamage,16,18,0, 4);
		enemy.setScaleX(2.5);
		enemy.setScaleY(2.5);
		enemy.setLayoutX(setX);
		enemy.setLayoutY(setY);
		
		Core.addSolid(enemy);

		enemy.setId("enemy");
		enemy.setViewport(enemyport);
	}

	
	/**
	 * This allows the enemy to move towards the player.
	 * @param pX the player's x coordinate
	 * @param pY the player's y coordinate
	 * @return 0 always returns 0
	 */
	public int move(double pX, double pY) {

			double eX=this.getX();
			double eY=this.getY();
		
			double vX= pX-eX;
			double vY= pY-eY;
			double len= Math.sqrt(vX*vX+vY*vY);
			vX=(vX/len);
			vY=(vY/len);
			this.setDelta(vX, vY);
			if(Core.checkCollision(this)==true)super.move();
		  return 0;

	}
	
	
	/**
	 * Getter for Bounds
	 * @return the current bounds of the enemy sprite
	 */
	@Override
	public Bounds getBounds() {
		return enemy.getBoundsInParent();
	}
	
	
	/**
	 * getter for ImageView
	 * @return an ImageView of the enemy sprite. 
	 */
	@Override
	public ImageView getImageView() {
		return enemy;
	}
	
	
	/**
	 * lets the enemy attack in a certain direction
	 * @param dir is an integer that represents direction
	 * @boolean always returns false.
	 */
	public boolean attack(int dir) {
		return false;
	}
	
	
	/**
	 * Empty method in here so interface can run properly
	 */
	public void drawHealthBar() {}
	
	
	/**
	 * Empty method in here so interface can run properly.
	 */
	public void resetDamage() {}
}
