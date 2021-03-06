package com.hadithbd.banglahadith.database.tables.hadith;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by rayhan on 02/23/2015.
 */
@DatabaseTable
public class HadithExplanation {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private int hadithId;
    @DatabaseField
    private String explanation;
    @DatabaseField
    private int isActive;
    @DatabaseField
    private String lastUpdate;

    public int getId() {
        return id;
    }

    public int getHadithId() {
        return hadithId;
    }

    public String getExplanation() {
        return explanation;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }
}
