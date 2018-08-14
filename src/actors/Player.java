package actors;

import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.*;
//thanks user https://opengameart.org/users/erbarlow for player sprites

//Erics changes: added getters and setters for every instance variables.
public class Player extends Actors {
	

	//private ImageView player= new ImageView(soldierImage);
	
	
	private Rectangle2D soldierPort;
	
	
	private ImageView damage = new ImageView(new Image("file:res/sprites/player/damage.png"));
	private ImageView player = new ImageView(new Image("file:res/sprites/player/soldier.png")); 
//	private Image playerRight = new Image("file:res/sprites/player/linkRight.png");
//	private Image playerLeft = new Image("file:res/sprites/player/linkLeft.png");
//	private Image playerDown = new Image("file:res/sprites/player/linkDown.png");
//	private Image playerUp = new Image("file:res/sprites/player/linkUp.png");
	
	
	private boolean attacking=false;
	/**
	 * 
	 * @param setX
	 * @param setY
	 * @param setHealth
	 * @param setDamage
	 */
	public Player(int setHealth, int setDamage){
		super(setHealth, setDamage,32,32,0,3);
		soldierPort= new Rectangle2D(0, 0, getW(), getH());
		
		
		/*
		soldier.setLayoutX(200);
		soldier.setLayoutY(150);*/
	
		//Core.addSolid(soldier);

		
		damage.setId("damage");
		damage.setFitHeight(getH());
		damage.setFitWidth(getW());
	
		Core.addSolid(damage);
		

		//player.setImage(playerDown);
		player.setViewport(soldierPort);
		
		player.setId("player");
		Core.addSolid(player);
	}

	/**
	 * 
	 */
	public boolean attack() {
		
			//up
			if (getDirection()==3) {
				damage.setLayoutX(player.getLayoutX());
				damage.setLayoutY(player.getLayoutY() - getH()-2);
			}
			//down
			else if (getDirection()==0) {
				damage.setLayoutX(player.getLayoutX());
				damage.setLayoutY(player.getLayoutY()+getH()+2);
			}
			//left
			else if (getDirection()==1) {
				damage.setLayoutX(player.getLayoutX() - getW()-2);
				damage.setLayoutY(player.getLayoutY());
			}
			//right
			else if (getDirection()==2) {
				damage.setLayoutX(player.getLayoutX()+getW()+2);
				damage.setLayoutY(player.getLayoutY());
			}
	return false;
	}
	


	public void resetDamage() {
		damage.setLayoutX(-1000);
		damage.setLayoutY(-1000);
	}
	
	public void setPlayerRight() {
		//player.setImage(playerRight);
	}
	public void setPlayerLeft() {
		//player.setImage(playerLeft);
	}
	public void setPlayerDown() {
		//player.setImage(playerDown);
	}
	public void setPlayerUp() {
		//player.setImage(playerUp);
	}
	public Bounds getBounds() {
		return player.getBoundsInParent();
	}
	//possible privacy leak
	protected ImageView getImageView() {
		return player;	
	}
//	int counter=0;
//	public void animate(int r) {
//		
//			Rectangle2D anim= new Rectangle2D(OFF+W*counter, r*H, W, H);
//			player.setViewport(anim);

	@Override
	public boolean attack(int dir) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the attacking
	 */
	public boolean isAttacking() {
		return attacking;
	}

	/**
	 * @param attacking the attacking to set
	 */
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	@Override
	public void drawHealthBar() {
		// TODO Auto-generated method stub
		
	}

//			counter++;
//			if (counter==3)counter=0;
//		
//		
//	}
//	
//	public void move() {
//		for (int i = 0; i < 10; i++) {
//			if (Core.check(this)) {
//				
//				
//				
//			}
//		}
//		setDelta(0,0);
//	}
	}

