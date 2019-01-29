package dina.nota.app.model.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import dina.nota.app.model.data.Note;
import dina.nota.app.model.room.NoteDao;
import dina.nota.app.model.room.NoteDataBase;

public class NotesRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NotesRepository(Application application) {
        NoteDataBase noteDataBase = NoteDataBase.getInstance(application);
        noteDao = noteDataBase.getNoteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        InsertNoteAsyncTask insertNoteAsyncTask = new InsertNoteAsyncTask(noteDao);
        insertNoteAsyncTask.execute(note);
    }

    public void delete(Note note) {
        DeleteNoteAsyncTask deleteNoteAsyncTask = new DeleteNoteAsyncTask(noteDao);
        deleteNoteAsyncTask.execute(note);

    }

    public void deleteAll() {
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }

}
