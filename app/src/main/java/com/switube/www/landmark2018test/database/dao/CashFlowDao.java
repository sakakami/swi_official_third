package com.switube.www.landmark2018test.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.switube.www.landmark2018test.database.entity.CashFlowEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface CashFlowDao {
    @Query("SELECT * FROM cash_flow WHERE maid = :maid")
    Maybe<List<CashFlowEntity>> getCashFlowDataWithRx(String maid);

    @Query("DELETE FROM cash_flow WHERE maid = :maid")
    void deleteData(String maid);

    @Query("DELETE FROM cash_flow")
    void cleanTable();

    @Insert
    void insertData(CashFlowEntity cashFlowEntity);
}
