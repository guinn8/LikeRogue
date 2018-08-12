package actors;


import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.*;
//thanks user kemono for the enemy sprite https://opengameart.org/content/monster-sprites
public class Enemy extends Actors {
	
	private Image enemySprite = new Image("file:res/sprites/enemy/skellysprite.png");
	private ImageView enemy = new ImageView(enemySprite);
	
	private Rectangle2D enemyport= new Rectangle2D(0, 0, W, H);
	

	
	public Enemy(int setX, int setY, int setHealth, int setDamage) {
		super(setHealth, setDamage,15,16,0, 4);
		enemy.setScaleX(2.5);
		enemy.setScaleY(2.5);
		enemy.setLayoutX(setX);
		enemy.setLayoutY(setY);
		
		Core.addSolid(enemy);

		enemy.setId("enemy");
		enemy.setViewport(enemyport);
		
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
