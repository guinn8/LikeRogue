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
		Core.addLayout(inventory);

		sworda.setLayoutX(95);
		sworda.setLayoutY(600);
		sworda.setPreserveRatio(true);
		sworda.setFitHeight(70);
		sworda.setFitWidth(150);
		sworda.setVisible(false);
		sworda.setImage(sword);
		Core.addLayout(sworda);

		healthbag.setLayoutX(150);
		healthbag.setLayoutY(600);
		healthbag.setPreserveRatio(true);
		healthbag.setFitWidth(150);
		healthbag.setFitHeight(70);
		healthbag.setVisible(false);
		healthbag.setImage(health);
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
			Core.getInventory().sworda.setVisible(true);
			Core.setPlayer1Damage(3);
		}
		
		if (chestchose == 2) {
			Core.getInventory().healthbag.setVisible(true);
		}
	}
	public void setHealthVis(boolean vis) {
		healthbag.setVisible(vis);
	
	}
	
	public boolean getHealthVis() {
		return healthbag.isVisible();
		
	}
	
	public void setSwordVis(boolean vis) {
		if(vis==true)sworda.setVisible(true);
		else if (vis==false)sworda.setVisible(false);
		
	}
}

