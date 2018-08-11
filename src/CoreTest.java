package jUnit;

import actors.*;
import main.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CoreTest{
	@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
	
	

	@Test 
	public void testcheck(){
		Core.WIDTH;
	}
}