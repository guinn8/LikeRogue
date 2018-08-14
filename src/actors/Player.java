package actors;

import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.*;


/**
 * This class primarily deals with player movement,state and actions.
 * @author Eric Zhang
 * @author Gavin Guinn
 * @Author Johnny Meng
 *thanks user https://opengameart.org/users/erbarlow for player sprites
 */
public class Player extends Actors {
	

	//private ImageView player= new ImageView(soldierImage);
	
	
	private Rectangle2D soldierPort;
	
	
	private ImageView damage = new ImageView(new Image("file:res/sprites/player/damage.png"));
	private ImageView player = new ImageView(new Image("file:res/sprites/player/soldier.png")); 

	
	
	private boolean attacking=false;

	/**
	 * Constructor for player
	 * @param setHealth Initial Health
	 * @param setDamage Initial Damage

	 */
	public Player(int setHealth, int setDamage){
		super(setHealth, setDamage,32,32,0,3);
		soldierPort= new Rectangle2D(0, 0, getW(), getH());
		
		
		damage.setId("damage");
		damage.setFitHeight(getH());
		damage.setFitWidth(getW());
	
		Core.addSolid(damage);
	
		player.setViewport(soldierPort);
		player.setId("player");
		Core.addSolid(player);
	}	

	
	/**
	 * This method will let the player attack and shows the sprite of the attack.
	 * @param dir the integer that determines the direction of the attack.
	 * @return false ends the method
	 */


	public boolean attack() {
			//up
			if (getDirection()==3) {
				damage.setLayoutX(player.getLayoutX());
				damage.setLayoutY(player.getLayoutY() - getH()-2);
			}
			//down
			else if (getDirection()==0) {
				damage.setLayoutX(player.getLayoutX());
				damage.setLayoutY(player.getLayoutY()+getH()+2);
			}
			//left
			else if (getDirection()==1) {
				damage.setLayoutX(player.getLayoutX() - getW()-2);
				damage.setLayoutY(player.getLayoutY());
			}
			//right
			else if (getDirection()==2) {
				damage.setLayoutX(player.getLayoutX()+getW()+2);
				damage.setLayoutY(player.getLayoutY());
			}
	return false;
	}
	

	
	/**
	 * This method will teleport the damage sprite away from the canvas when its called.
	 */




	public void resetDamage() {
		damage.setLayoutX(-1000);
		damage.setLayoutY(-1000);
	}
	
	
	/**
	 * Getter for bounds of the player
	 * @return bounds the boundaries of the player sprite
	 */
	public Bounds getBounds() {
		return player.getBoundsInParent();
	}
	
	
	/**
	 * Getter for ImageView of the player
	 * @return an ImageView of the player sprite
	 */
	protected ImageView getImageView() {
		return player;	
	}


//	int counter=0;
//	public void animate(int r) {
//		
//			Rectangle2D anim= new Rectangle2D(OFF+W*counter, r*H, W, H);
//			player.setViewport(anim);

	@Override
	public boolean attack(int dir) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the attacking
	 */
	public boolean isAttacking() {
		return attacking;
	}

	/**
	 * @param attacking the attacking to set
	 */
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	@Override
	public void drawHealthBar() {
		// TODO Auto-generated method stub
		
	}


	}


