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
	private Group walls = new Group();
	private Group chests = new Group();

	public Map() {
		Core.layout.getChildren().add(getWalls());
		Core.layout.getChildren().add(getChests());
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
				getWalls().getChildren().add(map[posX][posY]);
			}

			else if (layout.charAt(i) == '!') {
				map[posX][posY] = new ImageView();
				map[posX][posY].setImage(chest);
				map[posX][posY].setFitHeight(50);
				map[posX][posY].setFitWidth(50);
				map[posX][posY].setLayoutX(posX * 50);// map[i][j].getLayoutBounds().getWidth());
				map[posX][posY].setLayoutY(posY * 50);// map[i][j].getLayoutBounds().getHeight());
				getChests().getChildren().add(map[posX][posY]);
			}
		}
	}

	/**
	 * @return the chests
	 */
	public Group getChests() {
		return chests;
	}

	/**
	 * @param chests the chests to set
	 */
	public void setChests(Group chests) {
		this.chests = chests;
	}

	/**
	 * @return the walls
	 */
	public Group getWalls() {
		return walls;
	}

	/**
	 * @param walls the walls to set
	 */
	public void setWalls(Group walls) {
		this.walls = walls;
	}
}
