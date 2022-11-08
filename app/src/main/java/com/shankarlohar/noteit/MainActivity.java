package com.shankarlohar.noteit;

import static com.shankarlohar.noteit.utilities.Constants.NOTE_ID;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shankarlohar.noteit.data.model.NoteEntity;
import com.shankarlohar.noteit.data.viewmodel.MainViewModel;
import com.shankarlohar.noteit.databinding.ActivityMainBinding;
import com.shankarlohar.noteit.ui.NotesAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<NoteEntity> notesData = new ArrayList<>();
    private NotesAdapter mAdapter;

    private MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        initRecyclerView();
        initViewModel();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,EditorActivity.class);
                startActivity(intent);
            }
        });


        Log.i("notee", "onCreate: ");


    }

    private void initViewModel() {
        final Observer<List<NoteEntity>> notesObserver = new Observer<List<NoteEntity>>() {
            @Override
            public void onChanged(List<NoteEntity> noteEntities) {
                notesData.clear();
                notesData.addAll(noteEntities);
                if (mAdapter==null){
                mAdapter = new NotesAdapter(notesData, MainActivity.this, mainViewModel);
                binding.recyclerView.setAdapter(mAdapter);
            }else{
                    mAdapter.notifyDataSetChanged();
                }
            }
        };
        mainViewModel = new ViewModelProvider(MainActivity.this).get(MainViewModel.class);
        mainViewModel.mNotes.observe(this,notesObserver);

    }

    private void initRecyclerView() {
        binding.recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.add_sample_data){
            addSampleData();
            return true;
        }
        else if(id == R.id.action_delete_all){
            deleteAllNotes();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllNotes() {
        mainViewModel.deleteAllNotes();
    }

    private void addSampleData() {
        mainViewModel.addSampleData();
    }
}