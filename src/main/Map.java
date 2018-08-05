
package main;


//Privacy Leak at line 27
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.*;
import java.io.*;
public class Map {
	int size = 12;
	File map1=new File("res/layouts/map1.txt");
	ImageView[][] map = new ImageView[size][size];
	Image chest = new Image("file:res/sprites/map/Chest.jpg");
	Image brick = new Image("file:res/sprites/map/wall3.png");
	Image fin = new Image("file:res/map/sprites/X.png");
	Group walls = new Group();
	Group chests = new Group();
	Group fins = new Group();
	Map() {
		Core.layout.getChildren().add(walls);
		Core.layout.getChildren().add(chests);
		//Core.layout.getChildren().add(fins);
	}
	
	public void createMap(String layout) throws FileNotFoundException {
		int posX = 0;
		int posY = 0;
		//File has to stay in the "Main Folder". Cant be in sub folders or packages.
	    Scanner mapMaker = new Scanner(map1);
		String mapLayout = "";
		while(mapMaker.hasNextLine()){
			mapLayout = mapLayout + mapMaker.nextLine(); 

		}

		layout = mapLayout;
		//System.out.println(layout);
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
			
			else if (layout.charAt(i) == 'X') {
				map[posX][posY] = new ImageView();
				map[posX][posY].setImage(fin);
				map[posX][posY].setFitHeight(50);
				map[posX][posY].setFitWidth(50);
				map[posX][posY].setLayoutX(posX * 50);// map[i][j].getLayoutBounds().getWidth());
				map[posX][posY].setLayoutY(posY * 50);// map[i][j].getLayoutBounds().getHeight());
				fins.getChildren().add(map[posX][posY]);			
		}
	}
	mapMaker.close();
}

	public Group getWalls() {
	
		return walls;
	}

	public Group getChests() {
		// TODO Auto-generated method stub
		return chests;
	}

	public Group getFins() {
		// TODO Auto-generated method stub
		return fins;
	}
}