package com.hadithbd.banglahadith.database.tables.hadith;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by rayhan on 02/23/2015.
 */
@DatabaseTable
public class HadithStatus {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private String statusBengali;
    @DatabaseField
    private String statusEnglish;
    @DatabaseField
    private String colCode;
    @DatabaseField
    private int isActive;
    @DatabaseField
    private String lastUpdate;

    public int getId() {
        return id;
    }

    public String getStatusBengali() {
        return statusBengali;
    }

    public String getStatusEnglish() {
        return statusEnglish;
    }

    public String getColCode() {
        return colCode;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }
}
