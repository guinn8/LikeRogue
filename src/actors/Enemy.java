package actors;


import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.*;
//thanks user kemono for the enemy sprite https://opengameart.org/content/monster-sprites
public class Enemy extends Actors {
	
	private Image enemySprite = new Image("file:res/sprites/enemy/skellysprite.png");
	private ImageView enemy = new ImageView();
	
	boolean right=true;
	
	public Enemy(int setX, int setY, int setHealth, int setDamage) {
		super(setHealth, setDamage);
		enemy.setLayoutX(setX);
		enemy.setLayoutY(setY);
		enemy.setImage(enemySprite);
		Core.addSolid(enemy);

		enemy.setId("enemy");
		
	}


	public int move() {
		if (this.checkAlive()==true) {
			double pX=Core.getPlayer1().getX();
			double pY=Core.getPlayer1().getY();
		
			double eX=this.getX();
			double eY=this.getY();
		
			double vX= pX-eX;
			double vY= pY-eY;
			double len= Math.sqrt(vX*vX+vY*vY);
			vX=(vX/len);
			vY=(vY/len);
			
			//System.out.println("dX: "+vX+" dY: "+vY);
			this.setDelta(vX, vY);
			if(Core.check(this)==true)super.move();
		}
		return 0;

	}

	@Override
	public Bounds getBounds() {
		return enemy.getBoundsInParent();
	}

	@Override
	public ImageView getImageView() {
		return enemy;
	}
	
	public void setUserData(Integer num) {
		enemy.setUserData(num);
	}
	public int getUserData() {
		return (int) enemy.getUserData();
	}
}
