package com.switube.www.landmark2018test.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface AttractionClassDao {
    @Query("SELECT * FROM attractionClass WHERE msid = :msid")
    Maybe<List<AttractionClassEntity>> getAttractionClassDataWithRx(String msid);

    @Query("SELECT * FROM attractionClass WHERE mscid = :mscid")
    Maybe<AttractionClassEntity> getAttractionClassData(String mscid);

    @Query("DELETE FROM attractionClass")
    void cleanTable();

    @Insert
    void insertAllData(List<AttractionClassEntity> attractionClassEntities);
}
