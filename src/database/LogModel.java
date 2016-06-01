/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
