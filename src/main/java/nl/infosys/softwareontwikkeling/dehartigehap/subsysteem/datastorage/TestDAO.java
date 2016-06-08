package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author J. Bouman
 */
public class TestDAO {
    public void test() {
        DatabaseConnection connection = new DatabaseConnection();
        connection.openConnection();
    }
}
