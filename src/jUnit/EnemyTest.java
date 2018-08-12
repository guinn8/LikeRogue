package jUnit;

import actors.*;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EnemyTest{
	@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
	
	@Test
	public void testEnemyHealthIfZero() {
		Enemy e = new Enemy(0,0,0,0);
		assertEquals("Enemy Health cannot be zero", 0, e.getHealth());
	}

	@Test
	public void testEnemyHealthIfNegative() {
		Enemy e = new Enemy(0,0,-1,0);
		assertEquals("Enemy Health cannot be negative", -1, e.getHealth());
	}
	
	@Test
	public void testEnemyDamageIfZero() {
		Enemy e = new Enemy(0,0,0,0);
		assertEquals("Enemy Damage cannot be zero", 0, e.getDamage());
	}
	
	@Test
	public void testEnemyDamageIfNegative() {
		Enemy e = new Enemy(0,0,0,-1);
		assertEquals("Enemy Health cannot be negative", -1, e.getDamage());
	}
}
