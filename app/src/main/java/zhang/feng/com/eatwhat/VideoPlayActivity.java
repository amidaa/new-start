package zhang.feng.com.eatwhat;

import androidx.appcompat.app.AppCompatActivity;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import zhang.feng.com.eatwhat.vitamiovideo.MyMediaController;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VideoPlayActivity extends AppCompatActivity implements Runnable{
    private VideoView video;//视频播放器
    private Button startButton;//视频开始播放按钮
    private MediaController mMediaController;//视频控制器
    private AudioManager mAudioManager;
    private int mMaxVolume;//最大声音
    private int mVolume = -1;//当前声音
    private float mBrightness = -1f;//当前亮度
    private int mLayout = VideoView.VIDEO_LAYOUT_ZOOM;//当前缩放模式
    private GestureDetector mGestureDetector;//手势识别
    private MyMediaController mMyMediaController;//自定义视频播放控制器

    private static final int TIME = 0;
    private static final int BATTERY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        initView();
    }
    private void initView(){
        int flag_fullscreen = WindowManager.LayoutParams.FLAG_FULLSCREEN;//定义全屏参数
        Window window = VideoPlayActivity.this.getWindow();//获取当前窗体对象
        window.setFlags(flag_fullscreen,flag_fullscreen);//设置当前窗体全屏显示

        video = (VideoView)findViewById(R.id.video_player);
        String path = Environment.getExternalStorageDirectory().getPath()+"/video.mp4";
        Uri uri = Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");//将路径转换为uri
        video.setVideoURI(uri);//为视频播放器设置视频路径
        mMediaController = new MediaController(VideoPlayActivity.this);//实例化控制器
        mMyMediaController = new MyMediaController(VideoPlayActivity.this,video,this);
        mMediaController.show(5000);//控制器显示5s后自动隐藏

        video.setMediaController(mMyMediaController);//显示控制栏
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                video.start();
            }


        });

    }


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message message){
            switch (message.what){
                case TIME:
                    mMyMediaController.setTime(message.obj.toString());
                    break;
                case BATTERY:
                    mMyMediaController.setBattery(message.obj.toString());
                    break;
            }
        }
    };
    private BroadcastReceiver batterBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())){
                int battery_level = intent.getIntExtra("level",0);//获取当前电量
                int scale = intent.getIntExtra("scale",100);//电量总刻度
                Message msg = new Message();
                msg.obj = (battery_level*100)/scale+"";
                msg.what = BATTERY;
                mHandler.sendMessage(msg);
            }
        }
    };

    @Override
    public void run(){
        while (true){
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String str = sdf.format(new Date());
            Message message = new Message();
            message.obj = str;
            message.what = TIME;
            mHandler.sendMessage(message);
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
}
