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
    private Date lastUpdate;
}
