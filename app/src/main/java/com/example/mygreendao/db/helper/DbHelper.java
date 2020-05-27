package com.example.mygreendao.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mygreendao.db.DaoMaster;
import com.example.mygreendao.db.UserDao;

import org.greenrobot.greendao.database.Database;

/**
 * Created by tank on 2020/5/27.
 */
public class DbHelper extends DaoMaster.OpenHelper {
    public DbHelper(Context context, String name) {
        super(context, name);
    }

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by migrating all tables data");
        MigrationHelper.getInstance().migrate(db, UserDao.class);
    }
}
