package dina.nota.data;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// create table notes (id integer primary key auto increment, content varchar (50));

public class NotesDao extends SQLiteOpenHelper {

	private static final String DB_NAME = "notes.db";
	private static final String NOTES_TABLE = "notes";
	private static final String NOTES_ID = "id";
	private static final String NOTES_CONTENT = "content";
	private static final String NOTE_TITLE = "title";
	private static final String NOTE_AUDIO_FILE_PATH = "filepath";

	public NotesDao(Context context) {
		super(context, DB_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE " + NOTES_TABLE + " (" + NOTES_ID
				+ " INTEGER PRIMARY KEY, " + NOTES_CONTENT + " varchar(320), "
				+ NOTE_TITLE + " varchar(320), " + NOTE_AUDIO_FILE_PATH + " varchar(320))");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {

		db.execSQL("DROP TABLE IF EXISTS " + NOTES_TABLE);

		onCreate(db);
	}

	public void addNote(Note t) {

		SQLiteDatabase db = getWritableDatabase();
		ContentValues vals = new ContentValues();
		vals.put(NOTES_CONTENT, t.getContent());
		vals.put(NOTE_TITLE, t.getTitle());
		vals.put(NOTE_AUDIO_FILE_PATH, t.getFilePath());
		db.insert(NOTES_TABLE, null, vals);
		db.close();

	}

	public void editNote(Note t) {

		SQLiteDatabase db = getWritableDatabase();
		ContentValues vals = new ContentValues();
		vals.put(NOTES_CONTENT, t.getContent());
		vals.put(NOTE_TITLE, t.getTitle());
		db.update(NOTES_TABLE, vals, NOTES_ID + " = ?",
				new String[] { String.valueOf(t.getId()) });
		db.close();

	}

	public void deleteNote(Note t) {
		SQLiteDatabase db = getWritableDatabase();

		db.delete(NOTES_TABLE, NOTES_ID + " = ?",
				new String[] { String.valueOf(t.getId()) });

		db.close();

	}
	
	public int getNote(Note t)
	{
		return t.getId();
	}

	public ArrayList<Note> getAllNotes() {

		ArrayList<Note> myNotes = new ArrayList<Note>();
		SQLiteDatabase db = getReadableDatabase();

		Cursor result = db.rawQuery("SELECT * FROM " + NOTES_TABLE, null);

		while (result.moveToNext()) {

			int strId = result.getInt(0); // id
			String content = result.getString(1); // content
			String title = result.getString(2);
			String audiofilePath = result.getString(3);
			
			Note temp = new Note();
			temp.setId(strId);
			temp.setContent(content);
			temp.setTitle(title);
			temp.setFilePath(audiofilePath);
			myNotes.add(temp);
		}

		return myNotes;
	}
}
