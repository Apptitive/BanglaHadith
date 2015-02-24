package com.hadithbd.banglahadith.database.tables.hadith;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by rayhan on 02/23/2015.
 */
@DatabaseTable
public class HadithPublisher {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private String nameBengali;
    @DatabaseField
    private String nameEnglish;
    @DatabaseField
    private int isActive;
    @DatabaseField
    private Date lastUpdate;

    public String getNameBengali(){
        return this.nameBengali;
    }
}
