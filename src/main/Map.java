package main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.*;

import actors.Enemy;

import java.io.*;

/**
 * This class deals with the map
 *
 * @author Eric Zhang
 * @author Gavin Guinn
 * @author Johnny Meng
 */
public class Map {
	public static Map[] progress = new Map[4];
	private static int mapNum=0;
	
	static {
		progress[0]= new Map(new File("res/layouts/map0.txt"));
		progress[1]= new Map(new File("res/layouts/map1.txt"));
		progress[2]= new Map(new File("res/layouts/map2.txt"));
		progress[3]= new Map(new File("res/layouts/map3.txt"));
	}
	
	private int playerX;
	private int playerY;
	
	private static final int size = 12;
	private static final int tileSize = 50;
	private ImageView[][] map = new ImageView[size][size];
	private Image chestImage = new Image("file:res/sprites/map/chest.png");
	private Image brickImage = new Image("file:res/sprites/map/wall3.png");
	private Image finishImage = new Image("file:res/sprites/map/X.png");
	private File mapFile;
	
	
	/**
	 * Map Constructor
	 * @param setMapFile
	 */
	public Map(File setMapFile) {
		mapFile=setMapFile;	
	}
	
	
	/**
	 * This creates the map from a text file
	 * @return ArrayList<Enemy> is an arraylist that has the enemy objects.
	 */
	protected  ArrayList<Enemy> createMap()  {
		ArrayList<Enemy>enemyArray= new ArrayList<Enemy>();
		int posX = 0;
		int posY = 0;
	
		Integer enemyNum = 0;
		Scanner mapMaker = null;
		try {
			mapMaker = new Scanner(mapFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String mapLayout = "";
		while(mapMaker.hasNextLine())
		mapLayout = mapLayout + mapMaker.nextLine(); 
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
			}   else if (layout.charAt(i) == '!') {
				map[posX][posY] = new ImageView();
				map[posX][posY].setImage(chestImage);
				map[posX][posY].setFitHeight(tileSize);
				map[posX][posY].setFitWidth(tileSize);
				map[posX][posY].setLayoutX(posX * tileSize);
				map[posX][posY].setLayoutY(posY * tileSize);
				map[posX][posY].setId("chest");
				Core.addSolid(map[posX][posY]);
			}	else if (layout.charAt(i) == 'X') {
				map[posX][posY] = new ImageView();
				map[posX][posY].setImage(finishImage);
				map[posX][posY].setFitHeight(tileSize);
				map[posX][posY].setFitWidth(tileSize);
				map[posX][posY].setLayoutX(posX * tileSize);
				map[posX][posY].setLayoutY(posY * tileSize);
				map[posX][posY].setId("finish");
				Core.addSolid(map[posX][posY]);	
			}else if (layout.charAt(i) == 'P') {
				playerX=(posX * tileSize+1);
				playerY=(posY * tileSize+1);
			}else if (layout.charAt(i) == 'E') {
				Enemy e=new Enemy((posX * tileSize +1), (posY * tileSize) +1, 10, 2);
				enemyArray.add(e);
				enemyNum++;
			}
		}
	mapMaker.close();
	return enemyArray;
	}


	/** 
	 * This moves the enemies that are currently alive to the player's location.
	 * @param pX is the player's x coordinate
	 * @param is the player's y coordinate
	 */
	public void moveEnemys() {
		for (Enemy e: enemyArray) {
			e.move(Core.getPlayerX(),Core.getPlayerY());
		}
	}
	
	/**
	 * Handles collision detection between enemies and walls.
	 */
	public void checkEnemys() {
		for (Enemy e: enemyArray) Core.checkCollision(e);
		
	}
  
	/**
	 * Handles collision detection between enemies
	 * @return true if the enemy has another enemy in its way
   */

	/**
	 * This will remove the current map.
	 */
	protected void removeMap() {
		for(ImageView[] lists:map) {
			for(ImageView item:lists) {
				Core.removeSolid(item);
			}
			for (Enemy e: Core.enemyArray) {
				e.remove();
			}
		}
	}
	
	
	/**
	 * Getter for PX
	 * @return playerX
	 */
	public int getPX(){
		return playerX;
	}
	
	
	/**
	 * Setter for PY
	 * @return playerY
	 */
	public int getPY(){
		return playerY;
	}

	/**
	 * @return the mapNum
	 */
	public static int getMapNum() {
		return mapNum;
	}

	/**
	 * @param mapNum the mapNum to set
	 */
	public static void setMapNum(int mapNum) {
		Map.mapNum = mapNum;
	}
	
}
