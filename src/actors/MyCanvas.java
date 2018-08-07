package actors;

import main.Core;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyCanvas extends Canvas {

	  private GraphicsContext gc;


	    public MyCanvas(int width, int height) {
	    	super(width,height);
	    	gc=getGraphicsContext2D();
	    	
	        draw(gc);
	    	
	    }
	    public void draw(GraphicsContext gc) {
	  
	    	gc.setFill(Color.RED);
	    	gc.setStroke(Color.RED);
	    	gc.fillRect(0, 0, Core.getPlayer1Health()*30, 10);
	    	gc.strokeRect(0, 0, 300, 10);
	       
	    	
	    
	    
	    	gc.setFill(Color.BLUE);
	    	gc.setStroke(Color.BLUE);
	    	gc.fillRect(300, 0, Core.getEnemy1Health()*30, 10);
	    	gc.strokeRect(300, 0, 300, 10);
	    	
	    	
	    	
	    	
	    }
	   
	    
	  
}
	    

