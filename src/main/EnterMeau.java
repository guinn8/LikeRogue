package main;

import java.io.FileNotFoundException;


import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EnterMeau extends Application{
	private static final int WIDTH=600;
	private static final int HEIGHT=680;
	private Image background2 =new Image("file:res/sprites/background/background.jpg");
	private BackgroundSize backSize = new BackgroundSize(1000, 1000, true, true, true, true);
	private BackgroundImage floor = new BackgroundImage(background2, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,backSize);
	private Background background= new Background(floor);
	
	public static void main(String[] args) {
		launch(args);
	}
	private static Pane layout = new Pane();
	public void start(Stage stage) throws InterruptedException, FileNotFoundException {
		stage.setTitle("LikeRogue");
		Scene scene = new Scene(layout, WIDTH, HEIGHT);
		stage.setScene(scene);
		stage.show();
		layout.setBackground(background);
		
		Text t = new Text();
		t.setText("like Rogue");
		t.setStroke(Color.BLUE);  
		t.setFont(Font.font ("Verdana", 50));
		t.setFill(Color.RED);
		t.setLayoutX(80);
	    t.setLayoutY(100);
	    layout.getChildren().add(t);
	  		 
		  
		
		Button button = new Button();
		button.setText("Start The Game");
		button.setLayoutX(80);
		button.setLayoutY(180);
		button.setMinWidth(150);
		button.setMinHeight(70);
		button.setStyle("-fx-font: 24 arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: #ff4e4e; -fx-background-radius: 20; ");
		button.setOnAction(e->{
			
			
			
			
			
		});
		layout.getChildren().add(button);
		
		Button button2 = new Button();
		button2.setText("Leave The Game");
		button2.setLayoutX(80);
		button2.setLayoutY(280);
		button2.setMinWidth(150);
		button2.setMinHeight(70);
		button2.setStyle("-fx-font: 24 arial; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #ff4e4e; -fx-background-radius: 20; ");
		button2.setOnAction( e->{
		
			
			
			
			
			
		});
		layout.getChildren().add(button2);
		
		
		
}
}
