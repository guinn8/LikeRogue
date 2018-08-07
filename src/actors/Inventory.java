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
	
	private ImageView sword = new ImageView();
	final private Image swordImage = new Image("file:res/sprites/inventory/sword.jpg");

	private ImageView healthbag = new ImageView();
	final private Image healthBagImage = new Image("file:res/sprites/inventory/health.png");

	private ImageView inventory = new ImageView();
	final private Image inventoryBarImage = new Image("file:res/sprites/inventory/inventorybar.png");

	/**
	 * Those method in which add Image to the Core
	 * 
	 */
	public Inventory() {

		inventory.setImage(inventoryBarImage);
		inventory.setLayoutX(40);
		inventory.setLayoutY(500);
		inventory.setPreserveRatio(true);
		inventory.setFitHeight(300);
		inventory.setFitWidth(500);
		Core.addLayout(inventory);

		sword.setLayoutX(95);
		sword.setLayoutY(600);
		sword.setPreserveRatio(true);
		sword.setFitHeight(70);
		sword.setFitWidth(150);
		sword.setVisible(false);
		sword.setImage(swordImage);
		Core.addLayout(sword);

		healthbag.setLayoutX(150);
		healthbag.setLayoutY(600);
		healthbag.setPreserveRatio(true);
		healthbag.setFitWidth(150);
		healthbag.setFitHeight(70);
		healthbag.setVisible(false);
		healthbag.setImage(healthBagImage);
		Core.addLayout(healthbag);
	}

	/**
	 * this method let chest make chose
	 * 
	 * @return 0,1,2
	 */
	public void chestRoll() {
		chestchose = (int) (Math.ceil(Math.random() * 2));
		
		if (chestchose== 1) {
			sword.setVisible(true);
			Core.setPlayer1Damage(3);
		}
		
		if (chestchose == 2) {
			healthbag.setVisible(true);
		}
	}
	
	public void setHealthVis(boolean vis) {
		healthbag.setVisible(vis);
	}
	
	public boolean getHealthVis() {
		return healthbag.isVisible();
	}
	
}

