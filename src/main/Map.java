package main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.*;

import actors.Enemy;

import java.io.*;

public class Map {
	private int playerX;
	private int playerY;
	private static final int size = 12;
	private static final int tileSize = 50;
	private ImageView[][] map = new ImageView[size][size];
	
	private ArrayList<Enemy>enemyArray= new ArrayList<Enemy>();
	
	
	private Image chestImage = new Image("file:res/sprites/map/chest.png");
	private Image brickImage = new Image("file:res/sprites/map/wall3.png");
	private Image finishImage = new Image("file:res/sprites/map/X.png");

	private File mapFile;
	public Map(File setMapFile) {
		mapFile=setMapFile;	
	}
	
	public void createMap() throws FileNotFoundException {
		
		int posX = 0;
		int posY = 0;
	
		Integer enemyNum = 0;
		Scanner mapMaker = new Scanner(mapFile);
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
			else if (layout.charAt(i) == 'P') {
				playerX=(posX * tileSize+1);
				playerY=(posY * tileSize+1);
			}
			
			else if (layout.charAt(i) == 'E') {
				Enemy e=new Enemy((posX * tileSize +1), (posY * tileSize) +1, 10, 2);
				System.out.println(enemyNum);
				//map[posX][posY].setUserData(enemyNum);
			
				//System.out.println(e.getUserData());
				//e.setLastX(posX * tileSize +1);
				//e.setLastY((posY * tileSize) +1);
				enemyArray.add(e);
				
				enemyNum++;

			}

		}
	mapMaker.close();
	}
	
	public void moveEnemys() {
		for (Enemy e: enemyArray) {
			if (e.checkAlive()==true)e.move();
			
		}
	}
	public void checkEnemys() {
		for (Enemy e: enemyArray) Core.check(e);
			
	}
	
	public void removeMap() {
		for(ImageView[] lists:map) {
			for(ImageView item:lists) {
				Core.removeSolid(item);
			}
			for (Enemy e: enemyArray) {
				e.remove();
			}
		}
	}
	
	public boolean eCheck(ImageView i, Enemy en) {
		for (Enemy e: enemyArray) {
			if(e==en)break;
			if(i==e.getImageView()) {
				return true;
			}
		}
		return false;
		
		
	}
	public int getPX(){
		return playerX;
	}
	public int getPY(){
		return playerY;
	}
	

	
}