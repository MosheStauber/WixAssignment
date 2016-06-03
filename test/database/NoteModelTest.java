package database;

import java.sql.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author moshe
 */
public class NoteModelTest {
    
    public NoteModelTest() {
    }

    /**
     * Test of getId method, of class NoteModel.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Date date = new Date(System.currentTimeMillis());
        NoteModel instance = new NoteModel("1", "hello", date);
        String expResult = "1";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getContent method, of class NoteModel.
     */
    @Test
    public void testGetContent() {
        System.out.println("getContent");
        Date date = new Date(System.currentTimeMillis());
        NoteModel instance = new NoteModel("1", "hello", date);
        String expResult = "hello";
        String result = instance.getContent();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateCreated method, of class NoteModel.
     */
    @Test
    public void testGetDateCreated() {
        System.out.println("getDateCreated");
        Date date = new Date(System.currentTimeMillis());
        NoteModel instance = new NoteModel("1", "hello", date);
        Date expResult = date;
        Date result = instance.getDateCreated();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class NoteModel.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Date date = new Date(System.currentTimeMillis());
        NoteModel instance = new NoteModel("1", "hello", date);
        String expResult = "NoteID: 1"
                + "\nContent: hello"
                + "\nDate Created: " + date.toString();
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
