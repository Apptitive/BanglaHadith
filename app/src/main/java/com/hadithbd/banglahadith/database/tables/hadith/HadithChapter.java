package com.hadithbd.banglahadith.database.tables.hadith;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by rayhan on 02/23/2015.
 */
@DatabaseTable
public class HadithChapter {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private int bookId;
    @DatabaseField
    private int sectionId;
    @DatabaseField
    private String nameBengali;
    @DatabaseField
    private String nameArabic;
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

    public int getSectionId() {
        return sectionId;
    }

    public String getNameBengali() {
        return nameBengali;
    }

    public String getNameArabic() {
        return nameArabic;
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
