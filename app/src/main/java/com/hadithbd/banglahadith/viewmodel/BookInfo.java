package com.hadithbd.banglahadith.viewmodel;

/**
 * Created by rayhan on 03/02/2015.
 */
public class BookInfo {
    private int bookId;
    private String bookName;
    private long questionCount;

    public BookInfo(int bookId, String bookName, long sectionCount) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.questionCount = sectionCount;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public long getQuestionCount() {
        return questionCount;
    }
}
