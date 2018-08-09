package main;

import javafx.scene.layout.*;

import java.io.File;
import java.io.FileNotFoundException;

import actors.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

/**
 * Haven't found a way to check the boundaries of the window. ~Eric.Z Features
 * that are in here: - Movement with WASD
 *
 * @author Eric Zhang
 * @author Gavin Guinn
 */


public  class Core extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	private static int mapNum=0;
	private static int hitCount=0;
	private static Pane layout = new Pane();
	private static Group solid= new Group();

	private File map0=new File("res/layouts/map0.txt");
	private File map1=new File("res/layouts/map1.txt");
	private File map2=new File("res/layouts/map2.txt");
	private File map3=new File("res/layouts/map3.txt");
	
	private static Map[] progress = new Map[4];

	
	private Image floorImage =new Image("file:res/sprites/map/floor.png");
	private BackgroundSize backSize = new BackgroundSize(10000, 100000, true, true, true, true);
	private BackgroundImage floor = new BackgroundImage(floorImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,backSize);
	private Background background= new Background(floor);
	
	private static Inventory inventory = new Inventory();
	private static Player player1 = new Player(10,5);
	private static Enemy enemy1 = new Enemy(199,-199,10,2);
	
	private static final int WIDTH=600;
	private static final int HEIGHT=680;
	
	private static boolean attack = false;


	@Override
	public void start(Stage stage) throws InterruptedException, FileNotFoundException {
		progress[0]= new Map(map0);
		progress[1]= new Map(map1);
		progress[2]= new Map(map2);
		progress[3]= new Map(map3);
		
		layout.setBackground(background);
		Scene scene = new Scene(layout, WIDTH, HEIGHT);
		stage.setScene(scene);
		
		progress[mapNum].createMap();
		player1.teleport(progress[mapNum].getPX(), progress[mapNum].getPY());
	
		layout.getChildren().add(solid);

		stage.show();

		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.D) {
				player1.setDelta(Actors.MOVERES,0);
				player1.setPlayerRight();
			}

			else if (e.getCode() == KeyCode.A) {
				player1.setDelta(-Actors.MOVERES,0);
				player1.setPlayerLeft();				
			}

			else if (e.getCode() == KeyCode.S) {
				player1.setDelta(0, Actors.MOVERES);
				player1.setPlayerDown();
			}

			else if (e.getCode() == KeyCode.W) {
				player1.setDelta(0, -Actors.MOVERES);
				player1.setPlayerUp();
			}
			
			else if (e.getCode() == KeyCode.H) {
				if (inventory.getHealthVis() == true) {
					player1.setHealth(10);
				}
			}	
			
			else if (e.getCode() == KeyCode.SPACE) {
				attack=true;
			}
		});

		AnimationTimer gameLoop = new AnimationTimer() {
			int timer;
			@Override
			public void handle(long arg0) {
				timer++;
				player1.drawHealthBar() ;
				player1.move();
				
				player1.resetDamage();
				if(attack==true) {
					player1.attack();
					attack=false;
				}
				
				if (timer%5==0) {
					progress[mapNum].moveEnemys();
				
				}
				if (timer==1000)timer=0;
			}
		}; gameLoop.start();
	}
	
	/**
	 * check handles the collision detection
	 * 
	 * @param xDelt distance to check ahead in the x direction
	 * @param yDelt distance to precheck in the y direction
	 * @return false if solid object in the way
	 * @throws FileNotFoundException 
	 **/
	public static boolean check(Actors actor) {
	
		for (Node object : solid.getChildren()) {
			if (object.getBoundsInParent().intersects(actor.getBounds().getMinX() + actor.getDeltaX(), actor.getBounds().getMinY() + actor.getDeltaY(), 
				actor.getBounds().getWidth(), actor.getBounds().getHeight()) && object.getId() != null) {
							
				if (object.getId().equals("wall")) return false;
				
				if  (actor instanceof Player) {
					if (object.getId().equals("enemy")){
						
						//fix thiss!!!
						for(Enemy e: progress[mapNum].enemyArray) {
							if (object==e.getImageView()) {
								hit(actor,e);
							}
						}
						return false;
					}
				}
				
				if(actor instanceof Enemy) {
					if (object.getId().equals("player")){
						hit(actor, player1);
						return false;
					}
				}
				
				if (object.getId().equals("chest")) {
					solid.getChildren().remove(object);
					int roll = (int) (Math.ceil(Math.random() * 2));
					
					if (roll== 1) {
						inventory.setSwordVis(true);
						actor.setDamage(3);
					}
					else if (roll == 2) {
						inventory.setHealthVis(true);
					}
					
					return false;
				}
				
				if (object.getId().equals("finish")) {
					nextMap();
					
					return false;
					
				}
				
				if (object.getId().equals("damage")) {
				
					actor.setHealth(actor.getHealth()-player1.getDamage());
						
					actor.checkAlive();
					return false;
				
				}
			}
		}
			
		return true;
	}

	/**
	 * 
	 * @param actor1
	 * @param actor2
	 */
	public static void hit(Actors actor1, Actors actor2) {
		hitCount++;
		if (hitCount==100) {
			actor1.setHealth(actor1.getHealth()-actor2.getDamage());
			actor2.setHealth(actor2.getHealth()-actor1.getDamage());
			hitCount=0;
		}
		actor1.checkAlive();
		actor2.checkAlive();
	}
	
	
	public static void nextMap() {
		
		mapNum++;
		if(mapNum<progress.length) {
			try {
				
				progress[mapNum].createMap();
				player1.teleport(progress[mapNum].getPX(), progress[mapNum].getPY());
				
				System.out.println(progress[mapNum].getPX());
			} catch (FileNotFoundException e) {
	
				e.printStackTrace();
			}
			progress[mapNum-1].removeMap();
		}
	}
	
	public static void addLayout(Node n) {
		layout.getChildren().add(n);
	}
	public static void addSolid(Node n) {
		solid.getChildren().add(n);
	}
	public static void removeSolid(Node n) {
		solid.getChildren().remove(n);
	}
	
}