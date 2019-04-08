package zhang.feng.com.eatwhat.goods;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class VideoLab {
    private static VideoLab sVideoLab;
    private List<Video> mVideos;
    public static VideoLab get(Context context){
        if(sVideoLab ==null){
            sVideoLab = new VideoLab(context);
        }
        return sVideoLab;
    }
    private VideoLab (Context context){
        mVideos = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        for(int number = 0;number<=1;number++){
            Video video = new Video();
            video.setVideoContent("https://gslb.miaopai.com/stream/P4DnrjGZ7PzC2LfQK9k2cAKEIw39GiixIBpIHA__.mp4");
            video.setCoverImage("https://i01piccdn.sogoucdn.com/3eba47e952f474d8");
            video.setVideoName("冬季健康养生小妙招"+number);
            video.setPostTime(date);
            mVideos.add(video);
        }
    }

    public List<Video> getVideos(){return mVideos;}//获取数组

    public Video getVideo(UUID id){
        for(Video video:mVideos){
            if(video.getVideoId().equals(id)){
                return video;
            }
        }
        return null;
    }
}
