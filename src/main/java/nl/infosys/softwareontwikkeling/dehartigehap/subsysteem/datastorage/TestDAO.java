/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
