package com.switube.www.landmark2018test.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.switube.www.landmark2018test.database.entity.TagQBAEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface TagQBADao {
    @Query("SELECT * FROM tagQBA WHERE qbnid = :qbnid")
    Maybe<List<TagQBAEntity>> getSelectTag(String qbnid);

    @Query("DELETE FROM tagQBA")
    void handleClearTable();

    @Insert
    void handleInsertAllData(List<TagQBAEntity> tagQBAEntities);
}
