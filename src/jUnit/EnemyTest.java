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

<<<<<<< HEAD:src/jUnit/ActorsTest.java


=======
	/*public void testInventoryConst() {
		Inventory i = new Inventory();
		assertsEquals("testInventoryConst: Expected initial");
	}*/
		
>>>>>>> 922e9a91762741f3b09baf6ab3396a30dae09eec:src/jUnit/EnemyTest.java
}
	


