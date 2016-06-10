import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DatabaseConnectionException;

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

    @Test
    public void testcheckPassword1() throws DatabaseConnectionException {
        boolean a = ulm.checkPassword("maikel", "test");
        assertEquals(a, true);
    }
    
    @Test
    public void testcheckPassword2() throws DatabaseConnectionException {
        boolean a = ulm.checkPassword("maikel", "testt");
        assertEquals(a, false);
    }
    
    
    
    
    
}
