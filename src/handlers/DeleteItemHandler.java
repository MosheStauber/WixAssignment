/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import todolisthttpserver.ToDoListHttpServer;

/**
 *
 * @author moshe
 */
public class DeleteItemHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        URI requestedUri = he.getRequestURI();
        String query = requestedUri.getRawQuery();
        int noteID = parseQuery(query);
        String response = "";
        
        if(noteID == -1){
            response = "Must enter a valid ID";
        }else{
            try {
                response = ToDoListHttpServer.DAO.deleteItem(noteID);
            } catch (Exception ex) {
                Logger.getLogger(DeleteItemHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    
    public static int parseQuery(String query) throws UnsupportedEncodingException {
        if (query != null) {
            String param[] = query.split("=");
            String value = null;
            
            if (param.length > 1) {
                value = URLDecoder.decode(param[1], 
                System.getProperty("file.encoding"));
                return Integer.parseInt(value);
            }            
        }
        return -1;
    }
    
    
}
