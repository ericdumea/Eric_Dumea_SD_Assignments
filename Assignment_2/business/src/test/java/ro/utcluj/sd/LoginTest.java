package ro.utcluj.sd;

import junit.framework.TestCase;
import ro.utcluj.sd.dal.impl.DaoFactory;

public class LoginTest extends TestCase {

    public void testLoginWrap() {

        Login l = new Login(DaoFactory.Type.HIBERNATE);

        assertEquals(l.loginWrap("a@a.a", "aaaaaa"),2);


    }

    public void testLoginWrap2() {

        Login l = new Login(DaoFactory.Type.HIBERNATE);

        assertEquals(l.loginWrap("a", "aaaaaa"),-1);


    }
}