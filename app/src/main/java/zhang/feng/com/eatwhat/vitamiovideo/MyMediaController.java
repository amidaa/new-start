package zhang.feng.com.eatwhat.vitamiovideo;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import zhang.feng.com.eatwhat.R;
import zhang.feng.com.eatwhat.goods.Video;

public class MyMediaController extends MediaController {

    private GestureDetector mGestureDetector;//手势检测
    private ImageButton img_back;//返回键
    private ImageView img_battery;//电池电量显示
    private TextView textView_time;//时间提示
    private TextView textView_Battery;//文字显示电池
    private VideoView mVideoView;
    private Activity mActivity;
    private Context mContext;
    private int controllerWidth = 0;//设置mediaController高度为了使横屏时top显示在屏幕顶端

    //返回监听
    private View.OnClickListener backListener = new View.OnClickListener(){
        public void onClick(View view){
            if(mActivity != null){
                mActivity.finish();
            }
        }

    };



    public MyMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //videoview 用于对视频进行控制的等，activity为了退出
    public MyMediaController(Context context, VideoView videoView , Activity activity) {
        super(context);
        this.mContext = context;
        this.mVideoView = videoView;
        this.mActivity = activity;
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        controllerWidth = wm.getDefaultDisplay().getWidth();
        mGestureDetector = new GestureDetector(context, new MyGestureListener());
    }

    @Override
    protected View makeControllerView() {
        View v = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(getResources().getIdentifier("videoplaycontroller", "layout", getContext().getPackageName()), this);
        v.setMinimumHeight(controllerWidth);
        img_back = (ImageButton) v.findViewById(R.id.mediacontroller_top_back);
        img_battery = (ImageView) v.findViewById(R.id.mediacontroller_imgBattery);
        img_back.setOnClickListener(backListener);
        textView_Battery = (TextView)v.findViewById(R.id.mediacontroller_Battery);
        textView_time = (TextView)v.findViewById(R.id.mediacontroller_time);

        return v;

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        System.out.println("MYApp-MyMediaController-dispatchKeyEvent");
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event)) return true;
        // 处理手势结束
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            //当收拾结束，并且是单击结束时，控制器隐藏/显示
            toggleMediaControlsVisiblity();
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
        //双击暂停或开始
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            playOrPause();
            return true;
        }
    }



    public void setTime(String time){
        if (textView_time != null)
            textView_time.setText(time);
    }
    //显示电量，
    public void setBattery(String stringBattery){
        if(textView_time != null && img_battery != null){
            textView_Battery.setText( stringBattery + "%");
//            int battery = Integer.valueOf(stringBattery);
//            if(battery < 15)img_battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_15));
//            if(battery < 30 && battery >= 15)img_battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_15));
//            if(battery < 45 && battery >= 30)img_battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_30));
//            if(battery < 60 && battery >= 45)img_battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_45));
//            if(battery < 75 && battery >= 60)img_battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_60));
//            if(battery < 90 && battery >= 75)img_battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_75));
//            if(battery > 90 )img_battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_90));
        }
    }
    //隐藏/显示
    private void toggleMediaControlsVisiblity(){
        if (isShowing()) {
            hide();
        } else {
            show();
        }
    }
    //播放与暂停
    private void playOrPause() {
        if (mVideoView != null)
            if (mVideoView.isPlaying()) {
                mVideoView.pause();
            } else {
                mVideoView.start();
            }
    }
}
