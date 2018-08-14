package actors;

import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.*;
//thanks user https://opengameart.org/users/erbarlow for player sprites



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
	
	
	private ImageView hitMarker = new ImageView(new Image("file:res/sprites/player/damage.png"));
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
		
		
		/*
		soldier.setLayoutX(200);
		soldier.setLayoutY(150);*/
	
		//Core.addSolid(soldier);

		
		hitMarker.setId("damage");
		hitMarker.setFitHeight(getH());
		hitMarker.setFitWidth(getW());
	
		Core.addSolid(hitMarker);
		

		//player.setImage(playerDown);
		player.setViewport(soldierPort);
		
		player.setId("player");
		Core.addSolid(player);
	}

	/**
	 * This method will let the player attack and shows the sprite of the attack.
	 * @param dir the integer that determines the direction of the attack.
	 * @return false ends the method
	 * 
	 */
	public boolean attack() {
		
			//up
			if (getDirection()==3) {
				hitMarker.setLayoutX(player.getLayoutX());
				hitMarker.setLayoutY(player.getLayoutY() - getH()-2);
			}
			//down
			else if (getDirection()==0) {
				hitMarker.setLayoutX(player.getLayoutX());
				hitMarker.setLayoutY(player.getLayoutY()+getH()+2);
			}
			//left
			else if (getDirection()==1) {
				hitMarker.setLayoutX(player.getLayoutX() - getW()-2);
				hitMarker.setLayoutY(player.getLayoutY());
			}
			//right
			else if (getDirection()==2) {
				hitMarker.setLayoutX(player.getLayoutX()+getW()+2);
				hitMarker.setLayoutY(player.getLayoutY());
			}
	return false;
	}
	

	/**
	 * This method will teleport the damage sprite away from the canvas when its called.
	 */
	public void resetHitMarker() {
		hitMarker.setLayoutX(-1000);
		hitMarker.setLayoutY(-1000);
	}
	
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


	}

