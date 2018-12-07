package dina.nota.main;

import java.util.ArrayList;

import com.example.nota.R;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import dina.nota.data.*;

public class MainActivity extends ActionBarActivity {

	ArrayList<String> ar;
	public static ArrayList<Note> all_notes;
	CustomListAdapter adapter;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar mActionBar = getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);

		View mCustomView = mInflater.inflate(R.layout.main_custom_actionbar, null);

		ImageView addButton = (ImageView) mCustomView
				.findViewById(R.id.addButton);
		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				Intent t = new Intent(getApplicationContext(), AddNewNote.class);
				startActivity(t);
			}
		});

		mActionBar.setCustomView(mCustomView);
		mActionBar.setDisplayShowCustomEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();

		final NotesDao dao = new NotesDao(getApplicationContext());
		all_notes = dao.getAllNotes();

		ar = new ArrayList<String>();
		for (int i = 0; i < all_notes.size(); i++) {
			ar.add(all_notes.get(i).getId() + "-" + all_notes.get(i).getTitle());

		}

		ListView lv = (ListView) findViewById(R.id.listView);

		adapter = new CustomListAdapter(getApplicationContext(), ar);

		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				String title = all_notes.get(position).getTitle();
				String content = all_notes.get(position).getContent();
				String file_path = all_notes.get(position).getFilePath();
				
				Intent i = new Intent(MainActivity.this,SingleNoteView.class);
				i.putExtra("title", title);
				i.putExtra("content", content);
				i.putExtra("filePath", file_path);
				i.putExtra("id", all_notes.get(position).getId());
				startActivity(i);
				
			}
			
		});
		
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Note t =new Note();
				t.setId(position+1);
				dao.deleteNote(t);
				return false;
			}
			
			
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
//		super.onBackPressed();
		 moveTaskToBack(true);
	}
}
