package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 *
 * @author moshe
 * This class handle the root url context for the app. It returns the index.html file
 * which contains the form for the client to interact with. 
 */
public class RootHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        
        String root = getRootPath();
        
        File indexHTML = new File(root + "/src/views/index.html").getCanonicalFile(); 
        
        if(!indexHTML.exists()){
            String response = "404 (Not Found)\n"
                    + "current dir: " + root;
            he.sendResponseHeaders(404, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }else{       
            he.sendResponseHeaders(200, 0);
            OutputStream os = he.getResponseBody();
            FileInputStream fs = new FileInputStream(indexHTML);
            final byte[] buffer = new byte[0x10000];
            int count = 0;
            while ((count = fs.read(buffer)) >= 0){
                os.write(buffer, 0, count);
            }
            fs.close();
            os.close();
        }
    }
    
    // Get the root path of the application to allow run from Netbeans or JAR file
    public String getRootPath(){
        String match = "ToDoListHttpServer";
        String root = System.getProperty("user.dir");
        int endOfWordIdx = root.indexOf(match) + match.length()+1;
        if(endOfWordIdx >= root.length()){
            return root;
        }
        
        String root2 = root.substring(0,endOfWordIdx);
        System.out.println(root2);
        return root2;
    }
    
}
