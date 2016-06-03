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
 * This class handles the "/deleteitem" context. It parses the query and gets the 
 * id for the note to be deleted. It attempts to delete the note and 
 * returns the response to the client.
 * 
 */

public class DeleteItemHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        
        // Get the query from the URI and parse it 
        URI requestedUri = he.getRequestURI();
        String query = requestedUri.getRawQuery();
        int noteID = parseQuery(query);
        
        String response = "";
        
        // If the id is valid (id > 0), try to delete from database.
        // The database can return a response saying the id does not exist in the db
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
    
    /**
     * 
     * This method returns the query arg as an integer. If not a valid query, of
     * id < 0, returns -1
     */
    public int parseQuery(String query) throws UnsupportedEncodingException {
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
