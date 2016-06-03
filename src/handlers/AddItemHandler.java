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
 * 
 * This class handles the "/additem" context. It parses the query and gets the 
 * description for the new note. It attempts to insert a new note into the 
 * database and returns the response to the client
 */
public class AddItemHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange he) throws IOException {
        
        // Get the query from the URI and parse it 
        URI requestedUri = he.getRequestURI();
        String query = requestedUri.getRawQuery(); 
        String noteContent = parseQuery(query);
        
        String response = "";
        
        // Try to insert the note into the database and record response.
        // If the note description is empty, it fails to insert.
        try {
            if(noteContent != null){
                int insertedID;
                insertedID = ToDoListHttpServer.DAO.addItem(noteContent);
                response = insertedID + ": " + noteContent;
            }else{
                response = "Cannot insert empty note";
            }
        } catch (Exception ex) {
            Logger.getLogger(AddItemHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Return the response to client
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }
    
    
    /**
    * This method parses the query passed in the url and returns it as a String.
    */
    public String parseQuery(String query) throws UnsupportedEncodingException {
        if (query != null) {
            String param[] = query.split("=");
            String value = null;
            if (param.length > 1) {
                value = URLDecoder.decode(param[1], 
                System.getProperty("file.encoding"));
                return value;
            }
        }
        
        return null;
    }
}
