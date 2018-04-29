package ro.utcluj.sd;

import junit.framework.TestCase;
import ro.utcluj.sd.model.Match;

public class GameCoreTest extends TestCase {

    public void testIsMatchOver() {
        Match m = new Match(1,2,1,3,5,1);
        assertEquals(new GameCore().isMatchOver(m), true);
    }

    public void testIsMatchOver2() {
        Match m = new Match(1,2,1,4,5,1);
        assertEquals(new GameCore().isMatchOver(m), false);
    }

    public void testIsMatchOver3() {
        Match m = new Match(1,2,1,1,1,1);
        assertEquals(new GameCore().isMatchOver(m), false);
    }
}