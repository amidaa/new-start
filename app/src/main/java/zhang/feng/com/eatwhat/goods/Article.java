package zhang.feng.com.eatwhat.goods;

import android.graphics.Bitmap;

import java.util.Date;
import java.util.UUID;

public class Article {
    private UUID mID;//唯一的编号
    private String mBriefIntroduction;//文章简介
    private Bitmap mIntroductionPhoto;//引导图
    private String mArticleName;//文章名字
    private String mArticleDetails;//文章内容
    private String mArticleAuthor;//文章作者
    private Date mDate;//文章发布时间


    public Article() {
        mID = UUID.randomUUID();
    }

    public Article(String mBriefIntroduction, Bitmap mIntroductionPhoto, String mArticleName, String mArticleDetails, String mArticleAuthor, Date mDate) {
        this.mID = UUID.randomUUID();
        this.mBriefIntroduction = mBriefIntroduction;
        this.mIntroductionPhoto = mIntroductionPhoto;
        this.mArticleName = mArticleName;
        this.mArticleDetails = mArticleDetails;
        this.mArticleAuthor = mArticleAuthor;
        this.mDate = mDate;
    }

    public String getmBriefIntroduction() {
        return mBriefIntroduction;
    }

    public void setmBriefIntroduction(String mBriefIntroduction) {
        this.mBriefIntroduction = mBriefIntroduction;
    }

    public Bitmap getmIntroductionPhoto() {
        return mIntroductionPhoto;
    }

    public void setmIntroductionPhoto(Bitmap mIntroductionPhoto) {
        this.mIntroductionPhoto = mIntroductionPhoto;
    }

    public UUID getmID() {
        return mID;
    }

    public void setmID(UUID mID) {
        this.mID = mID;
    }

    public String getmArticleName() {
        return mArticleName;
    }

    public void setmArticleName(String mArticleName) {
        this.mArticleName = mArticleName;
    }

    public String getmArticleDetails() {
        return mArticleDetails;
    }

    public void setmArticleDetails(String mArticleDetails) {
        this.mArticleDetails = mArticleDetails;
    }

    public String getmArticleAuthor() {
        return mArticleAuthor;
    }

    public void setmArticleAuthor(String mArticleAuthor) {
        this.mArticleAuthor = mArticleAuthor;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
}
