package com.hadithbd.banglahadith.viewmodel;

/**
 * Created by rayhan on 03/02/2015.
 */
public class BookTypeInfo {
    private int typeId;
    private String categoryNae;
    private long bookCount;

    public BookTypeInfo(int typeId, String categoryNae, long bookCount) {
        this.typeId = typeId;
        this.categoryNae = categoryNae;
        this.bookCount = bookCount;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getCategoryNae() {
        return categoryNae;
    }

    public long getBookCount() {
        return bookCount;
    }
}
