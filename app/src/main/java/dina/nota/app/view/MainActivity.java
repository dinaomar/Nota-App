package dina.nota.app.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.nota.R;
import android.arch.lifecycle.ViewModelProviders;
import dina.nota.app.viewmodel.NoteViewModel;

public class MainActivity extends AppCompatActivity {

    NoteViewModel noteViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
    }
}
