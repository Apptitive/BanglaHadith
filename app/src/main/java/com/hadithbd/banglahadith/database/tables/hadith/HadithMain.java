package com.hadithbd.banglahadith.database.tables.hadith;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by rayhan on 02/23/2015.
 */
@DatabaseTable
public class HadithMain {
    @DatabaseField
    private int id;
    @DatabaseField
    private int rabiId;
    @DatabaseField
    private int bookId;
    @DatabaseField
    private int sectionId;
    @DatabaseField
    private int chapterId;
    @DatabaseField
    private int publisherId;
    @DatabaseField
    private int statusId;
    @DatabaseField
    private int hadithNo;
    @DatabaseField
    private String hadithArabic;
    @DatabaseField
    private String hadithBengali;
    @DatabaseField
    private String hadithEnglish;
    @DatabaseField
    private String note;
    @DatabaseField
    private int checkStatus;
    @DatabaseField
    private int isActive;
    @DatabaseField
    private Date lastUpdate;
}
