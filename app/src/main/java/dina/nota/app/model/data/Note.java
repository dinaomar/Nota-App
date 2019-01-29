package dina.nota.app.model.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title, description, soundFilePath;

    public Note(String title, String description, String soundFilePath) {
        this.title = title;
        this.description = description;
        this.soundFilePath = soundFilePath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSoundFilePath() {
        return soundFilePath;
    }
}
