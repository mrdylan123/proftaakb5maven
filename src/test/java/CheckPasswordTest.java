import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.*;

public class CheckPasswordTest {
    private UserLoginManager ulm;
    
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
        ulm = new UserLoginManager();
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
        boolean a = ulm.checkPassword("maikel", "test");
        assertEquals(a, true);
    }
    
    @Test
    public void testcheckPassword2(){
        boolean a = ulm.checkPassword("maikel", "testt");
        assertEquals(a, false);
    }
    
    
    
    
    
}
