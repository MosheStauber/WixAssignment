package database;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author moshe
 * 
 * This class is to test the MySQLAccess class. It tests all of the methods in
 * the class using a test database. When creating an instance of MySQLAccess with 
 * the test constructor, it TRUNCATES the database and starts from scratch.
 */
public class MySQLAccessTest {
    
    public MySQLAccessTest() {
    }

    /**
     * Test of addItem method, of class MySQLAccess. Create a new instance of
     * MySQLAccess and add a new note which will have an id of "1", it returns 
     * the correct id. 
     */
    @Test
    public void testAddItem() throws Exception {
        System.out.println("addItem");
        
        String server = "jdbc:mysql://wixassignment.c9kdh6uh1bbe.us-west-2.rds.amazonaws.com/";
        String dbname = "wixtodolisttest";
        String username = "moshe";
        String password = "1Likeeggs";
        boolean isTest = true;
        
        // Create db instance
        MySQLAccess instance = new MySQLAccess(server + dbname, username, password, isTest);
        
        // Add note
        String description = "hello";
        int result = instance.addItem(description);
        
        int expResult = 1;
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteItem method, of class MySQLAccess.
     * First add item to db then ensure removal works
     */
    @Test
    public void testDeleteItem() throws Exception {
        System.out.println("deleteItem");
        
        String server = "jdbc:mysql://wixassignment.c9kdh6uh1bbe.us-west-2.rds.amazonaws.com/";
        String dbname = "wixtodolisttest";
        String username = "moshe";
        String password = "1Likeeggs";
        boolean isTest = true;
        
        // Create db instance
        MySQLAccess instance = new MySQLAccess(server + dbname, username, password, isTest);
        instance.addItem("hello");
        
        // Delete valid note from db
        int id = 1;
        String expResult = "Successfully deleted note with ID: " + id;
        String result = instance.deleteItem(id);
        assertEquals(expResult, result);
        
        // Test invalid deletion 
        expResult = "Note with ID: " +id+ " does not exist";
        result = instance.deleteItem(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllItems method, of class MySQLAccess. Expects to receive a List
     * of NoteModels. Insert one note and retrieve it, testing the toString method
     * of the note.
     */
    @Test
    public void testGetAllItems() throws Exception {
        System.out.println("getAllItems");
        
        String server = "jdbc:mysql://wixassignment.c9kdh6uh1bbe.us-west-2.rds.amazonaws.com/";
        String dbname = "wixtodolisttest";
        String username = "moshe";
        String password = "1Likeeggs";
        boolean isTest = true;
        
        // Create instance and add note to list
        MySQLAccess instance = new MySQLAccess(server + dbname, username, password, isTest);
        instance.addItem("hello");
        
        // Create noteList for expected result. Add note to list.
        List<NoteModel> expResult = new ArrayList<>();        
        Date date = new Date(System.currentTimeMillis());
        expResult.add(new NoteModel("1", "hello", date));
        
        // Retrieve list of notes and assert
        List<NoteModel> result = instance.getAllItems();                
        assertEquals(expResult.get(0).toString(), result.get(0).toString());
    }

    /**
     * Test of getLogs method, of class MySQLAccess. Expects to receive a List
     * of LogModels. Insert one note and retrieve the logs, testing the toString 
     * method of the log.
     */
    @Test
    public void testGetLogs() throws Exception {
        System.out.println("getLogs");
        
        String server = "jdbc:mysql://wixassignment.c9kdh6uh1bbe.us-west-2.rds.amazonaws.com/";
        String dbname = "wixtodolisttest";
        String username = "moshe";
        String password = "1Likeeggs";
        boolean isTest = true;
        
        // Create instance and add note to db, the log will record it. 
        MySQLAccess instance = new MySQLAccess(server + dbname, username, password, isTest);
        instance.addItem("hello");
        
        // Create expected logList and add log to it
        Date date = new Date(System.currentTimeMillis());
        List<LogModel> expResult = new ArrayList<>();
        expResult.add(new LogModel("1", "hello", date, "ADDED"));
        
        // Retieve the logs and assert.
        List<LogModel> result = instance.getLogs();
        assertEquals(expResult.get(0).toString(), result.get(0).toString());
    }
    
}
