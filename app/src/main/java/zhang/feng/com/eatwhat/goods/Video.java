package zhang.feng.com.eatwhat.goods;

import java.util.Date;
import java.util.UUID;

public class Video {
    private UUID mVideoId;//视频编号
    private String mVideoName;//视频名字
    private Date mPostTime;//视频的发行时间
    private String mVideoContent;//视频地址
    private String mCoverImage;//视频封面图片

    public String getCoverImage() {
        return mCoverImage;
    }

    public void setCoverImage(String coverImage) {
        mCoverImage = coverImage;
    }

    public Video(String videoName, Date postTime, String videoContent, String coverImage) {
        this.mVideoId = UUID.randomUUID();
        mVideoName = videoName;
        mPostTime = postTime;
        mVideoContent = videoContent;
        mCoverImage = coverImage;
    }

    public Video() {
        this.mVideoId = UUID.randomUUID();
    }

    public UUID getVideoId() {
        return mVideoId;
    }

    public void setVideoId(UUID videoId) {
        mVideoId = videoId;
    }

    public String getVideoName() {
        return mVideoName;
    }

    public void setVideoName(String videoName) {
        mVideoName = videoName;
    }

    public Date getPostTime() {
        return mPostTime;
    }

    public void setPostTime(Date postTime) {
        mPostTime = postTime;
    }

    public String getVideoContent() {
        return mVideoContent;
    }

    public void setVideoContent(String videoContent) {
        mVideoContent = videoContent;
    }

}
