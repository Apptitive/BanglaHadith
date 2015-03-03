package com.hadithbd.banglahadith.database;

import android.content.Context;

import com.hadithbd.banglahadith.database.tables.book.BookContent;
import com.hadithbd.banglahadith.database.tables.book.BookName;
import com.hadithbd.banglahadith.database.tables.book.BookSection;
import com.hadithbd.banglahadith.database.tables.book.BookType;
import com.hadithbd.banglahadith.database.tables.book.BookWriter;
import com.hadithbd.banglahadith.database.tables.hadith.HadithBook;
import com.hadithbd.banglahadith.database.tables.hadith.HadithChapter;
import com.hadithbd.banglahadith.database.tables.hadith.HadithExplanation;
import com.hadithbd.banglahadith.database.tables.hadith.HadithMain;
import com.hadithbd.banglahadith.database.tables.hadith.HadithPublisher;
import com.hadithbd.banglahadith.database.tables.hadith.HadithSection;
import com.hadithbd.banglahadith.database.tables.hadith.HadithStatus;
import com.hadithbd.banglahadith.database.tables.hadith.RabiHadith;
import com.hadithbd.banglahadith.viewmodel.BookContentInfo;
import com.hadithbd.banglahadith.viewmodel.BookContentTitleInfo;
import com.hadithbd.banglahadith.viewmodel.BookInfo;
import com.hadithbd.banglahadith.viewmodel.BookTypeInfo;
import com.hadithbd.banglahadith.viewmodel.HadithBookChapterInfo;
import com.hadithbd.banglahadith.viewmodel.HadithBookInfo;
import com.hadithbd.banglahadith.viewmodel.HadithMainInfo;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rayhan on 02/24/2015.
 */
public class DbManager {
    static private DbManager instance;
    private DbHelper helper;

    private DbManager(Context ctx) {
        helper = new DbHelper(ctx);
    }

    static public void init(Context ctx) {
        if (null == instance) {
            instance = new DbManager(ctx);
        }
    }

    static public DbManager getInstance() {
        return instance;
    }

    private DbHelper getHelper() {
        return helper;
    }

    public List<BookWriter> getAllBookWriters() {
        List<BookWriter> bookWriterList = new ArrayList<>();
        try {
            bookWriterList = getHelper().getBookWriterDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookWriterList;
    }

    public List<BookType> getAllBookTypes() {
        List<BookType> bookTypeList = new ArrayList<>();
        try {
            bookTypeList = getHelper().getBookTypeDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookTypeList;
    }

    public List<BookName> getAllBookNames() {
        List<BookName> bookNameList = new ArrayList<>();
        try {
            bookNameList = getHelper().getBookNameDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookNameList;
    }

    public List<BookContent> getAllBookContents() {
        List<BookContent> bookContentList = new ArrayList<>();
        try {
            bookContentList = getHelper().getBookContentDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookContentList;
    }

    public List<BookSection> getAllBookSections() {
        List<BookSection> bookSectionList = new ArrayList<>();
        try {
            bookSectionList = getHelper().getBookSectionDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookSectionList;
    }

    public List<RabiHadith> getAllRabiHadiths() {
        List<RabiHadith> rabiHadithList = new ArrayList<>();
        try {
            rabiHadithList = getHelper().getRabiHadithDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rabiHadithList;
    }

    public List<HadithStatus> getAllHadithStatus() {
        List<HadithStatus> hadithStatusList = new ArrayList<>();
        try {
            hadithStatusList = getHelper().getHadithStatusDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hadithStatusList;
    }

    public List<HadithSection> getAllHadithSections() {
        List<HadithSection> hadithSectionList = new ArrayList<>();
        try {
            hadithSectionList = getHelper().getHadithSectionDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hadithSectionList;
    }

    public List<HadithPublisher> getAllHadithPublishers() {
        List<HadithPublisher> hadithPublisherList = new ArrayList<>();
        try {
            hadithPublisherList = getHelper().getHadithPublisherDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hadithPublisherList;
    }

    public List<HadithMain> getAllHadithMains() {
        List<HadithMain> hadithMainList = new ArrayList<>();
        try {
            hadithMainList = getHelper().getHadithMainDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hadithMainList;
    }


    public List<HadithExplanation> getAllHadithExplanations() {
        List<HadithExplanation> hadithExplanationList = new ArrayList<>();
        try {
            hadithExplanationList = getHelper().getHadithExplanationDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hadithExplanationList;
    }

    public List<HadithChapter> getAllHadithChapters() {
        List<HadithChapter> hadithChapterList = new ArrayList<>();
        try {
            hadithChapterList = getHelper().getHadithChapterDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hadithChapterList;
    }

    public List<HadithBook> getAllHadithBooks() {
        List<HadithBook> hadithBookList = new ArrayList<>();
        try {
            hadithBookList = getHelper().getHadithBookDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hadithBookList;
    }

    public HadithBook getHadithBook(int hadithBookId) {
        HadithBook entity = new HadithBook();
        QueryBuilder<HadithBook, Integer> qb = getHelper().getHadithBookDao().queryBuilder();
        Where<HadithBook, Integer> where = qb.where();
        try {
            where.eq("id", hadithBookId);
            entity = where.query().get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public List<HadithChapter> getHadithChaptersForBook(int bookId) {
        List<HadithChapter> chapterList = new ArrayList<>();
        QueryBuilder<HadithChapter, Integer> qb = getHelper().getHadithChapterDao().queryBuilder();
        Where<HadithChapter, Integer> where = qb.where();
        try {
            where.eq("bookId", bookId);
            chapterList = where.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapterList;
    }

    public List<HadithBookChapterInfo> getHadithBookChapterInfo(int bookId) {
        List<HadithChapter> chapterList = getHadithChaptersForBook(bookId);
        List<HadithBookChapterInfo> hadithBookChapterInfoList = new ArrayList<>();

        for (HadithChapter chapter : chapterList) {
            long hadithCount = 0;
            QueryBuilder<HadithMain, Integer> hadithainQueryBuilder = getHelper().getHadithMainDao().queryBuilder();
            Where<HadithMain, Integer> whereHadithMain = hadithainQueryBuilder.where();

            try {
                hadithCount = whereHadithMain.eq("chapterId", chapter.getId()).countOf();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            hadithBookChapterInfoList.add(new HadithBookChapterInfo(chapter.getId(), chapter.getNameBengali(), hadithCount));
        }

        return hadithBookChapterInfoList;
    }

    public long getHadithCount() {
        try {
            return getHelper().getHadithMainDao().countOf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<HadithBookInfo> getAllHadithBookInfo() {
        List<HadithBook> hadithBookList = getAllHadithBooks();
        List<HadithBookInfo> hadithBookInfoList = new ArrayList<>();

        for (HadithBook hadithBook : hadithBookList) {
            long chapterCount = 0, hadithCount = 0;
            QueryBuilder<HadithChapter, Integer> hadithChapterQueryBuilder = getHelper().getHadithChapterDao().queryBuilder();
            Where<HadithChapter, Integer> whereHadithChapter = hadithChapterQueryBuilder.where();

            QueryBuilder<HadithMain, Integer> hadithainQueryBuilder = getHelper().getHadithMainDao().queryBuilder();
            Where<HadithMain, Integer> whereHadithMain = hadithainQueryBuilder.where();

            try {
                chapterCount = whereHadithChapter.eq("bookId", hadithBook.getId()).countOf();
                hadithCount = whereHadithMain.eq("bookId", hadithBook.getId()).countOf();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            hadithBookInfoList.add(new HadithBookInfo(hadithBook.getId(), hadithBook.getNameBengali(), chapterCount, hadithCount));
        }

        return hadithBookInfoList;
    }

    public List<Integer> getHadithNoListForChapter(int chapterId) {
        List<HadithMain> mainList = new ArrayList<>();
        QueryBuilder<HadithMain, Integer> hadithMainQueryBuilder = getHelper().getHadithMainDao().queryBuilder();
        Where<HadithMain, Integer> whereHadithMain = hadithMainQueryBuilder.where();
        try {
            whereHadithMain.eq("chapterId", chapterId);
            mainList = whereHadithMain.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Integer> hadithIdList = new ArrayList<>();
        for (HadithMain main : mainList) {
            hadithIdList.add(main.getHadithNo());
        }
        return hadithIdList;
    }

    public HadithMain getHadithMainWithHadithNo(int hadithNo) {
        HadithMain hadithMain = new HadithMain();
        QueryBuilder<HadithMain, Integer> hadithMainQueryBuilder = getHelper().getHadithMainDao().queryBuilder();
        Where<HadithMain, Integer> whereHadithMain = hadithMainQueryBuilder.where();
        try {
            whereHadithMain.eq("hadithNo", hadithNo);
            hadithMain = whereHadithMain.query().get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hadithMain;
    }

    public String getRabiBengali(int rabiId) {
        RabiHadith entity = new RabiHadith();
        QueryBuilder<RabiHadith, Integer> qb = getHelper().getRabiHadithDao().queryBuilder();
        Where<RabiHadith, Integer> where = qb.where();
        try {
            where.eq("id", rabiId);
            entity = where.query().get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity.getRabiBengali();
    }

    public String getHadithBookName(int hadithBookId) {
        HadithBook entity = new HadithBook();
        QueryBuilder<HadithBook, Integer> qb = getHelper().getHadithBookDao().queryBuilder();
        Where<HadithBook, Integer> where = qb.where();
        try {
            where.eq("id", hadithBookId);
            entity = where.query().get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity.getNameBengali();
    }

    public String getHadithSectionBengali(int sectionId) {
        HadithSection entity = new HadithSection();
        QueryBuilder<HadithSection, Integer> qb = getHelper().getHadithSectionDao().queryBuilder();
        Where<HadithSection, Integer> where = qb.where();
        try {
            where.eq("id", sectionId);
            entity = where.query().get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity.getNameBengali();
    }

    public HadithChapter getHadithChapter(int chapterId) {
        HadithChapter entity = new HadithChapter();
        QueryBuilder<HadithChapter, Integer> qb = getHelper().getHadithChapterDao().queryBuilder();
        Where<HadithChapter, Integer> where = qb.where();
        try {
            where.eq("id", chapterId);
            entity = where.query().get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public String getHadithStatusBengali(int statusId) {
        HadithStatus entity = new HadithStatus();
        QueryBuilder<HadithStatus, Integer> qb = getHelper().getHadithStatusDao().queryBuilder();
        Where<HadithStatus, Integer> where = qb.where();
        try {
            where.eq("id", statusId);
            entity = where.query().get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity.getStatusBengali();
    }

    public String getHadithExplanation(int hadithId){
        HadithExplanation entity = new HadithExplanation();
        QueryBuilder<HadithExplanation, Integer> qb = getHelper().getHadithExplanationDao().queryBuilder();
        Where<HadithExplanation, Integer> where = qb.where();
        try {
            where.eq("hadithId", hadithId);
            entity = where.query().get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity.getExplanation();
    }

    public HadithMainInfo getHadithInformationForHadith(int hadithNo) {
        HadithMain main = getHadithMainWithHadithNo(hadithNo);

        HadithMainInfo mainInfo = new HadithMainInfo();
        mainInfo.setId(main.getId());
        mainInfo.setRabiId(main.getRabiId());
        mainInfo.setRabiBengali(getRabiBengali(main.getRabiId()));
        mainInfo.setBookId(main.getBookId());
        mainInfo.setBookName(getHadithBookName(main.getBookId()));
        mainInfo.setSectionId(main.getSectionId());
        mainInfo.setSectionBengali(getHadithSectionBengali(main.getSectionId()));
        mainInfo.setChapterId(main.getChapterId());
        mainInfo.setChapterArabic(getHadithChapter(main.getChapterId()).getNameArabic());
        mainInfo.setChapterBengali(getHadithChapter(main.getChapterId()).getNameBengali());
        mainInfo.setStatusId(main.getStatusId());
        mainInfo.setStatusBengali(getHadithStatusBengali(main.getStatusId()));
        mainInfo.setHadithNo(main.getHadithNo());
        mainInfo.setHadithArabic(main.getHadithArabic());
        mainInfo.setHadithBengali(main.getHadithBengali());
        mainInfo.setHadithEnglish(main.getHadithEnglish());
        mainInfo.setHadithExplanation(getHadithExplanation(main.getId()));
        mainInfo.setNote(main.getNote());
        mainInfo.setCheckStatus(main.getCheckStatus());

        return mainInfo;
    }

    public List<BookTypeInfo> getAllBookTypeInfo() {
        List<BookType> bookTypeList = getAllBookTypes();
        List<BookTypeInfo> bookTypeInfoList = new ArrayList<>();

        for (BookType bookType : bookTypeList) {
            long bookCount = 0;
            QueryBuilder<BookName, Integer> bookNameQueryBuilder = getHelper().getBookNameDao().queryBuilder();
            Where<BookName, Integer> whereBookName = bookNameQueryBuilder.where();

            try {
                bookCount = whereBookName.eq("typeId", bookType.getId()).countOf();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            bookTypeInfoList.add(new BookTypeInfo(bookType.getId(), bookType.getCategoryName(), bookCount));
        }

        return bookTypeInfoList;
    }

    public List<BookName> getAllBookNamesForTypeId(int typeId) {
        List<BookName> mainList = new ArrayList<>();
        QueryBuilder<BookName, Integer> qb = getHelper().getBookNameDao().queryBuilder();
        Where<BookName, Integer> where = qb.where();
        try {
            where.eq("typeId", typeId);
            mainList = where.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mainList;
    }

    public List<BookInfo> getAllBookInfoForType(int typeId) {
        List<BookName> bookList = getAllBookNamesForTypeId(typeId);
        List<BookInfo> bookInfoList = new ArrayList<>();

        for (BookName book : bookList) {
            long questionCount = 0;
            QueryBuilder<BookContent, Integer> qb = getHelper().getBookContentDao().queryBuilder();
            Where<BookContent, Integer> where = qb.where();

            try {
                questionCount = where.eq("bookId", book.getId()).countOf();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            bookInfoList.add(new BookInfo(book.getId(), book.getNameBengali(), questionCount));
        }

        return bookInfoList;
    }

    public List<BookContentTitleInfo> getContentTitleInfoForBook(int bookId) {
        List<BookContent> contentList = new ArrayList<>();
        List<BookContentTitleInfo> contentInfoList = new ArrayList<>();
        QueryBuilder<BookContent, Integer> qb = getHelper().getBookContentDao().queryBuilder();
        Where<BookContent, Integer> where = qb.where();
        try {
            where.eq("bookId", bookId);
            contentList = where.query();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (BookContent content : contentList) {
            contentInfoList.add(new BookContentTitleInfo(content.getId(), content.getQuestion()));
        }
        return contentInfoList;
    }

    public BookContent getBookContentWithId(int contentId) {
        BookContent bookContent = new BookContent();
        QueryBuilder<BookContent, Integer> qb = getHelper().getBookContentDao().queryBuilder();
        Where<BookContent, Integer> where = qb.where();
        try {
            where.eq("id", contentId);
            bookContent = where.query().get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookContent;
    }

    public String getBookNameWithId(int bookId) {
        BookName entity = new BookName();
        QueryBuilder<BookName, Integer> qb = getHelper().getBookNameDao().queryBuilder();
        Where<BookName, Integer> where = qb.where();
        try {
            where.eq("id", bookId);
            entity = where.query().get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity.getNameBengali();
    }

    public String getBookSectionNameWithId(int sectionId) {
        BookSection entity = new BookSection();
        QueryBuilder<BookSection, Integer> qb = getHelper().getBookSectionDao().queryBuilder();
        Where<BookSection, Integer> where = qb.where();
        try {
            where.eq("id", sectionId);
            entity = where.query().get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity.getName();
    }

    public BookContentInfo getBookContentInfo(int contentId) {
        BookContent content = getBookContentWithId(contentId);
        BookContentInfo contentInfo = new BookContentInfo();

        contentInfo.setId(content.getId());
        contentInfo.setBookId(content.getBookId());
        contentInfo.setBookName(getBookNameWithId(content.getBookId()));
        contentInfo.setSectionId(content.getSectionId());
        contentInfo.setSectionName(getBookSectionNameWithId(content.getSectionId()));
        contentInfo.setQuestion(content.getQuestion());
        contentInfo.setAnswer(content.getAnswer());
        contentInfo.setNote(content.getNote());

        return contentInfo;
    }

}


