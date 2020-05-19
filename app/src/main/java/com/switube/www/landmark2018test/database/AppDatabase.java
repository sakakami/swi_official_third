package com.switube.www.landmark2018test.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.database.dao.AttractionClassDao;
import com.switube.www.landmark2018test.database.dao.AttractionItemDao;
import com.switube.www.landmark2018test.database.dao.AttractionModeDao;
import com.switube.www.landmark2018test.database.dao.AttractionStyleDao;
import com.switube.www.landmark2018test.database.dao.AttractionTermDao;
import com.switube.www.landmark2018test.database.dao.CarbonDao;
import com.switube.www.landmark2018test.database.dao.EcoDao;
import com.switube.www.landmark2018test.database.dao.MapPlaceBaseDataDao;
import com.switube.www.landmark2018test.database.dao.MapPlaceBaseSubDataDao;
import com.switube.www.landmark2018test.database.dao.MusicDao;
import com.switube.www.landmark2018test.database.dao.TagQBADao;
import com.switube.www.landmark2018test.database.dao.TagQBNDao;
import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionModeEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;
import com.switube.www.landmark2018test.database.entity.CarbonEntity;
import com.switube.www.landmark2018test.database.entity.EcoEntity;
import com.switube.www.landmark2018test.database.entity.MapPlaceBaseDataEntity;
import com.switube.www.landmark2018test.database.entity.MapPlaceBaseSubDataEntity;
import com.switube.www.landmark2018test.database.entity.MusicEntity;
import com.switube.www.landmark2018test.database.entity.TagQBAEntity;
import com.switube.www.landmark2018test.database.entity.TagQBNEntity;

@Database(entities = {AttractionClassEntity.class,
        AttractionItemEntity.class, AttractionModeEntity.class, AttractionTermEntity.class,
        AttractionStyleEntity.class, MapPlaceBaseDataEntity.class, MapPlaceBaseSubDataEntity.class,
        TagQBNEntity.class, TagQBAEntity.class, MusicEntity.class,
        CarbonEntity.class, EcoEntity.class},
        version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sAppDatabase;
    private static final String DATABASE_NAME = "swi_official_database";
    public abstract AttractionClassDao attractionClassDao();
    public abstract AttractionItemDao attractionItemDao();
    public abstract AttractionModeDao attractionModeDao();
    public abstract AttractionStyleDao attractionStyleDao();
    public abstract AttractionTermDao attractionTermDao();
    public abstract MapPlaceBaseDataDao mapPlaceBaseDataDao();
    public abstract MapPlaceBaseSubDataDao mapPlaceBaseSubDataDao();
    public abstract CarbonDao carbonDao();
    public abstract TagQBNDao tagQBNDao();
    public abstract TagQBADao tagQBADao();
    public abstract MusicDao musicDao();
    public abstract EcoDao ecoDao();
    public static AppDatabase getInstance() {
        if (sAppDatabase == null) {
            synchronized (AppDatabase.class) {
                if (sAppDatabase == null) {
                    sAppDatabase = handleBuildDatabase();
                }
            }
        }
        return sAppDatabase;
    }

    @NonNull
    private static AppDatabase handleBuildDatabase() {
        return Room.databaseBuilder(MyApplication.getInstance(), AppDatabase.class, DATABASE_NAME).build();
    }
}
