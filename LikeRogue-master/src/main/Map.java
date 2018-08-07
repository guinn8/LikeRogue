
package main;


//Privacy Leak at line 27
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.*;
import java.io.*;
public class Map {
	int size = 12;
	private static File map1=new File("res/layouts/map1.txt");
	File map2=new File("res/layouts/map2.txt");
	File map3=new File("res/layouts/map3.txt");
	File map4=new File("res/layouts/map4.txt");
	File mapE=new File("res/layouts/EmptyMap");
	ImageView[][] map = new ImageView[size][size];
	public static Image chest = new Image("file:res/sprites/map/Chest.jpg");
	public static Image brick = new Image("file:res/sprites/map/wall3.png");
	public  Image fin = new Image("file:res/sprites/map/X.png");
	public static Group walls = new Group();
	public static Group chests = new Group();
	public static Group fins = new Group();
	Map() {
		Core.layout.getChildren().add(walls);
		Core.layout.getChildren().add(chests);
		Core.layout.getChildren().add(fins);
	
	}
	
	public void createMap(String layout) throws FileNotFoundException, IllegalArgumentException {
		int posX = 0;
		int posY = 0;
		//File has to stay in the "Main Folder". Cant be in sub folders or packages.
	    File X = null;
	    int random = (int)(Math.random()*4 + 1);
	    if(random == 1) {
	    	X = getMap1();
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
public static void removeMap() throws FileNotFoundException {
	Core.layout.getChildren().remove(fins);
	Core.layout.getChildren().remove(chests);
	Core.layout.getChildren().remove(walls);
	Core.layout.getChildren().add(Core.getmCanvas());
	Core.map1.createMap("void");
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

	public static File getMap1() {
		return map1;
	}

	public void setMap1(File map1) {
		this.map1 = map1;
	}
}