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
	public void testEnemyConst(){

    	Enemy e = new Enemy(250, 250, 10, 2);
        assertEquals("testEnemyConst: health value incorrect",10,e.getHealth());
        assertEquals("testEnemyConst: Damgae value incorrect",2,e.getDamage());  
		
	}
	@Test
	public void testnegativenumber(){

    	Enemy e = new Enemy(250, 250, 10, 2);
    	e.setHealth(-10);
        assertEquals("testEnemyConst: health value incorrect",10,e.getHealth());
        e.setDamage(-10);
        assertEquals("testEnemyConst: Damgae value incorrect",2,e.getDamage());	
	}
	@Test
	public void testoverrangenumber(){

    	Enemy e = new Enemy(250, 250, 10, 2);
    	e.setHealth(11);
        assertEquals("testEnemyConst: health value incorrect",10,e.getHealth());
        e.setDamage(22);
        assertEquals("testEnemyConst: Damgae value incorrect",2,e.getDamage());	
	}

	private boolean hasRequiredProtectedMethods() {
		boolean ImageViewIsProtected = false;
	
		try {
			BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Johnny\\Desktop\\LikeRogue-master\\src\\actors\\Enemy.java"));
			String line = in.readLine();
			while (line != null) {
				if (line.contains("protected ImageView") ) {
					ImageViewIsProtected = true;
				} 
				line = in.readLine();
			}
			in.close();
		} catch (FileNotFoundException e) {
			ImageViewIsProtected = false;
		} catch (IOException e) {
			ImageViewIsProtected =  false;
		}
		return ImageViewIsProtected;
	
	}
	
	@Test
	public void testprotectedmethod(){
		assertTrue("getImageView is protected mothod",hasRequiredProtectedMethods() );
  
	}
	private boolean noDefaultConstructor(){
		boolean noDefault = true;
		try {
			BufferedReader in = new BufferedReader(new FileReader("C:\\\\Users\\\\Johnny\\\\Desktop\\\\LikeRogue-master\\\\src\\\\actors\\\\Enemy.java"));
			String line = in.readLine();
			while (line != null) {
				if (line.contains("public Enemy()")) {
					noDefault = false;
				}
				line = in.readLine();
			}
			in.close();
		} catch (FileNotFoundException e) {
			noDefault = false;
		} catch (IOException e) {
			noDefault =  false;
		}
		return noDefault;
	
	}
	@Test
	public void nodefaultconstortest() {
		assertTrue("no default Constructor",noDefaultConstructor() );
	}

}
