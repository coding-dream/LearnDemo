package com.gomo.learndemo.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.gomo.learndemo.bean.User;

/**
 * Created by deeper on 2018/5/1.
 */

@Dao
public interface UserDao {
    @Query("SELECT * FROM user where id = :userId")
    LiveData<User> getUser(int userId);

    @Query("SELECT * FROM user where id = :userId")
    User getUserNomal(int userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);
}
