package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * This is the interface for the start screen and the end screen.
 * @author Gavin Guinn
 */
public interface Menus {
	
	
	/**
	 * This is the start method to initialize the start screen.
	 * @return a pane for the start screen.
	 */
	public static Pane start(){
		 Image background2 =new Image("file:res/sprites/background/background.jpg");
		 BackgroundSize backSize = new BackgroundSize(1000, 1000, true, true, true, true);
		 BackgroundImage floor = new BackgroundImage(background2, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,backSize);
		 Background background= new Background(floor);
			
		 
		 Pane layout = new Pane();
			
		 
		 VBox vbox= new VBox();
		 vbox.setLayoutX(175);
		 vbox.setLayoutY(50);
		 vbox.setSpacing(20);
		

		 
		 layout.setBackground(background);

		 
		 Text t = new Text();
		 t.setText("like Rogue");
		 t.setStroke(Color.BLUE);  
		 t.setFont(Font.font ("Verdana", 50));
		 t.setFill(Color.RED);
		 vbox.getChildren().add(t);
		 
		 
		 Button start = new Button();
		 start.setText("Start The Game");
		 start.setMinWidth(150);
		 start.setMinHeight(70);
		 start.setStyle("-fx-font: 24 arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: #ff4e4e; -fx-background-radius: 20; ");
		 start.setOnAction(e->{
			Core.setToMain();	
			Core.setMap(0);	
		 });
		 
		 
		 vbox.getChildren().add(start);
			
		 
		 Button button2 = new Button();
		 button2.setText("Leave The Game");
		 button2.setLayoutY(280);
		 button2.setMinHeight(70);
		 button2.setStyle("-fx-font: 24 arial; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #ff4e4e; -fx-background-radius: 20; ");
		 button2.setOnAction( e->{
			Platform.exit();	
		 });
		 
		 
		 vbox.getChildren().add(button2);
			
		 
		 Button load = new Button();
		 load.setText("Load last game");
		 load.setMinWidth(150);
		 load.setMinHeight(70);
		 load.setStyle("-fx-font: 24 arial; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #ff4e4e; -fx-background-radius: 20; ");
		 load.setOnAction( e->{
			try {
				BufferedReader br = new BufferedReader(new FileReader(Core.getSave()));
				Core.setToMain();	
				Core.setMap(Integer.parseInt(br.readLine()));
				br.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
				}
		 });
		 
		 
		 vbox.getChildren().add(load);
		 layout.getChildren().add(vbox);
			
		 
		 return layout;
			
	}

	
	/**
	 * This initializes the end screen.
	 * @param score an integer that increases the more the player deals damage to enemies.
	 * @param message A string that gives the user a message when they reach the end screen.
	 * @return a pane for the end screen
	 */
	public static Pane end(int score, String message) {
		 Image background3 =new Image("file:res/sprites/background/background2.png");
		 BackgroundSize backSize = new BackgroundSize(1000, 1000, true, true, true, true);
		 BackgroundImage floor = new BackgroundImage(background3, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,backSize);
		 Background background= new Background(floor);	
		 Pane layout = new Pane();	
	
		 
         layout.setBackground(background);
    
         
         Text t = new Text();
       	

         t.setText(message);	
       	 t.setStroke(Color.BLACK);  
       	 t.setFill(Color.RED);
       	 t.setFont(Font.font ("Verdana", 50));
       	 t.setLayoutX(80);
       	 t.setLayoutY(200);
       	 layout.getChildren().add(t);
    
       	 
       	 Text s = new Text();
       	 
       	 
       	 s.setText("Your Score is   "+ Integer.toString(score));
       	 s.setStroke(Color.BLACK);  
       	 s.setFill(Color.RED);
       	 s.setFont(Font.font ("Verdana", 50));
       	 s.setLayoutX(80);
       	 s.setLayoutY(300);
       	 
       	 
       	 layout.getChildren().add(s);
	

		 
	     Button button2 = new Button();
		 button2.setText("Leave");
		 button2.setLayoutX(215);
		 button2.setLayoutY(380);
		 button2.setMinWidth(150);
		 button2.setMinHeight(70);
		 button2.setStyle("-fx-font: 24 arial; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #ff4e4e; -fx-background-radius: 20; ");
		 button2.setOnAction( e->{
			 Platform.exit();	
		 });
		
		 
		layout.getChildren().add(button2);	
		return layout;	
	} 

}
