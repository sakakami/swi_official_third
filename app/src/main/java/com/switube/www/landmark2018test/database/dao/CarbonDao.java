package com.switube.www.landmark2018test.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.switube.www.landmark2018test.database.entity.CarbonEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface CarbonDao {
    @Insert void insert(List<CarbonEntity> list);
    @Query("select * from carbon_list") Maybe<List<CarbonEntity>> get();
}
