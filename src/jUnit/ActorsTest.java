package jUnit;

import static org.junit.Assert.*;
import org.junit.Test;

import actors.Player;

public class ActorsTest{
    @Test
    public void testPlayerConst(){
        Player p = new Player(250, 250, 300, 2);
        assertEquals("testPlayerConst: health value incorrect",300,p.getHealth());
    }
}
