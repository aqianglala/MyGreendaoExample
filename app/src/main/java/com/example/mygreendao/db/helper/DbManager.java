package com.example.mygreendao.db.helper;

import android.content.Context;

import com.example.mygreendao.db.DaoMaster;
import com.example.mygreendao.db.DaoSession;
import com.example.mygreendao.db.User;
import com.example.mygreendao.db.UserDao;

import org.greenrobot.greendao.database.Database;

import java.util.List;

/**
 * Created by tank on 2020/5/27.
 */
public class DbManager {

    private Context mContext;
    private DaoSession mDaoSession;

    public DbManager(Context context) {
        this.mContext = context;
        DbHelper helper = new DbHelper(context, "test-db");
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    public boolean insert(User user) {
        long insert = mDaoSession.getUserDao().insert(user);
        return insert > 0;
    }

    public void delete(User user) {
        mDaoSession.getUserDao().delete(user);
    }

    public void update(User user) {
        mDaoSession.getUserDao().update(user);
    }

    public List<User> getAll() {
        return mDaoSession.getUserDao().loadAll();
    }

    public List<User> getByName(String name) {
        return mDaoSession.getUserDao().queryBuilder()
                .where(UserDao.Properties.Name.eq(name))
                .list();
    }
}
