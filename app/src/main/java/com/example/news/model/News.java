package com.example.news.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note_table")
public class News {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    public News(String title) {
        this.title = title;

    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
