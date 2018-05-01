package com.gomo.learndemo;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

/**
 * Created by deeper on 2018/5/1.
 */

public class DatabaseCreator {

    private static DatabaseCreator sInstance;

    private AppDatabase mAppDatabase;

    private static final Object LOCK = new Object();

    public synchronized static DatabaseCreator getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new DatabaseCreator();
                }
            }
        }
        return sInstance;
    }

    private DatabaseCreator() {
        mAppDatabase = Room.databaseBuilder(LearnApp.getAppContext(), AppDatabase.class, AppDatabase.DATABASE_NAME)
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build();
    }

    public AppDatabase getAppDatabase() {
        return mAppDatabase;
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Create the new table
            database.execSQL(
                    "CREATE TABLE `user_bean` (`date` TEXT, `user_id` INTEGER NOT NULL, `name` TEXT, `passwd` TEXT, PRIMARY KEY(`date`, `user_id`))");
        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Create the new table
            database.execSQL(
                    " CREATE TABLE `message_bean` (`id` INTEGER NOT NULL, `title` TEXT, `body` TEXT, PRIMARY KEY(`id`))");
        }
    };
}
