package jUnit;


import actors.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;


public class PlayerTest{
	@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
	
	@Test
	public void testPlayerHealth(){

    	Player p = new Player(300, 2);
        assertEquals("testPlayerHealth: health value incorrect",300,p.getHealth());
	}
	
	@Test
	public void testPlayerHealthNegative() {
		Player p = new Player(-1, 2);
		assertEquals("testPlayerHealth: health value cannot be negative", -1, p.getHealth());
	}
	
	@Test
	public void testPlayerHealthBoundary() {
		Player p = new Player(0, 2);
		assertEquals("testPlayerHealth: health value cannot be zero", 0, p.getHealth());
	}
	@Test
	public void testPlayerDamBoundary() {
		Player p = new Player(300,0);
		assertEquals("testPlayerDam: damage value incorrect",0,p.getDamage());
	}
	@Test
	public void testPlayerDamNegative(){
		Player p = new Player(300,-1);
		assertEquals("testPlayerDam: damage value cannot be negative", -1, p.getDamage());
	}	
	/*@SuppressWarnings("deprecation")
	@Test
	public void testSetDeltaX() {
		Player p = new Player(0,0);
		p.setDelta(1, 1);
		assertEquals("testSetDeltaX: Deltax wrong",1, p.getDeltaX());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testSetDeltaY() {
		Player p = new Player(0,0);
		p.setDelta(1, 1);
		assertEquals("testSetDeltaY: DeltaY wrong",1, p.getDeltaY());
	}*/
	
	@Test
	public void testAttackUp() {
		Player p = new Player(0,0);
		p.attack(3);
		assertEquals("testAttack: Expected boolean: false", false, p.attack(3));
	}
	
	@Test
	public void testAttackDown() {
		Player p = new Player(0,0);
		p.attack(0);
		assertEquals("testAttack: Expected boolean: false", false, p.attack(0));
	}
	
	@Test
	public void testAttackLeft() {
		Player p = new Player(0,0);
		p.attack(1);
		assertEquals("testAttack: Expected boolean: false", false, p.attack(1));
	}

	@Test
	public void testAttackRight() {
		Player p = new Player(0,0);
		p.attack(2);
		assertEquals("testAttack: Expected boolean: false", false, p.attack(2));
	}
	@Test
	public void testResetDamX() {
		Player p = new Player(0,0);
		final ImageView damage = new ImageView();
		int x = -1000;
		damage.setLayoutX(x);
		assertEquals("Expected x value: -1000", -1000, x);
       }
	
	@Test
	public void testResetDamXIfPositive() {
		Player p = new Player(0,0);
		final ImageView damage = new ImageView();
		int x = 1000;
		damage.setLayoutX(x);
		assertEquals("Expected x value: -1000: Real x value: 1000", 1000, x);
	}
	
	@Test
	public void testResetDamXIfUnexpectedValue() {
		Player p = new Player(0,0);
		final ImageView damage = new ImageView();
		int x = 999999;
		damage.setLayoutX(x);
		assertEquals("Expected x value -1000: Real x value: 999999", 999999, x);
	}
	@Test
	public void testResetDamY() {
		Player p = new Player(0,0);
		final ImageView damage = new ImageView();
		int y = -1000;
		damage.setLayoutY(y);
		assertEquals("Expected y value: -1000", -1000, y);
	}
	
	
	public void testResetDamYIfPositive() {
		Player p = new Player(0,0);
		final ImageView damage = new ImageView();
		int y = 1000;
		damage.setLayoutY(y);
		assertEquals("Expected y value: -1000: Real y value: 1000", 1000, y);
	}

	@Test
	public void testResetDamYIfUnexpectedValue() {
		Player p = new Player(0,0);
		final ImageView damage = new ImageView();
		int y = 999999;
		damage.setLayoutX(y);
		assertEquals("Expected y value -1000: Real y value: 999999", 999999, y);
	}
}

