package com.switube.www.swiofficialthird.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.switube.www.swiofficialthird.database.dao.AttractionClassDao;
import com.switube.www.swiofficialthird.database.dao.AttractionDao;
import com.switube.www.swiofficialthird.database.dao.AttractionItemDao;
import com.switube.www.swiofficialthird.database.dao.AttractionModeDao;
import com.switube.www.swiofficialthird.database.dao.AttractionStyleDao;
import com.switube.www.swiofficialthird.database.dao.AttractionTermDao;
import com.switube.www.swiofficialthird.database.dao.LanguageDao;
import com.switube.www.swiofficialthird.database.dao.MapPlaceBaseDataDao;
import com.switube.www.swiofficialthird.database.entity.AttractionClassEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionItemEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionModeEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionTermEntity;
import com.switube.www.swiofficialthird.database.entity.LanguageEntity;
import com.switube.www.swiofficialthird.database.entity.MapPlaceBaseDataEntity;

@Database(entities = {LanguageEntity.class, AttractionEntity.class, AttractionClassEntity.class,
        AttractionItemEntity.class, AttractionModeEntity.class, AttractionTermEntity.class,
        AttractionStyleEntity.class, MapPlaceBaseDataEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sAppDatabase;
    private static final String DATABASE_NAME = "swi_official_database";
    public abstract LanguageDao languageDao();
    public abstract AttractionDao attractionDao();
    public abstract AttractionClassDao attractionClassDao();
    public abstract AttractionItemDao attractionItemDao();
    public abstract AttractionModeDao attractionModeDao();
    public abstract AttractionStyleDao attractionStyleDao();
    public abstract AttractionTermDao attractionTermDao();
    public abstract MapPlaceBaseDataDao mapPlaceBaseDataDao();
    public static AppDatabase getInstance(Context context) {
        if (sAppDatabase == null) {
            synchronized (AppDatabase.class) {
                if (sAppDatabase == null) {
                    sAppDatabase = handleBuildDatabase(context);
                }
            }
        }
        return sAppDatabase;
    }

    @NonNull
    private static AppDatabase handleBuildDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
    }
}
