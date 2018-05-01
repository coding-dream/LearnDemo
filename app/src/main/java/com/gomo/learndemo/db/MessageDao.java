package com.gomo.learndemo.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.gomo.learndemo.bean.MessageBean;

import java.util.List;

/**
 * Created by deeper on 2018/5/1.
 */

@Dao
public interface MessageDao {

    @Query("SELECT * FROM message order by id DESC")
    LiveData<List<MessageBean>> loadAllMessage();

    @Query("SELECT COUNT(*) from message where title = :title")
    LiveData<Integer> getMsgCountBy(String title);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MessageBean messageBean);

    @Delete
    void delete(MessageBean messageBean);
}
