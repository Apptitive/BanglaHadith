package com.hadithbd.banglahadith.database.tables.book;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by rayhan on 02/23/2015.
 */

@DatabaseTable
public class BookSection {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private int bookId;
    @DatabaseField
    private String name;
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

    public String getName() {
        return name;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }
}
