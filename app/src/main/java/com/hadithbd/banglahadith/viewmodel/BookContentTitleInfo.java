package com.hadithbd.banglahadith.viewmodel;

/**
 * Created by rayhan on 03/03/2015.
 */
public class BookContentTitleInfo {
    private int bookContentId;
    private String question;

    public BookContentTitleInfo(int bookContentId, String question) {
        this.bookContentId = bookContentId;
        this.question = question;
    }

    public int getBookContentId() {
        return bookContentId;
    }

    public String getQuestion() {
        return question;
    }
}
