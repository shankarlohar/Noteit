package com.shankarlohar.noteit.data.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "notes")
public class NoteEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private Date date;
    private String topic;
    private String text;

    @Ignore
    public NoteEntity() {
    }


    public NoteEntity(int id, Date date, String text, String topic) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.topic = topic;
    }

    @Ignore
    public NoteEntity(Date date, String text, String topic) {
        this.date = date;
        this.text = text;
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "NoteEntity{" +
                "id=" + id +
                ", date=" + date +
                ", topic='" + topic + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
