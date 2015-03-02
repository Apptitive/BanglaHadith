package com.hadithbd.banglahadith.viewmodel;

/**
 * Created by rayhan on 03/02/2015.
 */
public class HadithBookChapterInfo {
    private int chapterId;
    private String chapterName;
    private long hadithCount;

    public HadithBookChapterInfo(int chapterId, String chapterName, long hadithCount) {
        this.chapterId = chapterId;
        this.chapterName = chapterName;
        this.hadithCount = hadithCount;
    }

    public long getHadithCount() {
        return hadithCount;
    }

    public int getChapterId() {
        return chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }
}
