/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import database.LogModel;
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
public class ShowHistoryHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        List<LogModel> logs;
        StringBuilder response = new StringBuilder();
                response.append("<table border=\"1\">\n"
                + "<tr>\n"
                + "\t<th>NoteID</th>\n"
                + "\t<th>Content</th>\n"
                + "\t<th>Type</th>\n"
                + "\t<th>Created</th>\n"
                + "</tr>");
        try {
            logs = ToDoListHttpServer.DAO.getLogs();
            for(LogModel log : logs){
                response.append( "<tr>\n"
                            + "\t<td>" + log.getId() + "</td>"
                            + "\t<td>" + log.getContent() + "</td>"
                            + "\t<td>" + log.getLogType() + "</td>"
                            + "\t<td>" + log.getDateCreated() + "</td>"
                            + "</tr>");
            }
            response.append("</table>");
        } catch (Exception ex) {
            Logger.getLogger(GetAllItemsHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        he.sendResponseHeaders(200, response.length());        
        OutputStream os = he.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }
    
}
