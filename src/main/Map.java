
package main;




import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.*;
import java.io.*;

public class Map {
	int size = 12;
	File map1=new File("res/layouts/map1.txt");
	File map2=new File("res/layouts/map2.txt");
	File map3=new File("res/layouts/map3.txt");
	File map4=new File("res/layouts/map4.txt");
	
	ImageView[][] map = new ImageView[size][size];
	
	Image chest = new Image("file:res/sprites/map/Chest.jpg");
	Image brick = new Image("file:res/sprites/map/wall3.png");
	Image fin = new Image("file:res/sprites/map/X.png");

	public void createMap(String layout) throws FileNotFoundException {
		int posX = 0;
		int posY = 0;
	
//	    File X;
//	    int random = (int)(Math.random()*4 + 1);
//	    if(random == 1) {
//	    	X = map1;
//	    }
//	    else if(random == 2) {
//	    	X = map2;
//	    }
//	    else if(random == 3) {
//	    	X = map3;
//	    }
//	    else if(random == 4) {
//	    	X = map4;
//	    }
		Scanner mapMaker = new Scanner(map1);//this is done for testing 
		String mapLayout = "";
		while(mapMaker.hasNextLine()){
			mapLayout = mapLayout + mapMaker.nextLine(); 

		}

		layout = mapLayout;

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
				map[posX][posY].setId("wall");
				Core.solid.getChildren().add(map[posX][posY]);
		
			}

			else if (layout.charAt(i) == '!') {
				map[posX][posY] = new ImageView();
				map[posX][posY].setImage(chest);
				map[posX][posY].setFitHeight(50);
				map[posX][posY].setFitWidth(50);
				map[posX][posY].setLayoutX(posX * 50);// map[i][j].getLayoutBounds().getWidth());
				map[posX][posY].setLayoutY(posY * 50);// map[i][j].getLayoutBounds().getHeight());
				map[posX][posY].setId("chest");
				Core.solid.getChildren().add(map[posX][posY]);
		
			}
			
			else if (layout.charAt(i) == 'X') {
				map[posX][posY] = new ImageView();
				map[posX][posY].setImage(fin);
				map[posX][posY].setFitHeight(50);
				map[posX][posY].setFitWidth(50);
				map[posX][posY].setLayoutX(posX * 50);// map[i][j].getLayoutBounds().getWidth());
				map[posX][posY].setLayoutY(posY * 50);// map[i][j].getLayoutBounds().getHeight());
				map[posX][posY].setId("finish");
				Core.solid.getChildren().add(map[posX][posY]);
						
		}
	}
	mapMaker.close();
}

}