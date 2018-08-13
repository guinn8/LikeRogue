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
 *
 */
public class Player extends Actors {
	
	private Image soldierImage = new Image("file:res/sprites/player/soldier.png");
	private Rectangle2D soldierPort;
	private Rectangle healthBar= new Rectangle();
	private Rectangle hbOutline= new Rectangle();
	private ImageView damage = new ImageView();
	private ImageView player = new ImageView(soldierImage); 
	private Image damageImage = new Image("file:res/sprites/player/damage.png"); 
	/**
	 * Constructor for player
	 * @param setHealth Initial Health
	 * @param setDamage Initial Damage
	 */
	public Player(int setHealth, int setDamage){
		super(setHealth, setDamage,32,32,0,3);
		soldierPort= new Rectangle2D(0, 0, getW(), getH());
		
		hbOutline.setX(0);
		hbOutline.setY(660);
		hbOutline.setWidth(600);
		hbOutline.setHeight(20);
		Core.addLayout(hbOutline);
		
		healthBar.setX(0);
		healthBar.setY(660);
		healthBar.setWidth(600);
		healthBar.setHeight(20);
		healthBar.setFill(Color.RED);
		Core.addLayout(healthBar);
		
		damage.setId("damage");
		damage.setFitHeight(getH());
		damage.setFitWidth(getW());
		damage.setImage(damageImage);
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
	public boolean attack(int dir) {	
			//up
			if (dir==3) {
				damage.setLayoutX(player.getLayoutX());
				damage.setLayoutY(player.getLayoutY() - getH()-2);
			}
			//down
			else if (dir==0) {
				damage.setLayoutX(player.getLayoutX());
				damage.setLayoutY(player.getLayoutY()+getH()+2);
			}
			//left
			else if (dir==1) {
				damage.setLayoutX(player.getLayoutX() - getW()-2);
				damage.setLayoutY(player.getLayoutY());
			}
			//right
			else if (dir==2) {
				damage.setLayoutX(player.getLayoutX()+getW()+2);
				damage.setLayoutY(player.getLayoutY());
			}
	return false;
	}	
	/**
	 * This method draws the health bar at the bottom
	 */
	public void drawHealthBar() {
		healthBar.setWidth(this.getHealth()*60);
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
}

