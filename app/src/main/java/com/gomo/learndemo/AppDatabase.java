package com.gomo.learndemo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.gomo.learndemo.bean.MessageBean;
import com.gomo.learndemo.bean.User;
import com.gomo.learndemo.db.MessageDao;
import com.gomo.learndemo.db.UserDao;

/**
 * Created by deeper on 2018/5/1.
 */

@Database(entities = {MessageBean.class, User.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    static final String DATABASE_NAME = "learn_db";

    public abstract UserDao getUserDao();

    public abstract MessageDao getMessageDao();
}