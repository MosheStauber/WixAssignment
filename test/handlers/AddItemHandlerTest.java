package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLConnection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author moshe
 * 
 * This class runs a test to verify the additem context handler is working properly.
 * It only makes sure the handle method is being called so it tries to add an empty
 * note and expects it to fail. 
 * 
 * It does not test the actual database insert as that is the job of the 
 * MySQLAccess test class.
 * 
 */
public class AddItemHandlerTest {
    
    /**
     * Test of handle method, of class AddItemHandler.
     */
    @Test
    public void testHandle() throws Exception {
        System.out.println("handle");
        
        // Create the server
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(9000), 0);
        httpServer.createContext("/additem", new AddItemHandler());
        
        // Start the server
        httpServer.start();
        
        // Verify our client code. It attemps to insert an empty note and expects
        // it to fail.
        URL url = new URL("http://localhost:9000/additem");
        URLConnection conn = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        assertEquals("Cannot insert empty note", in.readLine());

        // stop the server
        httpServer.stop(0);
    }

    /**
     * Test of parseQuery method, of class AddItemHandler.
     */
    @Test
    public void testParseQuery() throws Exception {
        System.out.println("parseQuery");
        String query = "note=hello+world";
        String result = new AddItemHandler().parseQuery(query);
        String expResult = "hello world";
        assertEquals(expResult, result);
    }
    
}
