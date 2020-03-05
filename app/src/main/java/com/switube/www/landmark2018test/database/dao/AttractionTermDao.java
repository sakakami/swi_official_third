package com.switube.www.landmark2018test.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface AttractionTermDao {
    @Query("SELECT * FROM attractionTerm WHERE msid = :msid")
    Maybe<List<AttractionTermEntity>> getAttractionTermDataWithRx(String msid);

    @Query("DELETE FROM attractionTerm")
    void cleanTable();

    @Insert
    void insertAllData(List<AttractionTermEntity> attractionTermEntities);
}
