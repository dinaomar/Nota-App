package dina.nota.app.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
public abstract class NotesRookDatabase : RoomDatabase() {
    abstract fun NotesDao(): NoteDao
}