package com.hadithbd.banglahadith.database.tables.hadith;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by rayhan on 02/23/2015.
 */
@DatabaseTable
public class RabiHadith {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private String rabiBengali;
    @DatabaseField
    private String rabiEnglish;
    @DatabaseField
    private String sortBy;
    @DatabaseField
    private int isActive;
    @DatabaseField
    private String lastUpdate;

    public int getId() {
        return id;
    }

    public String getRabiBengali() {
        return rabiBengali;
    }

    public String getRabiEnglish() {
        return rabiEnglish;
    }

    public String getSortBy() {
        return sortBy;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }
}
