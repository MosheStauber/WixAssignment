package database;

import java.sql.Date;

/**
 *
 * @author moshe
 */
public class LogModel extends NoteModel{

    String logType;
    public LogModel(String id, String content, Date dateCreated, String logType) {
        super(id, content, dateCreated);
        this.logType = logType;
    }
    
    public String getLogType(){
        return this.logType;
    }
    
    
    @Override
    public String toString(){
        return "NoteID: " + getId() 
                + "\nContent: " + getContent()
                + "\nDate Created: " + getDateCreated()
                + "\nLogType: " + getLogType();
    }
    
}
