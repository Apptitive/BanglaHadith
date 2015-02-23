package com.hadithbd.banglahadith.database.tables.book;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by rayhan on 02/23/2015.
 */
@DatabaseTable
public class BookType {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private String categoryName;
    @DatabaseField
    private int isActive;
    @DatabaseField
    private Date lastUpdate;
}
