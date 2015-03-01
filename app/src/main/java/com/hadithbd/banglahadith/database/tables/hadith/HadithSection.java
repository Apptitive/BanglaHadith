package com.hadithbd.banglahadith.database.tables.hadith;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by rayhan on 02/23/2015.
 */
@DatabaseTable
public class HadithSection {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private int bookId;
    @DatabaseField
    private String nameBengali;
    @DatabaseField
    private String nameEnglish;
    @DatabaseField
    private int isActive;
    @DatabaseField
    private String lastUpdate;

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public String getNameBengali() {
        return nameBengali;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }
}
