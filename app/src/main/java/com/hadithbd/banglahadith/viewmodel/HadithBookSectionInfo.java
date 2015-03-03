package com.hadithbd.banglahadith.viewmodel;

/**
 * Created by rayhan on 03/02/2015.
 */
public class HadithBookSectionInfo {
    private int chapterId;
    private String sectionName;
    private long hadithCount;

    public HadithBookSectionInfo(int chapterId, String sectionName, long hadithCount) {
        this.chapterId = chapterId;
        this.sectionName = sectionName;
        this.hadithCount = hadithCount;
    }

    public long getHadithCount() {
        return hadithCount;
    }

    public int getChapterId() {
        return chapterId;
    }

    public String getSectionName() {
        return sectionName;
    }
}
