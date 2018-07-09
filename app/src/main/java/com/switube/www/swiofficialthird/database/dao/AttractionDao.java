package com.switube.www.swiofficialthird.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.switube.www.swiofficialthird.database.entity.AttractionEntity;

import java.util.List;

@Dao
public interface AttractionDao {
    @Query("SELECT * FROM attraction")
    AttractionEntity getAttractionData();

    @Query("DELETE FROM attraction")
    void cleanTable();

    @Insert
    void insertAllData(List<AttractionEntity> attractionEntities);
}
