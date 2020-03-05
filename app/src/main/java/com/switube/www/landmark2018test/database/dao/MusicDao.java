package com.switube.www.landmark2018test.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.switube.www.landmark2018test.database.entity.MusicEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface MusicDao {
    @Insert
    void handleInsertAllData(List<MusicEntity> musicEntities);

    @Query("DELETE FROM musicDetail")
    void clearTable();

    @Query("SELECT * FROM musicDetail ORDER BY name ASC")
    Maybe<List<MusicEntity>> getAllDataByNameA();

    @Query("SELECT * FROM musicDetail ORDER BY atdate ASC")
    Maybe<List<MusicEntity>> getAllDataByDateA();

    @Query("SELECT * FROM musicDetail ORDER BY atdate DESC")
    Maybe<List<MusicEntity>> getAllDataByDateD();

    @Query("SELECT * FROM musicDetail ORDER BY RANDOM()")
    Maybe<List<MusicEntity>> getAllDataByRandom();

    @Query("SELECT * FROM musicDetail ORDER BY LENGTH(views) DESC, views DESC")
    Maybe<List<MusicEntity>> getAllDataByViewD();

    @Query("SELECT * FROM musicDetail ORDER BY LENGTH(lengths) ASC, lengths ASC")
    Maybe<List<MusicEntity>> getAllDataByLengthA();

    @Query("SELECT * FROM musicDetail ORDER BY LENGTH(lengths) DESC, lengths DESC")
    Maybe<List<MusicEntity>> getAllDataByLengthD();

    @Query("SELECT * FROM musicDetail WHERE xx = :xx AND yy = :yy ORDER BY RANDOM()")
    Maybe<List<MusicEntity>> getAllDataOrderByRandom(String xx, String yy);

    @Query("SELECT * FROM musicDetail WHERE (xx = :xx1 OR xx = :xx2 OR xx = :xx3) AND (yy = :yy1 OR yy = :yy2 OR yy = :yy3) ORDER BY RANDOM()")
    Maybe<List<MusicEntity>> getAllDataOrderByRandom(String xx1, String xx2, String xx3, String yy1, String yy2, String yy3);

    @Query("SELECT * FROM musicDetail WHERE (xx = :xx1 OR xx = :xx2 OR xx = :xx3 OR xx = :xx4 OR xx = :xx5) AND (yy = :yy1 OR yy = :yy2 OR yy = :yy3 OR yy = :yy4 OR yy = :yy5) ORDER BY RANDOM()")
    Maybe<List<MusicEntity>> getAllDataOrderByRandom(String xx1, String xx2, String xx3, String xx4, String xx5, String yy1, String yy2, String yy3, String yy4, String yy5);

    @Query("SELECT * FROM musicDetail WHERE name LIKE :keyword ORDER BY RANDOM()")
    Maybe<List<MusicEntity>> getSearchDataByRandom(String keyword);

}
