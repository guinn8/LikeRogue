package jUnit;


import actors.*;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;


public class ActorsTest{
	@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
	
	
	@Test
	public void testPlayerConst(){

    	Player p = new Player(250, 250, 300, 2);
        assertEquals("testPlayerConst: health value incorrect",300,p.getHealth());
		
	}


}
