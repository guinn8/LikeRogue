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
		
		player.setId("player");
		damageView.setId("damage");
		
		damageView.setImage(damage);

		player.setLayoutX(setX);
		player.setLayoutY(setY);
		player.setImage(playerDown);
		
		Core.solid.getChildren().add(player);
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
		return player.getBoundsInParent();
	}
	
	/**
	 * 
	 */
	public ImageView getImageView() {
		return player;	
	}

	/**
	 * 
	 */
	public void tryAttack() {
		
		damageView.setLayoutX(-1000);
		damageView.setLayoutY(-1000);
		
		if (Core.isAttack()==true) {
			if (player.getImage() == playerUp) {
				damageView.setLayoutX(player.getLayoutX() - 20);
				damageView.setLayoutY(player.getLayoutY() - 75);
			}
	
			if (player.getImage() == playerDown) {
				damageView.setLayoutX(player.getLayoutX() - 15);
				damageView.setLayoutY(player.getLayoutY() + 50);
			}
			
			if (player.getImage() == playerLeft) {
				damageView.setLayoutX(player.getLayoutX() - 75);
				damageView.setLayoutY(player.getLayoutY() - 15);
			}
	
			if (player.getImage() == playerRight) {
				damageView.setLayoutX(player.getLayoutX() + 50);
				damageView.setLayoutY(player.getLayoutY() - 10);
			}
		}
	Core.setAttack(false);
	}
}
