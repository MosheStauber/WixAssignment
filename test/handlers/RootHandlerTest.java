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
 * This class runs a test to verify the root context handler is working properly.
 * It expects the handler to return an HTML document.
 */
public class RootHandlerTest {
    
    public RootHandlerTest() {
    }

    /**
     * Test of handle method, of class RootHandler.
     */
    @Test
    public void testHandle() throws Exception {
        System.out.println("handle");
        
        // Create server to test response
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(9000), 0);
        httpServer.createContext("/", new RootHandler());
        
        // Start the server
        httpServer.start();
        
        // Verify client code. The test is expecting to return an HTML document
        URL url = new URL("http://localhost:9000/");
        URLConnection conn = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        assertEquals("<!DOCTYPE html>", in.readLine());

        // stop the server
        httpServer.stop(0);
    }
    
}
