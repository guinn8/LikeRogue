package jUnit;


import actors.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Map;

import static org.junit.Assert.*;
import org.junit.Test;


public class ActorsTest{

	@Test
	public void testPlayerConst(){

    	Player p = new Player(250, 250, 300, 2);
        assertEquals("testPlayerConst: health value incorrect",300,p.getHealth());
		
	}


}
