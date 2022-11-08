package com.shankarlohar.noteit.data.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.shankarlohar.noteit.data.AppRepository;
import com.shankarlohar.noteit.data.model.NoteEntity;
import com.shankarlohar.noteit.utilities.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainViewModel extends AndroidViewModel {

    public LiveData<List<NoteEntity>> mNotes;
    private final AppRepository mRepository;

    private Executor executor = Executors.newSingleThreadExecutor();

    public MainViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mNotes = mRepository.mNotes;
    }

    public void addSampleData() {
        mRepository.addSampleData();
    }

    public void deleteAllNotes() {
        mRepository.deleteAllNotes();
    }

    public void deleteNote(int noteId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                NoteEntity node = mRepository.getNoteById(noteId);
                mRepository.deleteNote(node);
            }
        });
    }


}
