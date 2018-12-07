package dina.nota.main;

import java.io.IOException;

import sound.record.SoundRecorder;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.nota.R;

import dina.nota.data.Note;
import dina.nota.data.NotesDao;

public class SingleNoteView extends Activity {

	EditText editTextTitle, editTextContent;
	Button saveBtn, playBtn;
	String noteTitle, noteContent, audioFilePath;
	NotesDao notesdao;
	int noteID;
	Note note;
	MediaPlayer mPlayer;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_note_view);

		initialize();

		ActionBar mActionBar = getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);

		View mCustomView = mInflater.inflate(R.layout.note_custom_actionbar,
				null);

		ImageView editButton = (ImageView) mCustomView
				.findViewById(R.id.editButton);
		ImageView deletButton = (ImageView) mCustomView
				.findViewById(R.id.deleteButton);
		deletButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				notesdao = new NotesDao(getApplicationContext());
				note.setId(noteID);
				notesdao.deleteNote(note);
				finish();
			}
		});
		editButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				editTextTitle.setInputType(EditorInfo.TYPE_CLASS_TEXT);
				editTextContent.setInputType(EditorInfo.TYPE_CLASS_TEXT);
			}
		});

		mActionBar.setCustomView(mCustomView);
		mActionBar.setDisplayShowCustomEnabled(true);

	}

	private void initialize() {

		editTextTitle = (EditText) findViewById(R.id.editTexttitle);
		editTextContent = (EditText) findViewById(R.id.editTextcontent);
		saveBtn = (Button) findViewById(R.id.btnsave);
		playBtn = (Button) findViewById(R.id.playBtn);
		saveBtn.setVisibility(View.GONE);

		Bundle extras = getIntent().getExtras();
		noteTitle = extras.getString("title");
		noteContent = extras.getString("content");
		audioFilePath = extras.getString("filePath");
		noteID = extras.getInt("id", 0);

		editTextTitle.setText(noteTitle);
		editTextContent.setText(noteContent);

		TextWatcher textWatcher = new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				saveBtn.setVisibility(View.VISIBLE);
				noteTitle = editTextTitle.getText().toString();
				noteContent = editTextContent.getText().toString();

			}

			@Override
			public void afterTextChanged(Editable s) {

			}

		};

		editTextTitle.addTextChangedListener(textWatcher);
		editTextContent.addTextChangedListener(textWatcher);

		note = new Note();

		saveBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				saveFun();
				Intent i = new Intent(SingleNoteView.this, MainActivity.class);
				startActivity(i);
				finish();
			}
		});

		playBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mPlayer = new MediaPlayer();
				try {
					mPlayer.setDataSource(audioFilePath);
					mPlayer.prepare();
					mPlayer.start();
				} catch (IOException e) {
					Log.e("!!!!", "prepare() failed");
				}
			}
		});

	}

	private void saveFun() {

		notesdao = new NotesDao(getApplicationContext());
		note.setTitle(noteTitle);
		note.setContent(noteContent);
		note.setId(noteID);
		notesdao.editNote(note);

	}

}
