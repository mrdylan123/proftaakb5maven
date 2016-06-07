/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

import javafx.scene.control.Alert;

/**
 *
 * @author maikel
 */
public class Utils {
    static boolean isDateValid(Integer day, Integer month, Integer year)
    {
         if (year == null || month == null || day == null || day < 1 || day > 32
                || month < 1 || month > 12 || year < 2016 || year > 2100)
         {
             return false;
         }
         else
         {
             return true;
         }
    }
}
