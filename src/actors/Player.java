package actors;




import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.*;

//Erics changes: added getters and setters for every instance variables.
public class Player extends Actors {
	
	
	private Rectangle healthBar= new Rectangle();
	
	
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
		
		healthBar.setX(0);
		healthBar.setY(660);
		healthBar.setWidth(600);
		healthBar.setHeight(20);
		healthBar.setFill(Color.RED);
	
		
		Core.addLayout(healthBar);
		
		player.setId("player");
		damageView.setId("damage");
		
		damageView.setImage(damage);

		player.setLayoutX(setX);
		player.setLayoutY(setY);
		player.setImage(playerDown);
		
		Core.addSolid(player);
		Core.addSolid(damageView);
		
		
		heathText.setText("Player's Health                 "+ " "+"Enemy's Health   ");
		heathText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		heathText.setLayoutX(50);
		heathText.setLayoutY(40);
		
		Core.addLayout(heathText);
	}
	
	/**
	 * 
	 */
	public boolean attack() {
		
		
		
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
	return false;
	}
	
   
   
	
	public void resetDamage() {
		damageView.setLayoutX(-1000);
		damageView.setLayoutY(-1000);
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
	protected ImageView getImageView() {
		return player;	
	}
	
	public void drawHealthBar() {
		healthBar.setWidth(this.getHealth()*60);
		
	}
}
