

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.*;

/**
 *
 * @author dyl
 */
public class CheckPasswordTest {
    
    public CheckPasswordTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        UserLoginManager ulm = new UserLoginManager();
    }
    
    @After
    public void tearDown() {

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testcheckPassword1(){
        UserLoginManager ulm1 = new UserLoginManager();
        boolean a = ulm1.checkPassword("maikel", "test");
        assertEquals(a, true);
    }
    
    @Test
    public void testcheckPassword2(){
        UserLoginManager ulm2 = new UserLoginManager();
        boolean a = ulm2.checkPassword("maikel", "testt");
        assertEquals(a, false);
    }
    
    
    
    
    
}
