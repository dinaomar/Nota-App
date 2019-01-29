package dina.nota.app.viewmodel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import dina.nota.app.model.data.Note;
import dina.nota.app.model.repository.NotesRepository;

public class NoteViewModel extends AndroidViewModel {

    NotesRepository repository;
    LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NotesRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }

    public void update(Note note) {
        repository.update(note);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
