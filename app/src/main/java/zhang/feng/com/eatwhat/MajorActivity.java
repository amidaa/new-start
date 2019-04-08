package zhang.feng.com.eatwhat;

import android.graphics.Color;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import zhang.feng.com.eatwhat.fragment.ChatFragment;
import zhang.feng.com.eatwhat.fragment.HomeFragment;
import zhang.feng.com.eatwhat.fragment.MyFragmentAdapter;
import zhang.feng.com.eatwhat.fragment.NewsFragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

public class MajorActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener {
    private AHBottomNavigation bottomNavigation;
    private List<Fragment> fragments = new ArrayList<>();//四个界面
    private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.major_layout);

        addBottomItems();
    }


    private void addBottomItems(){
        bottomNavigation = (AHBottomNavigation)findViewById(R.id.bottom_navigation);//底部导航栏
        viewPager = (ViewPager)findViewById(R.id.myviewpager);



        //添加元素
        AHBottomNavigationItem everydaymeal = new AHBottomNavigationItem(R.string.everyday_meal,R.drawable.tab_1,R.color.little_blue);
        AHBottomNavigationItem chatplace = new AHBottomNavigationItem(R.string.intelligent_test,R.drawable.tab_2,R.color.little_blue);
        AHBottomNavigationItem news = new AHBottomNavigationItem(R.string.recommend,R.drawable.tab_3,R.color.little_blue);
        AHBottomNavigationItem test = new AHBottomNavigationItem(R.string.chat_zone,R.drawable.tab_4,R.color.little_blue);


        //添加元素
        bottomNavigation.addItem(everydaymeal);
        bottomNavigation.addItem(chatplace);
        bottomNavigation.addItem(news);
        bottomNavigation.addItem(test);


        //添加背景色
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#54FF9F"));

        //禁止转化
        bottomNavigation.setBehaviorTranslationEnabled(false);

        //允许
//        bottomNavigation.manageFloatingActionButtonBehavior();

        //设置颜色变换,选中的图标着色以及文本的颜色
        bottomNavigation.setAccentColor(Color.parseColor("#FFDEAD"));
//      未选中的图标着色及文本颜色
        bottomNavigation.setInactiveColor(Color.parseColor("#54FF9F"));


        //给drawable染色
        bottomNavigation.setForceTint(true);

        //在导航中展示颜色
        bottomNavigation.setTranslucentNavigationEnabled(true);

        //设置标题显示状态
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);


        //显示圆形效果
        bottomNavigation.setColored(true);

        //
        bottomNavigation.setCurrentItem(0);

        //定做通知
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setNotification("1",3);



        //

        Fragment homeFragment = new HomeFragment();
        Fragment assessmentFragment = new ChatFragment();
        Fragment chatplceFragment = new ChatFragment();
        Fragment newsFragment = new NewsFragment();

        //添加界面元素到列表
        fragments.add(homeFragment);//智能营养
        fragments.add(assessmentFragment);//测评
        fragments.add(chatplceFragment);//交流广场
        fragments.add(newsFragment);//推荐


        //
        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(myFragmentAdapter);
        viewPager.setOffscreenPageLimit(3);//设置缓存页面数
        //添加监听
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position){
                    case 0:
                        viewPager.setCurrentItem(0);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        break;
                    case 3:
                        viewPager.setCurrentItem(3);
                        break;
                    default:
                        break;

                }
                return true;
            }

        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {

            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("-------------------onResume:");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("-------------------onStart:");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("-------------------onPause:");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("-------------------onStop:");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("-------------------onRestart:");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
