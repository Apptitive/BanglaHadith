package com.hadithbd.banglahadith.database.tables.book;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by rayhan on 02/23/2015.
 */

@DatabaseTable
public class BookContent {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private int bookId;
    @DatabaseField
    private int sectionId;
    @DatabaseField
    private String question;
    @DatabaseField
    private String answer;
    @DatabaseField
    private String note;
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

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getNote() {
        return note;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }
}
