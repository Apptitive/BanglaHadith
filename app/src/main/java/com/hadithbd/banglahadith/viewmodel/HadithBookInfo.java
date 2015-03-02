package com.hadithbd.banglahadith.viewmodel;

/**
 * Created by Sharif on 3/1/2015.
 */
public class HadithBookInfo {

    private int bookId;
    private String bookName;
    private long chapterCount;
    private long hadithCount;

    public HadithBookInfo(int bookId, String bookName, long chapterCount, long hadithCount) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.chapterCount = chapterCount;
        this.hadithCount = hadithCount;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public long getChapterCount() {
        return chapterCount;
    }

    public long getHadithCount() {
        return hadithCount;
    }
}
