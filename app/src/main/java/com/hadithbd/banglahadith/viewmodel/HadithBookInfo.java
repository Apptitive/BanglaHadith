package com.hadithbd.banglahadith.viewmodel;

/**
 * Created by Sharif on 3/1/2015.
 */
public class HadithBookInfo {

    private int bookId;
    private String bookName;
    private long sectionCount;
    private long hadithCount;

    public HadithBookInfo(int bookId, String bookName, long chapterCount, long hadithCount) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.sectionCount = chapterCount;
        this.hadithCount = hadithCount;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public long getSectionCount() {
        return sectionCount;
    }

    public long getHadithCount() {
        return hadithCount;
    }
}
