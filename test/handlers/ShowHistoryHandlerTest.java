/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * This class runs a test to verify the showhistory context handler. It expects the 
 * handler to return an a table so it tests for a table header.
 */
public class ShowHistoryHandlerTest {
    
    public ShowHistoryHandlerTest() {
    }

    /**
     * Test of handle method, of class ShowHistoryHandler.
     */
    @Test
    public void testHandle() throws Exception {
        System.out.println("handle");
        
        // Create the server
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(9000), 0);
        httpServer.createContext("/showhistory", new ShowHistoryHandler());
        
        // Start the server
        httpServer.start();
        
        // Verify the client code. Expect the server to return a table, sp test
        // for table header
        URL url = new URL("http://localhost:9000/showhistory");
        URLConnection conn = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        assertEquals("<table border=\"1\">", in.readLine());

        // Stop the server
        httpServer.stop(0);
    }
    
}
