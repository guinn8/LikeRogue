package actors;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.*;

//Erics changes: added getters and setters for every instance variables.
public class Player extends Actors {
	
	private Rectangle healthBar= new Rectangle();
	private Rectangle hbOutline= new Rectangle();
	private ImageView damage = new ImageView();
	private ImageView player = new ImageView(); 
	private Image playerRight = new Image("file:res/sprites/player/linkRight.png");
	private Image playerLeft = new Image("file:res/sprites/player/linkLeft.png");
	private Image playerDown = new Image("file:res/sprites/player/linkDown.png");
	private Image playerUp = new Image("file:res/sprites/player/linkUp.png");
	private Image damageImage = new Image("file:res/sprites/player/damage.png"); 
	
	/**
	 * 
	 * @param setX
	 * @param setY
	 * @param setHealth
	 * @param setDamage
	 */
	public Player(int setHealth, int setDamage) {
		super(setHealth, setDamage);
		
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
		damage.setImage(damageImage);
		Core.addSolid(damage);
		

		player.setImage(playerDown);
		player.setId("player");
		Core.addSolid(player);
	}

	/**
	 * 
	 */
	public boolean attack() {
			if (player.getImage() == playerUp) {
				damage.setLayoutX(player.getLayoutX() - 15);
				damage.setLayoutY(player.getLayoutY() - 55);
			}
	
			else if (player.getImage() == playerDown) {
				damage.setLayoutX(player.getLayoutX() - 15);
				damage.setLayoutY(player.getLayoutY() + 30);
			}
			
			else if (player.getImage() == playerLeft) {
				damage.setLayoutX(player.getLayoutX() - 55);
				damage.setLayoutY(player.getLayoutY() - 15);
			}
	
			else if (player.getImage() == playerRight) {
				damage.setLayoutX(player.getLayoutX() + 20);
				damage.setLayoutY(player.getLayoutY() - 10);
			}
	return false;
	}
	
	public void drawHealthBar() {
		healthBar.setWidth(this.getHealth()*60);
	}

	public void resetDamage() {
		damage.setLayoutX(-1000);
		damage.setLayoutY(-1000);
	}
	
	public void setPlayerRight() {
		player.setImage(playerRight);
	}
	public void setPlayerLeft() {
		player.setImage(playerLeft);
	}
	public void setPlayerDown() {
		player.setImage(playerDown);
	}
	public void setPlayerUp() {
		player.setImage(playerUp);
	}
	public Bounds getBounds() {
		return player.getBoundsInParent();
	}
	//possible privacy leak
	protected ImageView getImageView() {
		return player;	
	}
}
