package com.switube.www.landmark2018test.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.switube.www.landmark2018test.database.entity.EcoEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface EcoDao {
    @Insert void insert(List<EcoEntity> list);
    @Query("select * from eco_list") Maybe<List<EcoEntity>> get();
}
