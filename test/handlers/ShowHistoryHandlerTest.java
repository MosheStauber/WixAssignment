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
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(9000), 0);
        httpServer.createContext("/showhistory", new ShowHistoryHandler());
        
        // start the server
        httpServer.start();
        // verify our client code
        URL url = new URL("http://localhost:9000/showhistory");
        URLConnection conn = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        assertEquals("<table border=\"1\">", in.readLine());

        // stop the server
        httpServer.stop(0);
    }
    
}
