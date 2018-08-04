package main;
//import PlayerVSEnemy.*;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Map {
	int size = 12;
	ImageView[][] map = new ImageView[size][size];
	Image chest = new Image("file:data/map/Chest.jpg");
	Image brick = new Image("file:data/map/wall3.png");
	Group walls = new Group();
	Group chests = new Group();

	Map() {
		Core.layout.getChildren().add(walls);
		Core.layout.getChildren().add(chests);
	}

	public void createMap(String layout) {
		int posX = 0;
		int posY = 0;

		layout =  "############" 
				+ "#          #" 
				+ "########## #" 
				+ "#     #    #" 
				+ "# # ! #    #"
				+ "# #####    #"
				+ "#          #"
				+ "#          #"
				+ "#          #"
				+ "#          #"
				+ "#          #"
				+ "############";

		for (int i = 0; i < layout.length(); i++) {

			if (i % size == 0 && i != 0) {
				posY++;
				posX = 0;
			} else if (i != 0)
				posX++;

			if (layout.charAt(i) == '#') {
				map[posX][posY] = new ImageView();
				map[posX][posY].setImage(brick);
				map[posX][posY].setLayoutX(posX * 50);// map[i][j].getLayoutBounds().getWidth());
				map[posX][posY].setLayoutY(posY * 50);// map[i][j].getLayoutBounds().getHeight());
				walls.getChildren().add(map[posX][posY]);
			}

			else if (layout.charAt(i) == '!') {
				map[posX][posY] = new ImageView();
				map[posX][posY].setImage(chest);
				map[posX][posY].setFitHeight(50);
				map[posX][posY].setFitWidth(50);
				map[posX][posY].setLayoutX(posX * 50);// map[i][j].getLayoutBounds().getWidth());
				map[posX][posY].setLayoutY(posY * 50);// map[i][j].getLayoutBounds().getHeight());
				chests.getChildren().add(map[posX][posY]);
			}
		}
	}
}
