package actors;





import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.*;

//Eric's changes: Added getter and setter to Imageview enemy, hostileG.
public class Enemy extends Actors {
	private Group hostiles = new Group();
	private Image enemySprite = new Image("file:res/sprites/enemy/frown.png");
	private ImageView enemy = new ImageView();
	boolean right=true;

	public Enemy(int setX, int setY, int setHealth, int setDamage) {
		super(setX, setY, setHealth, setDamage);
		getEnemy().setLayoutX(setX);
		getEnemy().setLayoutY(setY);
		getEnemy().setImage(enemySprite);
		getHostileG().getChildren().add(enemy);
		Core.layout.getChildren().add(hostiles);

	}

	public ImageView getEnemy() {
		return enemy;
	}

	public void setEnemy(ImageView enemy) {
		this.enemy = enemy;
	}

	public Group getHostileG() {
		return hostiles;
	}

	public void setHostileG(Group hostileG) {
		this.hostiles = hostileG;
	}

	@Override
	public Bounds getBounds() {
		return enemy.getBoundsInParent();
	}

	@Override
	public ImageView getImageView() {
		return enemy;
	}

	public void enemyMove(Enemy e) {
		
	   // Random r = new Random();
	    
	    //int x= r.nextInt(3)-1;
	    //int y= r.nextInt(3)-1;
	    
	 
	
		
			//System.out.println("right true");
			//System.out.println();
		
		if (right==true) {
			 move(e,-1,0);
			 System.out.println("move(e,-1,0)");
			 if (move(e,-1,0)==true)right=false;	 
				
		
		}
		else {
			
			System.out.println("move(e,1,0)");
			move(e,1,0);
			if (move(e,1,0)==true)right=true;	
			
			
		}
	
	}

}
