package com.switube.www.landmark2018test.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface AttractionItemDao {
    @Query("SELECT * FROM attractionItem")
    Maybe<List<AttractionItemEntity>> getAttractionItemData();

    @Query("SELECT * FROM attractionItem WHERE mtid LIKE :mtid")
    Maybe<List<AttractionItemEntity>> getAttractionItemDataWithRx(String mtid);

    @Query("DELETE FROM attractionItem")
    void cleanTable();

    @Insert
    void insertAllData(List<AttractionItemEntity> attractionItemEntities);
}
