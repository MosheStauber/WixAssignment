/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author moshe
 */
public class MySQLAccessTest {
    
    public MySQLAccessTest() {
    }

    /**
     * Test of addItem method, of class MySQLAccess.
     */
    @Test
    public void testAddItem() throws Exception {
        System.out.println("addItem");
        
        String server = "jdbc:mysql://wixassignment.c9kdh6uh1bbe.us-west-2.rds.amazonaws.com/";
        String dbname = "wixtodolisttest";
        String username = "moshe";
        String password = "1Likeeggs";
        boolean isTest = true;
        
        MySQLAccess instance = new MySQLAccess(server + dbname, username, password, isTest);
        String description = "hello";
        int result = instance.addItem(description);
        
        int expResult = 1;
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteItem method, of class MySQLAccess.
     */
    @Test
    public void testDeleteItem() throws Exception {
        System.out.println("deleteItem");
        
        String server = "jdbc:mysql://wixassignment.c9kdh6uh1bbe.us-west-2.rds.amazonaws.com/";
        String dbname = "wixtodolisttest";
        String username = "moshe";
        String password = "1Likeeggs";
        boolean isTest = true;
        
        MySQLAccess instance = new MySQLAccess(server + dbname, username, password, isTest);
        instance.addItem("hello");
        
        int id = 1;
        String expResult = "Successfully deleted note with ID: " + id;
        String result = instance.deleteItem(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllItems method, of class MySQLAccess.
     */
    @Test
    public void testGetAllItems() throws Exception {
        System.out.println("getAllItems");
        
        String server = "jdbc:mysql://wixassignment.c9kdh6uh1bbe.us-west-2.rds.amazonaws.com/";
        String dbname = "wixtodolisttest";
        String username = "moshe";
        String password = "1Likeeggs";
        boolean isTest = true;
        
        MySQLAccess instance = new MySQLAccess(server + dbname, username, password, isTest);
        instance.addItem("hello");
        
        Date date = new Date(System.currentTimeMillis());
        
        List<NoteModel> expResult = new ArrayList<>();
        expResult.add(new NoteModel("1", "hello", date));
        
        List<NoteModel> result = instance.getAllItems();        
        
        assertEquals(expResult.get(0).toString(), result.get(0).toString());
    }

    /**
     * Test of getLogs method, of class MySQLAccess.
     */
    @Test
    public void testGetLogs() throws Exception {
        System.out.println("getLogs");
        
        String server = "jdbc:mysql://wixassignment.c9kdh6uh1bbe.us-west-2.rds.amazonaws.com/";
        String dbname = "wixtodolisttest";
        String username = "moshe";
        String password = "1Likeeggs";
        boolean isTest = true;
        
        MySQLAccess instance = new MySQLAccess(server + dbname, username, password, isTest);
        instance.addItem("hello");
        
        Date date = new Date(System.currentTimeMillis());
        List<LogModel> expResult = new ArrayList<>();
        expResult.add(new LogModel("1", "hello", date, "ADDED"));
        List<LogModel> result = instance.getLogs();
        assertEquals(expResult.get(0).toString(), result.get(0).toString());
    }
    
}
