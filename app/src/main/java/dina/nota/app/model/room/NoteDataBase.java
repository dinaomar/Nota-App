package dina.nota.app.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import dina.nota.app.model.data.Note;

@Database(entities = Note.class, version = 1)
public abstract class NoteDataBase extends RoomDatabase {

    private static NoteDataBase instance;

    public abstract NoteDao getNoteDao();

    public static synchronized NoteDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, NoteDataBase.class, "notes_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
