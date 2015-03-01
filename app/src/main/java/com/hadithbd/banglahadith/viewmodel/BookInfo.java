package com.hadithbd.banglahadith.viewmodel;

/**
 * Created by Sharif on 3/1/2015.
 */
public class BookInfo {

    private int bookId;
    private String bookName;
    private int chapterCount;
    private int hadithCount;

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public int getChapterCount() {
        return chapterCount;
    }

    public int getHadithCount() {
        return hadithCount;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setChapterCount(int chapterCount) {
        this.chapterCount = chapterCount;
    }

    public void setHadithCount(int hadithCount) {
        this.hadithCount = hadithCount;
    }
}
