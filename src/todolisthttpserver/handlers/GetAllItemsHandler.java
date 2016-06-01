/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolisthttpserver.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import database.NoteModel;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import todolisthttpserver.ToDoListHttpServer;

/**
 *
 * @author moshe
 */
public class GetAllItemsHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange he) throws IOException {
        List<NoteModel> allNotes;
        String response = "<table border=\"1\">\n"
                + "<tr>\n"
                + "\t<th>NoteID</th>\n"
                + "\t<th>Content</th>\n"
                + "\t<th>Created</th>\n"
                + "</tr>";
        try {
            allNotes = ToDoListHttpServer.DAO.getAllItems();
            for(NoteModel note : allNotes){
                response +=  "<tr>\n"
                            + "\t<td>" + note.getId() + "</td>"
                            + "\t<td>" + note.getContent() + "</td>"
                            + "\t<td>" + note.getDateCreated() + "</td>"
                            + "</tr>";
            }
            response += "</table>";
        } catch (Exception ex) {
            Logger.getLogger(GetAllItemsHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        he.sendResponseHeaders(200, response.length());        
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    
}
