package actors;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.*;

//import javafx.scene.layout.*;
/**
 * feature : - Sword: change damage to 3 - Healthbag: return full health -
 * inventory bar: Store iteams - chest return 3 choices
 * 
 * @author zhaoning meng
 */
//Eric's Changes: Added getter and setter for healthbag, sworda.
public class Inventory {


	private int chestchose = 0;
	
	private ImageView sworda = new ImageView();
	private Image sword = new Image("file:res/sprites/inventory/sword.jpg");

	private ImageView healthbag = new ImageView();
	private Image health = new Image("file:res/sprites/inventory/health.png");

	private ImageView inventory = new ImageView();
	private Image inventorybar = new Image("file:res/sprites/inventory/inventorybar.png");

	/**
	 * Those method in which add Image to the Core
	 * 
	 */
	public Inventory() {

		inventory.setImage(inventorybar);
		inventory.setLayoutX(40);
		inventory.setLayoutY(500);
		inventory.setPreserveRatio(true);
		inventory.setFitHeight(300);
		inventory.setFitWidth(500);
		Core.layout.getChildren().add(inventory);

		getSworda().setLayoutX(95);
		getSworda().setLayoutY(600);
		getSworda().setPreserveRatio(true);
		getSworda().setFitHeight(70);
		getSworda().setFitWidth(150);
		getSworda().setVisible(false);
		getSworda().setImage(sword);
		Core.layout.getChildren().add(getSworda());

		getHealthbag().setLayoutX(150);
		getHealthbag().setLayoutY(600);
		getHealthbag().setPreserveRatio(true);
		getHealthbag().setFitWidth(150);
		getHealthbag().setFitHeight(70);
		getHealthbag().setVisible(false);
		getHealthbag().setImage(health);
		Core.layout.getChildren().add(getHealthbag());
	}

	/**
	 * this method let chest make chose
	 * 
	 * @return 0,1,2
	 */
	public void chestRoll() {
		chestchose = (int) (Math.ceil(Math.random() * 2));
		
		if (chestchose== 1) {
			Core.getInventory().getSworda().setVisible(true);
			Core.getPlayer1().setDamage(3);
		}
		
		if (chestchose == 2) {
			Core.getInventory().getHealthbag().setVisible(true);
		}
	}

	public ImageView getHealthbag() {
		return healthbag;
	}

	public void setHealthbag(ImageView healthbag) {
		this.healthbag = healthbag;
	}

	public ImageView getSworda() {
		return sworda;
	}

	public void setSworda(ImageView sworda) {
		this.sworda = sworda;
	}
}
