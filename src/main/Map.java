package main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.util.*;
import java.io.*;

public class Map {
	
	private static final int size = 12;
	private static final int tileSize = 50;
	private ImageView[][] map = new ImageView[size][size];
	
	private File map1=new File("res/layouts/map1.txt");
	private File map2=new File("res/layouts/map2.txt");
	private File map3=new File("res/layouts/map3.txt");
	private File map4=new File("res/layouts/map4.txt");
	
	
	private Image chestImage = new Image("file:res/sprites/map/chest.png");
	private Image brickImage = new Image("file:res/sprites/map/wall3.png");
	private Image finishImage = new Image("file:res/sprites/map/X.png");

	
	public void createMap() throws FileNotFoundException {
		
		int posX = 0;
		int posY = 0;
	
	    File X=null;
	    int random = (int)(Math.random()*4 + 1);
	    if(random == 1) {
	    	X = map1;
	    }
	    else if(random == 2) {
	    	X = map2;
	    }
	    else if(random == 3) {
	    	X = map3;
	    }
	    else if(random == 4) {
	    	X = map4;
	    }
		
		Scanner mapMaker = new Scanner(X);
		String mapLayout = "";
		while(mapMaker.hasNextLine()) mapLayout = mapLayout + mapMaker.nextLine(); 
		
		String layout = mapLayout;

		for (int i = 0; i < layout.length(); i++) {
			if (i % size == 0 && i != 0) {
				posY++;
				posX = 0;
			} 
			else if (i != 0)posX++;
		
			if (layout.charAt(i) == '#') {
				map[posX][posY] = new ImageView();
				map[posX][posY].setImage(brickImage);
				map[posX][posY].setLayoutX(posX * tileSize);
				map[posX][posY].setLayoutY(posY * tileSize);
				map[posX][posY].setId("wall");
				Core.addSolid(map[posX][posY]);
			}

			else if (layout.charAt(i) == '!') {
				map[posX][posY] = new ImageView();
				map[posX][posY].setImage(chestImage);
				map[posX][posY].setFitHeight(tileSize);
				map[posX][posY].setFitWidth(tileSize);
				map[posX][posY].setLayoutX(posX * tileSize);
				map[posX][posY].setLayoutY(posY * tileSize);
				map[posX][posY].setId("chest");
				Core.addSolid(map[posX][posY]);
			}
			
			else if (layout.charAt(i) == 'X') {
				map[posX][posY] = new ImageView();
				map[posX][posY].setImage(finishImage);
				map[posX][posY].setFitHeight(tileSize);
				map[posX][posY].setFitWidth(tileSize);
				map[posX][posY].setLayoutX(posX * tileSize);
				map[posX][posY].setLayoutY(posY * tileSize);
				map[posX][posY].setId("finish");
				Core.addSolid(map[posX][posY]);	
			}
		}
	mapMaker.close();
	}
}