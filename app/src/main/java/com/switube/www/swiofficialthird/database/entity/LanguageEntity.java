package com.switube.www.swiofficialthird.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "language")
public class LanguageEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String wordId;
    private String wordEn;
    private String wordTw;
    private String wordCn;
    private String wordJp;
    private String status;
    public LanguageEntity(String wordId, String wordEn, String wordTw, String wordCn, String wordJp,
                          String status) {
        this.wordId = wordId;
        this.wordEn = wordEn;
        this.wordTw = wordTw;
        this.wordCn = wordCn;
        this.wordJp = wordJp;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getWordId() {
        return wordId;
    }

    public String getWordEn() {
        return wordEn;
    }

    public String getWordTw() {
        return wordTw;
    }

    public String getWordCn() {
        return wordCn;
    }

    public String getWordJp() {
        return wordJp;
    }

    public String getStatus() {
        return status;
    }
}
