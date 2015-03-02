package com.hadithbd.banglahadith.database.tables.hadith;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

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
    private String lastUpdate;

    public int getId() {
        return id;
    }

    public int getRabiId() {
        return rabiId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public int getStatusId() {
        return statusId;
    }

    public int getHadithNo() {
        return hadithNo;
    }

    public String getHadithArabic() {
        return hadithArabic;
    }

    public String getHadithBengali() {
        return hadithBengali;
    }

    public String getHadithEnglish() {
        return hadithEnglish;
    }

    public String getNote() {
        return note;
    }

    public int getCheckStatus() {
        return checkStatus;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }
}
