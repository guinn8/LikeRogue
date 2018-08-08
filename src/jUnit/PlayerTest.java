package jUnit;


import actors.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;


public class PlayerTest{
	@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
	
	@Test
	public void testPlayerHealth(){

    	Player p = new Player(250, 250, 300, 2);
        assertEquals("testPlayerConst: health value incorrect",300,p.getHealth());
	}
	
	@Test
	public void testPlayerDam() {
		Player p = new Player(250,250,300,0);
		assertEquals("testPlayerDam: damge value incorrect",0,p.getDamage());
	}
	
	@Test
	public void testSetDeltaX() {
		Player p= new Player(0,0,0,0);
		p.setDelta(1, 1);
		assertEquals("testSetDeltaX: Deltax wrong",1, p.getDeltaX());
	}
	
	@Test
	public void testSetDeltaY() {
		Player p= new Player(0,0,0,0);
		p.setDelta(1, 1);
		assertEquals("testSetDeltaX: Deltax wrong",1, p.getDeltaY());
	}
	
	@Test
	public void testMove() {
		Player p= new Player(-100,-100,0,0);
		p.setDelta(1, 1);
		p.move();
		assertEquals("testMove: Movement when wrong",0,p.getDeltaX());
		
	}
}
