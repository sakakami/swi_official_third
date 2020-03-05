package com.switube.www.landmark2018test.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.switube.www.landmark2018test.database.entity.TagQBNEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface TagQBNDao {
    @Query("SELECT * FROM tagQBN WHERE qbid = :qbid")
    Maybe<List<TagQBNEntity>> getSelectTag(String qbid);

    @Query("DELETE FROM tagQBN")
    void handleClearTable();

    @Insert
    void handleInsertAllData(List<TagQBNEntity> tagQBNEntities);
}
