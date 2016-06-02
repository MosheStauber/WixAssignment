/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author moshe
 */
public class LogModelTest {
    
    public LogModelTest() {
    }

    /**
     * Test of getLogType method, of class LogModel.
     */
    @Test
    public void testGetLogType() {
        System.out.println("getLogType");
        Date date = new Date(System.currentTimeMillis());
        LogModel instance = new LogModel("1", "hello", date,"ADDED");
        String expResult = "ADDED";        
        String result = instance.getLogType();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class LogModel.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Date date = new Date(System.currentTimeMillis());
        LogModel instance = new LogModel("1", "hello", date,"ADDED");
        String expResult = "NoteID: 1"
                + "\nContent: hello"
                + "\nDate Created: " + date.toString()
                + "\nLogType: ADDED";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
