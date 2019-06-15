package com.example.news;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.news.model.News;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NoteDao {

    @Insert()
    void insert(News news);

    @Update
    void update(News news);

//    @Delete
//    void delete(Note note);

    @Query("SELECT * FROM note_table ")
    LiveData<List<News>> getAllNotes();
}

