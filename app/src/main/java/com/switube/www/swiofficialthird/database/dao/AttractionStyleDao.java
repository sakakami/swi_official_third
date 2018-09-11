package com.switube.www.swiofficialthird.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface AttractionStyleDao {
    @Query("SELECT * FROM AttractionStyle")
    List<AttractionStyleEntity> getAttractionStyleData();

    @Query("SELECT * FROM AttractionStyle")
    Maybe<List<AttractionStyleEntity>> getAttractionStyleDataWithRx();

    @Query("DELETE FROM AttractionStyle")
    void cleanTable();

    @Insert
    void insertAllData(List<AttractionStyleEntity> attractionStyleEntities);
}
