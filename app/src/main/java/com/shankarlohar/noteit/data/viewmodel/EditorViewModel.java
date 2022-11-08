package com.shankarlohar.noteit.data.viewmodel;


import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.shankarlohar.noteit.data.AppRepository;
import com.shankarlohar.noteit.data.model.NoteEntity;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EditorViewModel extends AndroidViewModel {

    public MutableLiveData<NoteEntity> mLiveNote = new MutableLiveData<>();
    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();
    public EditorViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(getApplication());
    }

    public void loadData(final int noteId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                NoteEntity note  = mRepository.getNoteById(noteId);
                mLiveNote.postValue(note);
            }
        });
    }

    public void saveNote(String noteText) {
        NoteEntity note = mLiveNote.getValue();
        if (note == null){
            if (TextUtils.isEmpty(noteText.trim())) {
                return;
            }else{
                note = new NoteEntity(new Date(),noteText,"New Topic");
            }
        }else{
            note.setText(noteText.trim());
            note.setTopic("Topic");
        }
        mRepository.insertNote(note);
    }
}
