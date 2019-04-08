package zhang.feng.com.eatwhat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    private ViewPager page;
    private List<ImageView> guidanceImages;
    private Button app_start;
    private ImageView[] dots;//三个点

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        page = (ViewPager)findViewById(R.id.guide);
        dots = new ImageView[3];
        dots[0] = (ImageView)findViewById(R.id.dot_one);
        dots[1] = (ImageView)findViewById(R.id.dot_two);
        dots[2] = (ImageView)findViewById(R.id.dot_three);
        app_start = (Button)findViewById(R.id.start_app);
        app_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        initView();
        initStatusBar();
    }
    private void initView(){
        fillData();
        setListener();

    }

    private void setListener(){
        page.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0;i<dots.length;i++){
                    if(position == i){
                        dots[i].setImageResource(R.drawable.point_selected);
                    }else{
                        dots[i].setImageResource(R.drawable.point_xml);
                    }
                }
                if(position == 2){
                    app_start.setVisibility(View.VISIBLE);
                }else{
                    app_start.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void fillData(){
        guidanceImages = new ArrayList<>();
        page.setAdapter(new MyPageAdapter());
    }


    public class MyPageAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 3;
        }

        //判断obj是否是view

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return object == view;
        }

//        移除一个item

        @Override

        public void destroyItem(ViewGroup container,int position,Object obj){
            ((ViewPager) container).removeView(guidanceImages.get(position));
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position){
            ImageView iv = null;
            if(position == 0){
                iv = new ImageView(WelcomeActivity.this);
                iv.setBackgroundResource(R.mipmap.bg3);
            }else if(position == 1){
                iv = new ImageView(WelcomeActivity.this);
                iv.setBackgroundResource(R.mipmap.tab6);

            }else{
                iv = new ImageView(WelcomeActivity.this);
                iv.setBackgroundResource(R.mipmap.bg5);
            }
            guidanceImages.add(iv);
            ((ViewPager)container).addView(iv);
            return iv;
        }

    }

    //实现沉浸式状态栏

    private void initStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
}
