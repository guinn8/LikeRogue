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
	private static int mapNum=0;
	private int playerStartX;
	private int playerStartY;
	private static final int size = 12;
	private static final int tileSize = 50;
	
	private ImageView[][] map = new ImageView[size][size];//array of imageview objects that constitute the map
	private ArrayList<Enemy>enemyArray= new ArrayList<Enemy>();// array of all enemys created by the map file
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
	 * 
	 */
	protected void createMap()  {
		int arrayX = 0;
		int arrayY = 0;
		
		String mapLayout = ""; //String that repersents the map
	
		//trys to create a string from the map file
		try {
			Scanner mapMaker = new Scanner(mapFile);
			while(mapMaker.hasNextLine()) mapLayout = mapLayout + mapMaker.nextLine(); //loops through the file and appends the lines to a string
			mapMaker.close();
		} catch (FileNotFoundException e1) {
			System.out.println("could not find map file");
		}
		
		
		//this block parses the string and appends it to a square 2d array 
		for (int i = 0; i < mapLayout.length(); i++) {
			if (i % size == 0 && i != 0) {//i!=0 because i dont want it to increment y on 0
				arrayY++;
				arrayX = 0;
			} 
			else if (i != 0)arrayX++;
			
			//walls
			if (mapLayout.charAt(i) == '#') {
				map[arrayX][arrayY] = new ImageView(new Image("file:res/sprites/map/wall3.png"));
				map[arrayX][arrayY].setLayoutX(arrayX * tileSize);
				map[arrayX][arrayY].setLayoutY(arrayY * tileSize);
				map[arrayX][arrayY].setId("wall");
				Core.addSolid(map[arrayX][arrayY]);
			}
			
			//chest
			else if (mapLayout.charAt(i) == '!') {
				map[arrayX][arrayY] = new ImageView(new Image("file:res/sprites/map/chest.png"));
				map[arrayX][arrayY].setFitHeight(tileSize);
				map[arrayX][arrayY].setFitWidth(tileSize);
				map[arrayX][arrayY].setLayoutX(arrayX * tileSize);
				map[arrayX][arrayY].setLayoutY(arrayY * tileSize);
				map[arrayX][arrayY].setId("chest");
				Core.addSolid(map[arrayX][arrayY]);
			}
			//finish
			else if (mapLayout.charAt(i) == 'X') {
				map[arrayX][arrayY] = new ImageView(new Image("file:res/sprites/map/X.png"));
				map[arrayX][arrayY].setFitHeight(tileSize);
				map[arrayX][arrayY].setFitWidth(tileSize);
				map[arrayX][arrayY].setLayoutX(arrayX * tileSize);
				map[arrayX][arrayY].setLayoutY(arrayY * tileSize);
				map[arrayX][arrayY].setId("finish");
				Core.addSolid(map[arrayX][arrayY]);	
			}
			//place to teleport the player to 
			else if (mapLayout.charAt(i) == 'P') {
				playerStartX=(arrayX * tileSize+1);
				playerStartY=(arrayY * tileSize+1);
			}
			//place to start the enemys
			else if (mapLayout.charAt(i) == 'E') {
				Enemy e=new Enemy((arrayX * tileSize +1), (arrayY * tileSize) +1, 10, 2);
				enemyArray.add(e);
			}
		}
	
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
	public boolean enemyEnemyCollis(ImageView i, Enemy check) {
		for (Enemy e: enemyArray) {
			if(i==e.getImageView() && e!=check) return true;// checks to see if imageview i is equal to a specific enemy and checks to make sure that enemy is not equal  the the enemy that the colision is being checked on 
		}
		return false;
	}
	
	
	/**
	 * This will remove the current map.
	 */
	protected void removeMap() {
		for(ImageView[] lists:map) {//loops through the list and removes all the items
			for(ImageView item:lists) {
				Core.removeSolid(item);
			}
			
			for (Enemy e: enemyArray) {
				e.remove();
			}
		}
	}
	/**
	 * Getter for PX
	 * @return playerX
	 */
	public int getPX(){
		return playerStartX;
	}
	/**
	 * Setter for PY
	 * @return playerY
	 */
	public int getPY(){
		return playerStartY;
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