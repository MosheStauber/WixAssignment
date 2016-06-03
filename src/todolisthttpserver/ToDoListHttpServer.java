/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolisthttpserver;

import com.sun.net.httpserver.HttpServer;
import database.MySQLAccess;
import java.io.IOException;
import java.net.InetSocketAddress;
import handlers.AddItemHandler;
import handlers.DeleteItemHandler;
import handlers.GetAllItemsHandler;
import handlers.RootHandler;
import handlers.ShowHistoryHandler;

/**
 *
 * @author moshe
 */
public class ToDoListHttpServer {
    static final String server = "jdbc:mysql://wixassignment.c9kdh6uh1bbe.us-west-2.rds.amazonaws.com/";
    static final String dbname = "wixtodolist";
    static final String username = "moshe";
    static final String password = "1Likeeggs";
    
    public static final MySQLAccess DAO = new MySQLAccess(server + dbname, username, password);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        int port = 9000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("server started at " + port);
        server.createContext("/", new RootHandler());
        server.createContext("/additem", new AddItemHandler());
        server.createContext("/getallitems", new GetAllItemsHandler());
        server.createContext("/deleteitem", new DeleteItemHandler());
        server.createContext("/showhistory", new ShowHistoryHandler());
        server.setExecutor(null);
        server.start();
    }
    
}
