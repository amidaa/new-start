package zhang.feng.com.eatwhat.goods;

import java.util.Date;
import java.util.List;

public class Issue {
    private String mContent;//内容
    private String mAuthor;//作者
    private String mData;//发布时间
    private String mSuspect;
    private List<String> mPhotoPath;//图片的路径

    public String getSuspect() {
        return mSuspect;
    }

    public List<String> getPhotoPath() {
        return mPhotoPath;
    }

    public void setPhotoPath(List<String> photoPath) {
        mPhotoPath = photoPath;
    }

    public Issue(){

    }
    public Issue(String mContent, String mAuthor, String mData,String suspect,List<String> photoPath) {
        this.mContent = mContent;
        this.mAuthor = mAuthor;
        this.mData = mData;
        this.mSuspect = suspect;
        this.mPhotoPath = photoPath;
    }
    public String getmContent() {
        return mContent;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmData() {
        return mData;
    }



    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public void setmData(String mData) {
        this.mData = mData;
    }

    public void setSuspect(String suspect){
        mSuspect = suspect;
    }

    public String getPhotoFilename(){
        return "IMG_"+getmAuthor()+".jpg";
    }

}
