package com.switube.www.swiofficialthird.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.switube.www.swiofficialthird.database.entity.AttractionClassEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface AttractionClassDao {
    @Query("SELECT * FROM attractionClass")
    List<AttractionClassEntity> getAttractionClassData();

    @Query("SELECT * FROM attractionClass WHERE msid = :msid")
    Maybe<List<AttractionClassEntity>> getAttractionClassDataWithRx(String msid);

    @Query("DELETE FROM attractionClass")
    void cleanTable();

    @Insert
    void insertAllData(List<AttractionClassEntity> attractionClassEntities);
}
