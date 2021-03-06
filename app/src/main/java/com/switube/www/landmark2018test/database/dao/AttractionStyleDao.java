package com.switube.www.landmark2018test.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface AttractionStyleDao {
    @Query("SELECT * FROM AttractionStyle")
    Maybe<List<AttractionStyleEntity>> getAttractionStyleDataWithRx();

    @Query("SELECT * FROM attractionStyle WHERE msid = :msid")
    Maybe<AttractionStyleEntity> getAttractionStyleDataWitchRx(String msid);

    @Query("DELETE FROM AttractionStyle")
    void cleanTable();

    @Query("SELECT * FROM attractionStyle WHERE msid IN (:msid)")
    Maybe<List<AttractionStyleEntity>> getSelectedStyle(List<String> msid);

    @Insert
    void insertAllData(List<AttractionStyleEntity> attractionStyleEntities);
}
