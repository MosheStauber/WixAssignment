package database;

import java.sql.Date;

public class NoteModel {
    private String id;
    private String content;
    private Date dateCreated;
    
    public NoteModel(String id, String content, Date dateCreated){
        this.id = id;
        this.content = content;
        this.dateCreated = dateCreated;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
    
    @Override
    public String toString(){
        return "NoteID: " + getId() 
                + "\nContent: " + getContent()
                + "\nDate Created: " + getDateCreated();
    }
    
    
}
