package actors;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.*;


/**
 * This class deals with the inventory system and creates the items.
 * @author zhaoning meng
 */
public class Inventory {
	
	private ImageView sword = new ImageView();
	final private Image swordImage = new Image("file:res/sprites/inventory/sword.jpg");
	
	private ImageView sword2 = new ImageView();
	final private Image sword2Image = new Image("file:res/sprites/inventory/sword2.jpg");
	
	private ImageView sword3 = new ImageView();
	final private Image sword3Image = new Image("file:res/sprites/inventory/sword3.jpg");
	
	private ImageView sword4 = new ImageView();
	final private Image sword4Image = new Image("file:res/sprites/inventory/sword4.jpg");

	private ImageView healthbag = new ImageView();
	final private Image healthBagImage = new Image("file:res/sprites/inventory/health.png");

	private ImageView inventory = new ImageView();
	final private Image inventoryBarImage = new Image("file:res/sprites/inventory/inventorybar.png");
	
	private Rectangle white= new Rectangle();
	
	
	/**
	 * This method adds the images to Core
	 */
	public Inventory() {

		white.setX(0);
		white.setY(600);
		white.setWidth(600);
		white.setHeight(80);
		white.setFill(Color.WHITE);
		Core.addLayout(white);

		inventory.setImage(inventoryBarImage);
		inventory.setLayoutX(40);
		inventory.setLayoutY(506);
		inventory.setPreserveRatio(true);
		inventory.setFitHeight(300);
		inventory.setFitWidth(500);
		Core.addLayout(inventory);

		sword.setLayoutX(85);
		sword.setLayoutY(600);
		sword.setPreserveRatio(false);
		sword.setFitHeight(50);
		sword.setFitWidth(60);
		sword.setVisible(false);
		sword.setImage(swordImage);
		Core.addLayout(sword);
		
		
		sword2.setLayoutX(150);
		sword2.setLayoutY(600);
		sword2.setPreserveRatio(false);
		sword2.setFitHeight(50);
		sword2.setFitWidth(60);
		sword2.setVisible(false);
		sword2.setImage(sword2Image);
		Core.addLayout(sword2);
		
		sword3.setLayoutX(220);
		sword3.setLayoutY(600);
		sword3.setPreserveRatio(false);
		sword3.setFitHeight(50);
		sword3.setFitWidth(60);
		sword3.setVisible(false);
		sword3.setImage(sword3Image);
		Core.addLayout(sword3);
		
		sword4.setLayoutX(285);
		sword4.setLayoutY(600);
		sword4.setPreserveRatio(false);
		sword4.setFitHeight(50);
		sword4.setFitWidth(60);
		sword4.setVisible(false);
		sword4.setImage(sword4Image);
		Core.addLayout(sword4);
		

		healthbag.setLayoutX(420);
		healthbag.setLayoutY(600);
		healthbag.setPreserveRatio(true);
		healthbag.setFitWidth(160);
		healthbag.setFitHeight(60);
		healthbag.setVisible(false);
		healthbag.setImage(healthBagImage);
		Core.addLayout(healthbag);
	}
	
	
	/**
	 * Setter for health 
	 * @param vis sets true to allow object to be visible.
	 */
	public void setHealthVis(boolean vis) {
		healthbag.setVisible(vis);
	}
	
	
	/**
	 * Setter for Sword 
	 * @param vis sets true to allow object to be visible.
	 */
	public void setSwordVis(boolean vis) {
		sword.setVisible(vis);
	}
	
	
	/**
	 * Setter for Sword2 
	 * @param vis sets true to allow object to be visible.
	 */
	public void setSword2Vis(boolean vis) {
		sword2.setVisible(vis);
	}
	
	
	/**
	 * Setter for Sword3 
	 * @param vis sets true to allow object to be visible.
	 */
	public void setSword3Vis(boolean vis) {
		sword3.setVisible(vis);
	}
	
	
	/**
	 * Setter for Sword4 
	 * @param vis sets true to allow object to be visible.
	 */
	public void setSword4Vis(boolean vis) {
		sword4.setVisible(vis);
	}
	
	
	/**
	 * Getter for health 
	 * @return vis returns true if object is visible, false if not visible.
	 */
	public boolean getHealthVis() {
		return healthbag.isVisible();
	}
	
}


