import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.Utils;

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
    
    //Test if valid date counts as valid
    @Test
    public void testDateValid() {
        boolean a = Utils.isDateValid(17, 9, 2016);
        assertEquals(a, true);        
    }
   
    //Test day validator with false day input with a number that exceeds the maximum number
    @Test
    public void testDateValid1() {
        boolean a = Utils.isDateValid(59, 9, 2016);
        assertEquals(a, false);
    }
    
    //Test day validator with false day input with a number that is below the minimum number
    @Test
    public void testDateValid2() {
        boolean a = Utils.isDateValid(0, 9, 2016);
    }
        
    //Test month validator with false month input that exceeds the maximum number
    @Test
    public void testDateValid3() {
        boolean a = Utils.isDateValid(17, 24, 1998);
        assertEquals(a, false); 
    }
    
    //Test month validator with false input that is below the minimum number
    @Test
    public void testDateValid4() {
        boolean a = Utils.isDateValid(17, 0, 2016);
        assertEquals(a, false);
    }
    
    //Test year validator with false year input that exceeds the maximum number
    @Test
    public void testDateValid5() {
        boolean a = Utils.isDateValid(17, 9, 2026);
        assertEquals(a, false);
    }
    
    //Test year validator with false year input that is below the minimum number
    @Test
    public void testDateValid6() {
        boolean a = Utils.isDateValid(17, 9, 1990);
    }
    
}
