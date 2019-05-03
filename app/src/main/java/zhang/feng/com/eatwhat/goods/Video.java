package zhang.feng.com.eatwhat.goods;

public class Video {
    private Integer videoId;//视频编号
    private String videoNameString;//视频名称
    private String videoAuthor;//视频作者
    private String videoDate;//视频发布时间
    private String videoCover;//封面
    private String videoUrl;//视频地址



    public Video() {

    }

    public Video(Integer videoId, String videoName, String videoAuthor, String videoDate, String videoCover, String videoUrl) {
        this.videoId = videoId;
        this.videoNameString = videoName;
        this.videoAuthor = videoAuthor;
        this.videoDate = videoDate;
        this.videoCover = videoCover;
        this.videoUrl = videoUrl;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoNameString() {
        return videoNameString;
    }

    public void setVideoNameString(String videoNameString) {
        this.videoNameString = videoNameString;
    }

    public String getVideoAuthor() {
        return videoAuthor;
    }

    public void setVideoAuthor(String videoAuthor) {
        this.videoAuthor = videoAuthor;
    }

    public String getVideoDate() {
        return videoDate;
    }

    public void setVideoDate(String videoDate) {
        this.videoDate = videoDate;
    }

    public String getVideoCover() {
        return videoCover;
    }

    public void setVideoCover(String videoCover) {
        this.videoCover = videoCover;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
