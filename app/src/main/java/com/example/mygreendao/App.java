package com.example.mygreendao;

import android.app.Application;

import com.example.mygreendao.db.helper.DbManager;

/**
 * Created by tank on 2020/5/27.
 */
public class App extends Application {

    private DbManager mDbManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mDbManager = new DbManager(this);
    }

    public DbManager getDb() {
        return mDbManager;
    }
}
