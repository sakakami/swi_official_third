package com.switube.www.swiofficialthird.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.switube.www.swiofficialthird.database.entity.LanguageEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface LanguageDao {
    @Query("SELECT * FROM language WHERE wordId = :wordId")
    LanguageEntity getDataFromWordId(String wordId);

    @Query("SELECT * FROM language WHERE wordId = :wordId")
    Maybe<LanguageEntity> getDataFromWordIdWithRx(String wordId);

    @Query("DELETE FROM language")
    void cleanTable();

    @Insert
    void insertAllData(List<LanguageEntity> languageEntities);
}
