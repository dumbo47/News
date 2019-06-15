package com.example.news;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.news.model.News;
import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<News>> allNotes;

    public NoteRepository(Application application) {
        NoteDataBase database = NoteDataBase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(News news) {
        new InsertNoteAsyncTask(noteDao).execute(news);
    }

    public void update(News news) {
        new UpdateNoteAsyncTask(noteDao).execute(news);
    }
//
//    public void delete(News news) {
//        new DeleteNoteAsyncTask(noteDao).execute(news);
//    }


    public LiveData<List<News>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<News, Void, Void> {
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(News... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<News, Void, Void> {
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(News... news) {
            noteDao.update(news[0]);
            return null;
        }
    }

//    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
//        private NoteDao noteDao;
//
//        private DeleteNoteAsyncTask(NoteDao noteDao) {
//            this.noteDao = noteDao;
//        }
//
//        @Override
//        protected Void doInBackground(Note... notes) {
//            noteDao.delete(notes[0]);
//            return null;
//        }
//    }


}
