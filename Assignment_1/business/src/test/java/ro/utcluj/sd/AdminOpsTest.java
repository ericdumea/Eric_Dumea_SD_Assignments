package ro.utcluj.sd;

import junit.framework.TestCase;
import ro.utcluj.sd.model.Player;

public class AdminOpsTest extends TestCase {

    public void testGetPlayerOnId() {
        AdminOps a = new AdminOps();
        Player p =  new Player(1,"Vlad Pantis","pantisvlad@bucuresti.ro","tietata",false);
        assertEquals(p.getName(),a.getPlayerOnId(1).getName());
        assertEquals(p.getEmail(),a.getPlayerOnId(1).getEmail());
    }

    public void testEmailValidator() {
        AdminOps a = new AdminOps();
        assertEquals(true, a.emailValidator("aaa@aa.aa"));
    }

    public void testEmailValidator2() {
        AdminOps a = new AdminOps();
        assertEquals(false, a.emailValidator("potato"));
    }
}