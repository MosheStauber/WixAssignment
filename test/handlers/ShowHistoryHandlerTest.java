/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import com.sun.net.httpserver.HttpExchange;
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
        HttpExchange he = null;
        ShowHistoryHandler instance = new ShowHistoryHandler();
        instance.handle(he);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
