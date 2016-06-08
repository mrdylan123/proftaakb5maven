

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
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.Utils;

/**
 *
 * @author dyl
 */
public class DateValidTest {
    
    public DateValidTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    public void testDateValid() {
        boolean a = Utils.isDateValid(17,9,1998);
        assertEquals(a, true);        
    }
    
    public void testDateValid1() {
        boolean a = Utils.isDateValid(17,24,1998);
        assertEquals(a, true);        
    }
    
    
}
