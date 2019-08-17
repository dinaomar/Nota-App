package dina.nota.app.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Query("SELECT * from notes_table")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("DELETE from notes_table")
    fun deletaAllNotes()

    @Insert
    fun addNote(note: Note)

    @Update
    fun updateNote(note: Note)
}