package dina.nota.main;

import java.io.IOException;
import java.util.ArrayList;

import sound.record.SoundRecorder;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nota.R;

import dina.nota.data.Note;
import dina.nota.data.NotesDao;

public class AddNewNote extends Activity {

	Button btnAdd, btnRecord, btnStop, btnPlay;
	EditText noteContent, noteTitle;
	NotesDao database;
	Note t;
	String contentdata, titledata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_note);

		initialize();

	}

	private void initialize() {

		btnAdd = (Button) findViewById(R.id.btnAddNote);
		btnRecord = (Button) findViewById(R.id.btnRecord);
		btnStop = (Button) findViewById(R.id.btnStop);
		btnPlay = (Button) findViewById(R.id.btnPlay);
		btnPlay.setVisibility(View.GONE);
		noteContent = (EditText) findViewById(R.id.NoteContent);
		noteTitle = (EditText) findViewById(R.id.NoteTitle);

		t = new Note();
		final SoundRecorder soundRecorder = new SoundRecorder();

		btnAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				contentdata = noteContent.getText().toString();
				titledata = noteTitle.getText().toString();

				if (contentdata.equals("") && titledata.equals("")) {
					Toast.makeText(getApplicationContext(),
							"Enter text to add", Toast.LENGTH_LONG).show();
					return;
				}

				t.setContent(contentdata);
				t.setTitle(titledata);
				t.setFilePath(soundRecorder.setAudioFileName());
				database = new NotesDao(getApplicationContext());
				database.addNote(t);
				noteContent.setText("");
				noteTitle.setText("");
				finish();

			}
		});

		btnRecord.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				soundRecorder.startRecording();

			}

		});

		btnStop.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				soundRecorder.stopRecording();
				btnPlay.setVisibility(View.VISIBLE);
				soundRecorder.startPlaying();
			}
		});

		btnPlay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				soundRecorder.startPlaying();
			}
		});
	}

}
