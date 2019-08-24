package dina.nota.app.domain.repositories

import dina.nota.app.data.room.Note

interface NotesRepositories {

    fun getAllNotes()
    fun addNote(note: Note)
    fun deleteNote(note: Note)
    fun editNote(note: Note);
}
