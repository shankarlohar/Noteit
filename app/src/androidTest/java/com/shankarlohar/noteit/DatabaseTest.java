package com.shankarlohar.noteit;


import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.shankarlohar.noteit.data.AppDatabase;
import com.shankarlohar.noteit.data.NoteDao;
import com.shankarlohar.noteit.data.model.NoteEntity;
import com.shankarlohar.noteit.utilities.SampleData;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    public static final String TAG = "JUnit";
    private AppDatabase mDb;
    private NoteDao mDao;

    @Before
    public void createDb(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context,AppDatabase.class).build();
        mDao = mDb.noteDao();
        Log.i(TAG, "createDb: ");
    }

    @After
    public void closeDb(){
        mDb.close();
        Log.i(TAG, "closeDb: ");
    }

    @Test
    public void createAndRetrieveNotes(){
        mDao.insertNotes(SampleData.getNotes());
        int count = mDao.getCount();
        Log.i(TAG, "createAndRetrieveNotes: ");
        Assert.assertEquals(SampleData.getNotes().size(),count);

    }

    @Test
    public void compareStrings(){
        mDao.insertNotes(SampleData.getNotes());
        NoteEntity original = SampleData.getNotes().get(0);
        NoteEntity fromDb = mDao.getNoteById(1);
        Log.i(TAG, "compareStrings: ");
        Assert.assertEquals(original.getText(),fromDb.getText());

    }
}
