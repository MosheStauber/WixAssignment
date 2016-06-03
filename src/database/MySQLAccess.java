package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author moshe
 */
public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String url;
    private String username;
    private String password;
    private boolean isTest;
    
    public MySQLAccess(String url, String username, String password){        
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) { }
      
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    /**
     * 
     * This constructor is used for testing
     */
    public MySQLAccess(String url, String username, String password, boolean isTest){        
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) { }
      
        this.url = url;
        this.username = username;
        this.password = password;
        
        if(isTest){
            try {
                // Add a new note to the database.
                connect = DriverManager.getConnection(url, username, password);
                statement = connect.createStatement();
                String query1 = "TRUNCATE TABLE notes";
                String query2 = "TRUNCATE TABLE logs";
                statement.executeUpdate(query1);
                statement.executeUpdate(query2);                
            }catch(Exception e){}
            finally{
                close();
            }
        }
    }
    
    /**
     * This method adds an item to the notes table, if it was successful it
     * records the addition in the logs. It returns inserted items ID
     */
    public int addItem(String description) throws Exception{
        try {
            // Add a new note to the database.
            connect = DriverManager.getConnection(url, username, password);
            preparedStatement = connect
                .prepareStatement("insert into notes(DESCRIPTION) values (?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, description);
            preparedStatement.executeUpdate();
            
            // If database update success, write to log and return the new items ID.
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int insertedID = generatedKeys.getInt(1);
                
                preparedStatement = connect
                        .prepareStatement("insert into logs(NOTEID, DESCRIPTION, LOGTYPE) values (?,?,?)", 
                        Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, insertedID);
                preparedStatement.setString(2, description);
                preparedStatement.setString(3, "ADDED");
                preparedStatement.executeUpdate();
                
                return insertedID;
            }
            else {
                throw new Exception("Note description cannot be NULL");
            }
            
        } catch (Exception e) {
          throw e;
        } finally {
            close();
        }
    }
    
    /**
     * 
     * This method deletes items from the database. It first checks if the the 
     * row exists, if it does it copies it to the log and deletes it, otherwise
     * it returns a response that the row with given ID does not exist.
     */
    public String deleteItem(int id) throws Exception{
        
        try {
            connect = DriverManager.getConnection(url, username, password);
            statement = connect.createStatement();
            
            // Retrive row to be deleted to logs table
            resultSet = statement.executeQuery("select DESCRIPTION from notes where id =" + id);
            
            // If the row exists, it copies it over to the log table and deletes.
            // Otherwise it responds with invalid operation
            if(resultSet.next()){
                String noteID = ""+id;
                String content = resultSet.getString("DESCRIPTION");
                preparedStatement = connect
                        .prepareStatement("insert into logs(NOTEID, DESCRIPTION, LOGTYPE) values (?,?,?)", 
                        Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, content);
                preparedStatement.setString(3, "DELETED");
                preparedStatement.executeUpdate();
                statement.executeUpdate("delete from notes where id =" + id);
                return "Successfully deleted note with ID: " + id;
            }else{
                return "Note with ID: " +id+ " does not exist";
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    /**
     * This method executes a statement to get all rows in the db.
     * It return a list of NoteModels
     */
    public List<NoteModel> getAllItems() throws Exception{
        try {
            connect = DriverManager.getConnection(url, username, password);
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select * from notes");
            return writeNoteResultSet(resultSet);

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    /**
     * This method executes a statement to get all rows in the db.
     * It return a list of LogModels
     */
    public List<LogModel> getLogs() throws Exception{
        try {
            connect = DriverManager.getConnection(url, username, password);
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select NOTEID, DESCRIPTION, CREATED, LOGTYPE from logs");
            return writeLogResultSet(resultSet);

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    /**
     * This method populates a list of NoteModels from a given result set and 
     * returns the list.
     */
    private List<NoteModel> writeNoteResultSet(ResultSet resultSet) throws SQLException {
        List<NoteModel> resultList = new ArrayList<>();
        while (resultSet.next()) {
          String noteID = resultSet.getString("id");
          String content = resultSet.getString("DESCRIPTION");
          Date date = resultSet.getDate("CREATED");
          resultList.add(new NoteModel(noteID, content, date));
        }
        return resultList;
    }
    
    /**
     * This method populates a list of LogModels from a given result set and 
     * returns the list.
     */
    private List<LogModel> writeLogResultSet(ResultSet resultSet) throws SQLException {
        List<LogModel> resultList = new ArrayList<>();
        while (resultSet.next()) {
          String noteID = resultSet.getString("NOTEID");
          String content = resultSet.getString("DESCRIPTION");
          Date date = resultSet.getDate("CREATED");
          String type = resultSet.getString("LOGTYPE");
          resultList.add(new LogModel(noteID, content, date, type));
        }
        return resultList;
    }

    // You need to close the resultSet
    private void close() {
      try {
        if (resultSet != null) {
          resultSet.close();
        }

        if (statement != null) {
          statement.close();
        }
        
        if(preparedStatement != null){
            preparedStatement.close();
        }

        if (connect != null) {
          connect.close();
        }
      } catch (Exception e) {

      }
    }
}
