package com.hadithbd.banglahadith.viewmodel;

/**
 * Created by rayhan on 03/02/2015.
 */
public class BookTypeInfo {
    private int typeId;
    private String categoryName;
    private long bookCount;

    public BookTypeInfo(int typeId, String categoryName, long bookCount) {
        this.typeId = typeId;
        this.categoryName = categoryName;
        this.bookCount = bookCount;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public long getBookCount() {
        return bookCount;
    }
}
