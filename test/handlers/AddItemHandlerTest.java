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
public class AddItemHandlerTest {
    
    public AddItemHandlerTest() {
    }

    /**
     * Test of handle method, of class AddItemHandler.
     */
    @Test
    public void testHandle() throws Exception {
        System.out.println("handle");
        HttpExchange he = null;
        AddItemHandler instance = new AddItemHandler();
        instance.handle(he);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseQuery method, of class AddItemHandler.
     */
    @Test
    public void testParseQuery() throws Exception {
        System.out.println("parseQuery");
        String query = "";
        String expResult = "";
        String result = AddItemHandler.parseQuery(query);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
