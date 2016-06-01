/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolisthttpserver.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import todolisthttpserver.ToDoListHttpServer;

/**
 *
 * @author moshe
 */
public class AddItemHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange he) throws IOException {
        
        Map<String, Object> parameters = new HashMap<String, Object>();
        URI requestedUri = he.getRequestURI();
        String query = requestedUri.getRawQuery();
        System.out.println("Query is : " + query);
        parseQuery(query, parameters);
        
        // send response
        String noteContent = "";
        String response = "";
        for (String key : parameters.keySet())
                 noteContent += parameters.get(key) + "\n";
        
        try {
            response = ToDoListHttpServer.DAO.addItem(noteContent) + ": " +noteContent;
        } catch (Exception ex) {
            Logger.getLogger(AddItemHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }
    
    public static void parseQuery(String query, Map<String, 
	Object> parameters) throws UnsupportedEncodingException {

        if (query != null) {
            String param[] = query.split("=");
            String key = null;
            String value = null;
            if (param.length > 0) {
                key = URLDecoder.decode(param[0], 
                System.getProperty("file.encoding"));
            }

            if (param.length > 1) {
                value = URLDecoder.decode(param[1], 
                System.getProperty("file.encoding"));
            }

            if (parameters.containsKey(key)) {
                Object obj = parameters.get(key);
                if (obj instanceof List<?>) {
                    List<String> values = (List<String>) obj;
                    values.add(value);

                } else if (obj instanceof String) {
                    List<String> values = new ArrayList<String>();
                    values.add((String) obj);
                    values.add(value);
                    parameters.put(key, values);
                }
            } else {
                parameters.put(key, value);
            }

        }
    }
}
