package com.shankarlohar.noteit;

import static com.shankarlohar.noteit.utilities.Constants.NOTE_ID;
import static com.shankarlohar.noteit.utilities.Constants.NOTE_TITLE;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.MenuItem;
import android.view.View;

import com.shankarlohar.noteit.data.model.NoteEntity;
import com.shankarlohar.noteit.data.viewmodel.EditorViewModel;
import com.shankarlohar.noteit.data.viewmodel.MainViewModel;
import com.shankarlohar.noteit.databinding.ActivityEditorBinding;
import com.shankarlohar.noteit.databinding.ContentScrollingBinding;

public class EditorActivity extends AppCompatActivity {

    private ActivityEditorBinding binding;
    private EditorViewModel editorViewModel;
    private boolean mNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = binding.fab;
        initViewModel();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAndReturn();
            }
        });

    }

    private void saveAndReturn() {
        editorViewModel.saveNote(binding.contentScrolling.noteTextEdit.getText().toString());
        finish();
    }

    private void initViewModel() {
        editorViewModel = new ViewModelProvider(EditorActivity.this).get(EditorViewModel.class);
        editorViewModel.mLiveNote.observe(this, noteEntity ->{
            if (noteEntity != null){
                binding.toolbarLayout.setTitle(noteEntity.getTopic());
                binding.contentScrolling.noteTextEdit.setText(noteEntity.getText());
            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            binding.toolbarLayout.setTitle("New Note");
            mNewNote = true;
        } else {
            int noteId = extras.getInt(NOTE_ID);
            editorViewModel.loadData(noteId);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveAndReturn();
    }
}