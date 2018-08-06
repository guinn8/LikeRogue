package actors;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.*;

//Erics changes: added getters and setters for every instance variables.
public class Player extends Actors {
	
	private ImageView damageView = new ImageView();
	private ImageView player = new ImageView(); 
	private Image playerRight = new Image("file:res/sprites/player/linkRight.png");
	private Image playerLeft = new Image("file:res/sprites/player/linkLeft.png");
	private Image playerDown = new Image("file:res/sprites/player/linkDown.png");
	private Image playerUp = new Image("file:res/sprites/player/linkUp.png");
	private Image damage = new Image("file:res/sprites/player/damage.png"); 
	Text heathText = new Text();
	/**
	 * 
	 * @param setX
	 * @param setY
	 * @param setHealth
	 * @param setDamage
	 */
	public Player(int setX, int setY, int setHealth, int setDamage) {
		
		super(setHealth, setDamage);
		
		getPlayer().setId("player");
		damageView.setId("damage");
		
		damageView.setImage(damage);

		getPlayer().setLayoutX(setX);
		getPlayer().setLayoutY(setY);
		getPlayer().setImage(playerDown);
		
		Core.solid.getChildren().add(getPlayer());
		Core.solid.getChildren().add(damageView);
		
		
		heathText.setText("Player's Health                 "+ " "+"Enemy's Health   ");
		heathText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		heathText.setLayoutX(50);
		heathText.setLayoutY(40);
		
		Core.layout.getChildren().add(heathText);
	}
	
	/**
	 * 
	 * @return
	 */
	public Image getPlayerRight() {
		return playerRight;
	}
		
	/** 
	 * 
	 * @return
	 */
	public Image getPlayerLeft() {
		return playerLeft;
	}
	
	/**
	 * 
	 * @return
	 */
	public Image getPlayerDown() {
		return playerDown;
	}

	/**
	 * 
	 * @return
	 */
	public Image getPlayerUp() {
		return playerUp;
	}
	
	/**
	 * 
	 * @return
	 */
	public ImageView getDamageView() {
		return damageView;
	}
	
	/**
	 * 
	 */
	public Bounds getBounds() {
		return getPlayer().getBoundsInParent();
	}
	
	/**
	 * 
	 */
	public ImageView getImageView() {
		return getPlayer();	
	}

	/**
	 * 
	 */
	public void tryAttack() {
		
		damageView.setLayoutX(-1000);
		damageView.setLayoutY(-1000);
		
		if (Core.isAttack()==true) {
			if (getPlayer().getImage() == playerUp) {
				damageView.setLayoutX(getPlayer().getLayoutX() - 20);
				damageView.setLayoutY(getPlayer().getLayoutY() - 75);
			}
	
			if (getPlayer().getImage() == playerDown) {
				damageView.setLayoutX(getPlayer().getLayoutX() - 15);
				damageView.setLayoutY(getPlayer().getLayoutY() + 50);
			}
			
			if (getPlayer().getImage() == playerLeft) {
				damageView.setLayoutX(getPlayer().getLayoutX() - 75);
				damageView.setLayoutY(getPlayer().getLayoutY() - 15);
			}
	
			if (getPlayer().getImage() == playerRight) {
				damageView.setLayoutX(getPlayer().getLayoutX() + 50);
				damageView.setLayoutY(getPlayer().getLayoutY() - 10);
			}
	
			if (damageView.getBoundsInParent().intersects(Core.getEnemy1().getBounds())) {
				
				Core.getEnemy1().setHealth(Core.getEnemy1().getHealth()-Core.getPlayer1().getDamage());
				checkAlive(Core.getEnemy1());
				
			}
		}
	Core.setAttack(false);
	
	
	}

	/**
	 * @return the player
	 */
	public ImageView getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(ImageView player) {
		this.player = player;
	}

}
