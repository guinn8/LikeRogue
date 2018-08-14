package main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 *this interface handles the GUI elements relating to gameplay like the health bar and sword.
 * @author zhaoning meng
 */
public interface GameGUI {
	 ImageView sword = new ImageView(new Image("file:res/sprites/inventory/sword.jpg"));
	 ImageView sword2 = new ImageView(new Image("file:res/sprites/inventory/sword2.jpg"));
	 ImageView sword3 = new ImageView(new Image("file:res/sprites/inventory/sword3.jpg"));
	 ImageView sword4 = new ImageView(new Image("file:res/sprites/inventory/sword4.jpg"));
	 ImageView healthbag = new ImageView(new Image("file:res/sprites/inventory/health.png"));
	 ImageView inventory = new ImageView(new Image("file:res/sprites/inventory/inventorybar.png"));
	 Rectangle healthBar= new Rectangle();
	 Rectangle hbOutline= new Rectangle();	
	 Rectangle white= new Rectangle();
	/**
	 * this method creates the GUI objects required
	 * @return a root pane with gui objects in it
	 */
	public static  Pane Inventory() {
		Pane gui= new Pane();
			
		white.setX(0);
		white.setY(600);
		white.setWidth(600);
		white.setHeight(80);
		white.setFill(Color.WHITE);
		gui.getChildren().add(white);

		inventory.setLayoutX(40);
		inventory.setLayoutY(506);
		inventory.setPreserveRatio(true);
		inventory.setFitHeight(300);
		inventory.setFitWidth(500);
		gui.getChildren().add(inventory);
		
		sword.setLayoutX(85);
		sword.setLayoutY(600);
		sword.setPreserveRatio(false);
		sword.setFitHeight(50);
		sword.setFitWidth(60);
		sword.setVisible(false);
		gui.getChildren().add(sword);
			
		sword2.setLayoutX(150);
		sword2.setLayoutY(600);
		sword2.setPreserveRatio(false);
		sword2.setFitHeight(50);
		sword2.setFitWidth(60);
		sword2.setVisible(false);	
		gui.getChildren().add(sword2);
		
		sword3.setLayoutX(220);
		sword3.setLayoutY(600);
		sword3.setPreserveRatio(false);
		sword3.setFitHeight(50);
		sword3.setFitWidth(60);
		sword3.setVisible(false);		
		gui.getChildren().add(sword3);
		
		sword4.setLayoutX(285);
		sword4.setLayoutY(600);
		sword4.setPreserveRatio(false);
		sword4.setFitHeight(50);
		sword4.setFitWidth(60);
		sword4.setVisible(false);	
		gui.getChildren().add(sword4);
		
		healthbag.setLayoutX(420);
		healthbag.setLayoutY(600);
		healthbag.setPreserveRatio(true);
		healthbag.setFitWidth(160);
		healthbag.setFitHeight(60);
		healthbag.setVisible(false);	
		gui.getChildren().add(healthbag);
			
		hbOutline.setX(0);
		hbOutline.setY(660);
		hbOutline.setWidth(600);
		hbOutline.setHeight(20);
		gui.getChildren().add(hbOutline);
		
		healthBar.setX(0);
		healthBar.setY(660);
		healthBar.setWidth(600);
		healthBar.setHeight(20);
		healthBar.setFill(Color.RED);
		gui.getChildren().add(healthBar);
		
		return gui;	
	}
	
	
	/**
	 * redraws the healthbar based on the health passed to it
	 * @param health the health bar is drawn in proporton to health
	 */
	public static void drawHealthBar(int health) {
		healthBar.setWidth(health*60);
	}
	
	
	/**
	 * Setter for health 
	 * @param vis sets true to allow object to be visible.
	 */
	public static void setHealthVis(boolean vis) {
		healthbag.setVisible(vis);
	}
	
	
	/**
	 * Setter for Sword 
	 * @param vis sets true to allow object to be visible.
	 */
	public static void setSwordVis(boolean vis) {
		sword.setVisible(vis);
	}
	
	
	/**
	 * Setter for Sword2 
	 * @param vis sets true to allow object to be visible.
	 */
	public static void setSword2Vis(boolean vis) {
		sword2.setVisible(vis);
	}
	
	
	/**
	 * Setter for Sword3 
	 * @param vis sets true to allow object to be visible.
	 */
	public static void setSword3Vis(boolean vis) {
		sword3.setVisible(vis);
	}
	
	
	/**
	 * Setter for Sword4 
	 * @param vis sets true to allow object to be visible.
	 */
	public static void setSword4Vis(boolean vis) {
		sword4.setVisible(vis);
	}
	
	
	/**
	 * Getter for health 
	 * @return vis returns true if object is visible, false if not visible.
	 */
	public static boolean getHealthVis() {
		return healthbag.isVisible();
	}
}

