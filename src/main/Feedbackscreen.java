package main;

import javafx.application.Platform;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This is the game over screen for the game. it will pop up when the player dies
 * or when the player successfully completes the game.
 * @author Eric Zhang
 * @author Gavin Guinn
 * @author Johnny Meng
 *
 */
public class Feedbackscreen  {

	private Image background3 =new Image("file:res/sprites/background/background2.png");
	private BackgroundSize backSize = new BackgroundSize(1000, 1000, true, true, true, true);
	private BackgroundImage floor = new BackgroundImage(background3, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,backSize);
	private Background background= new Background(floor);	
	private static Pane layout = new Pane();	
	private int Score=0;
	public Pane end() {
	Score=Core.getPlayer1().getDamage();
  
    layout.setBackground(background);
    
    
    
    Text t = new Text();
 
	t.setText("Thanks for Playing");	
    
  

	t.setStroke(Color.BLACK);  
	t.setFill(Color.RED);
	
    t.setFont(Font.font ("Verdana", 50));
	t.setLayoutX(80);
    t.setLayoutY(200);
    layout.getChildren().add(t);
    
    Text s = new Text();
    s.setText("Your Score is   "+ Integer.toString(Score));
	
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
		button2.setOnAction(e->{
			Platform.exit();
	});
		layout.getChildren().add(button2);	
		return layout;	
}
}
		