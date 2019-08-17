package dina.nota.app.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class Note {
    private val noteTitle: String
    @PrimaryKey
    private val id: Int
    private val noteBody: String

    constructor(noteTitle: String, id: Int, noteBody: String) {
        this.noteTitle = noteTitle
        this.id = id
        this.noteBody = noteBody
    }
}