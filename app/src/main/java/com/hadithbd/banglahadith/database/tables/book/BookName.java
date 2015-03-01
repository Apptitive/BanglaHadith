package com.hadithbd.banglahadith.database.tables.book;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by rayhan on 02/23/2015.
 */
@DatabaseTable
public class BookName {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private int writerId;
    @DatabaseField
    private int typeId;
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

    public int getWriterId() {
        return writerId;
    }

    public int getTypeId() {
        return typeId;
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
