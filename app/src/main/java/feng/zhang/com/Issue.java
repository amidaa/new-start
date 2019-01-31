package feng.zhang.com;

import java.util.Date;

public class Issue {
    private String mContent;//内容
    private String mAuthor;//作者
    private Date mData;//发布时间


    public Issue(){

    }
    public Issue(String mContent, String mAuthor, Date mData) {
        this.mContent = mContent;
        this.mAuthor = mAuthor;
        this.mData = mData;
    }
    public String getmContent() {
        return mContent;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public Date getmData() {
        return mData;
    }



    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public void setmData(Date mData) {
        this.mData = mData;
    }
}
