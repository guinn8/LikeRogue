package jUnit;


import actors.*;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;


public class EnemyTest{
	@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
	
	@Test
	public void testEnemyConst() {
		Enemy e = new Enemy(400,400,10,400);
		assertEquals("testEnemyConst: damage value incorrect",400,e.getDamage());
	}

	/*public void testInventoryConst() {
		Inventory i = new Inventory();
		assertsEquals("testInventoryConst: Expected initial");
	}*/
		
}
	


