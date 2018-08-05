package actors;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.*;

//Erics changes: added getters and setters for every instance variables.
public class Player extends Actors {
	
	private ImageView damageView = new ImageView();
	public ImageView player = new ImageView(); // Public Awareness
	private Image playerRight = new Image("file:res/sprites/player/linkRight.png");
	private Image playerLeft = new Image("file:res/sprites/player/linkLeft.png");
	private Image playerDown = new Image("file:res/sprites/player/linkDown.png");
	private Image playerUp = new Image("file:res/sprites/player/linkUp.png");
	private Image damage = new Image("file:res/sprites/player/damage.png"); 
	private Group players = new Group();
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
		
		damageView.setImage(damage);

		player.setLayoutX(setX);
		player.setLayoutY(setY);
		player.setImage(playerDown);
		
		Core.layout.getChildren().add(players);
		players.getChildren().add(player);
		
		Core.layout.getChildren().add(damageView);
		
		
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
	@Override
	public Group getGroup() {
		return players;
	}
	
	/**
	 * 
	 * @param attack
	 * @return
	 */
	public boolean attack(boolean attack) {
		if (attack==true) {
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
	
			if (damageView.getBoundsInParent().intersects(Core.getEnemy1().getBounds())) {
				
				Core.getEnemy1().setHealth(Core.getEnemy1().getHealth()-Core.getPlayer1().getDamage());
				
				if (Core.getEnemy1().getHealth() <= 0) {
					Core.layout.getChildren().remove(Core.getEnemy1().getImageView());
					Core.getEnemy1().getImageView().setLayoutX(-10000);
					Core.getEnemy1().getImageView().setLayoutY(-10000);
				}
			}
		}
	return false;
	}
}
