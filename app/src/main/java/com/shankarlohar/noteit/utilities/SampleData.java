package com.shankarlohar.noteit.utilities;

import com.shankarlohar.noteit.data.model.NoteEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleData {
    private static final String SAMPLE_TEXT_1 = "A simple note";
    private static final String SAMPLE_TEXT_2 = "A simple note with additional\n lines of text";
    private static final String SAMPLE_TEXT_3 = "Terrance knew that sometimes it was simply best to stay out of it. He kept repeating this to himself as he watched the scene unfold. He knew that nothing good would come of him getting involved. It was far better for him to stay on the sidelines and observe. He kept yelling this to himself inside his head as he walked over to the couple and punched the man in the face.";
    private static Date getDate(int diff){
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MILLISECOND,diff);
        return cal.getTime();
    }
    public static List<NoteEntity> getNotes(){
        List<NoteEntity> notes = new ArrayList<>();
        notes.add(new NoteEntity(getDate(0),SAMPLE_TEXT_1,"Topic 1"));
        notes.add(new NoteEntity(getDate(-1),SAMPLE_TEXT_2,"Topic 2"));
        notes.add(new NoteEntity(getDate(-2),SAMPLE_TEXT_3,"Topic 3"));
        return notes;
    }
}
