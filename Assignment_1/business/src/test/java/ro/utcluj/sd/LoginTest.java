package ro.utcluj.sd;

import junit.framework.TestCase;

public class LoginTest extends TestCase {

    public void testLoginWrap() {

        Login l = new Login();

        assertEquals(l.loginWrap("a@a.a", "aaaaaa"),2);


    }

    public void testLoginWrap2() {

        Login l = new Login();

        assertEquals(l.loginWrap("a", "aaaaaa"),-1);


    }
}