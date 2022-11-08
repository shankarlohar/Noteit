package com.shankarlohar.noteit.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.shankarlohar.noteit.data.model.NoteEntity;
import com.shankarlohar.noteit.utilities.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static AppRepository repositoryInstance;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public LiveData<List<NoteEntity>> mNotes;
    public static AppRepository getInstance(Context context){
        if (repositoryInstance == null){
             repositoryInstance = new AppRepository(context);
        }
        return repositoryInstance;
    }
    private AppRepository(Context context){

        mDb = AppDatabase.getInstance(context);
        mNotes = getAllNotes();

    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().insertNotes(SampleData.getNotes());
            }
        });
    }

    private LiveData<List<NoteEntity>> getAllNotes(){
            return mDb.noteDao().getAll();
    }

    public void deleteAllNotes() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().deleteAll();
            }
        });
    }

    public NoteEntity getNoteById(int noteId) {
        return mDb.noteDao().getNoteById(noteId);
    }

    public void insertNote(NoteEntity note) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().insertNote(note);
            }
        });
    }

    public void deleteNote(NoteEntity note) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().deleteNote(note);
            }
        });
    }
}
