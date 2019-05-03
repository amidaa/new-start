package zhang.feng.com.eatwhat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

//主入口 启动app欢迎界面，延时功能和判断功能

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;//实例化SharePreferences,配置记录文件
    private SharedPreferences.Editor mEditor;
    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = this.getSharedPreferences("check",MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();//将SharedPreferences存储，可编辑化


        final Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);//初始化跳转欢迎引导界面
        final Intent loginInt = new Intent(MainActivity.this,LoginActivity.class);
        Timer timer = new Timer();//创建一个定时器
        firstLoad(timer,intent,loginInt);

    }

    public void firstLoad(Timer timer,Intent intent,Intent loginIntent){

        boolean firstLoadUI = mSharedPreferences.getBoolean("firstLoadUI",true);
        if(firstLoadUI){
            delayTime(timer,intent);
            mEditor.putBoolean("firstLoadUI",false);//第一次启动后，将firstLoadUI置为false
            mEditor.commit();//提交，执行操作
        }else {
            delayTime(timer,loginIntent);

        }

    }
    private void delayTime(Timer timer, final Intent intent){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);//执行进入
                finish();//释放当前页面
            }
        };
        timer.schedule(task,1000*2);//1秒后进入
    }

    private void initStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

}
