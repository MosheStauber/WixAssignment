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
import todolisthttpserver.handlers.AddItemHandler;
import todolisthttpserver.handlers.DeleteItemHandler;
import todolisthttpserver.handlers.GetAllItemsHandler;
import todolisthttpserver.handlers.RootHandler;
import todolisthttpserver.handlers.ShowHistoryHandler;

/**
 *
 * @author moshe
 */
public class ToDoListHttpServer {

    public static final MySQLAccess DAO = new MySQLAccess();
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
