package actors;


import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.*;
//thanks user kemono for the enemy sprite https://opengameart.org/content/monster-sprites
/**
 * This class deals primarily with the enemy movement and state.
 * @author Eric Zhang
 * @author Gavin Guinn
 * @Author Johnny Meng@author Eric1
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
	 * This method handles movement for enemy
	 * @return always returns 0 
	 */
	public int move() {
		if (this.checkAlive()==true) {
			double pX=Core.getPlayer1().getX()-Core.getPlayer1().getImageView().getFitWidth()/2;
			double pY=Core.getPlayer1().getY()-Core.getPlayer1().getImageView().getFitHeight()/2;
		
			double eX=this.getX();
			double eY=this.getY();
		
			double vX= pX-eX;
			double vY= pY-eY;
			double len= Math.sqrt(vX*vX+vY*vY);
			vX=(vX/len);
			vY=(vY/len);
			
			//System.out.println("dX: "+vX+" dY: "+vY);
			this.setDelta(vX, vY);
			if(Core.check(this)==true)super.move();
		}
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
<<<<<<< HEAD
	
	public void setUserData(Integer num) {
		enemy.setUserData(num);
	}
	public int getUserData() {
		return (int) enemy.getUserData();
	}
	
	
=======
>>>>>>> 3ee7dda9097c282de7c9eb7c03f033d532fd32e1
}
