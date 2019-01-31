package zhang.feng.com.eatwhat;

import android.content.Intent;
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
import feng.zhang.com.fragment.ChatFragment;
import feng.zhang.com.fragment.HomeFragment;
import feng.zhang.com.fragment.MyFragmentAdapter;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
    private DrawerLayout mDrawerLayout;
    private Button bloodpressure;//血压
    private Button heartrate;//心率
    private AHBottomNavigation bottomNavigation;
    private List<Fragment> fragments = new ArrayList<>();//四个界面
    private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.major_layout);
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        bloodpressure = (Button)findViewById(R.id.bloodpressure);
        heartrate = (Button)findViewById(R.id.heartrate);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();



        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu_launch);
        }
        navigationView.setCheckedItem(R.id.nav_friend);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Data delete",Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MajorActivity.this,"Data restored",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
//        bloodpressure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MajorActivity.this,BloodPressureChartView.class);
//                startActivity(intent);
//            }
//        });
//        heartrate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MajorActivity.this,EnergyChartView.class);
//                startActivity(intent);
//            }
//        });
        addBottomItems();
    }


    private void addBottomItems(){
        bottomNavigation = (AHBottomNavigation)findViewById(R.id.bottom_navigation);//底部导航栏
        viewPager = (ViewPager)findViewById(R.id.myviewpager);



        //添加元素
        AHBottomNavigationItem everydaymeal = new AHBottomNavigationItem(R.string.app_name,R.drawable.carrot,R.color.colorPrimary);
        AHBottomNavigationItem chatplace = new AHBottomNavigationItem(R.string.app_name,R.drawable.carrot,R.color.colorPrimary);
        AHBottomNavigationItem news = new AHBottomNavigationItem(R.string.app_name,R.drawable.carrot,R.color.colorPrimary);
        AHBottomNavigationItem test = new AHBottomNavigationItem(R.string.app_name,R.drawable.carrot,R.color.colorPrimary);


        //添加元素
        bottomNavigation.addItem(everydaymeal);
        bottomNavigation.addItem(chatplace);
        bottomNavigation.addItem(news);
        bottomNavigation.addItem(test);


        //添加背景色
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

        //禁止转化
        bottomNavigation.setBehaviorTranslationEnabled(false);

        //允许
//        bottomNavigation.manageFloatingActionButtonBehavior();

        //设置颜色变换,选中的图标着色以及文本的颜色
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
//      未选中的图标着色及文本颜色
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));


        //给drawable染色
        bottomNavigation.setForceTint(true);

        //在导航中展示颜色
        bottomNavigation.setTranslucentNavigationEnabled(true);

        //设置标题显示状态
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);


        //显示圆形效果
        bottomNavigation.setColored(true);

        //
        bottomNavigation.setCurrentItem(1);

        //定做通知
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setNotification("1",3);



        //

        Fragment homeFragment = new HomeFragment();
        Fragment assessmentFragment = new ChatFragment();
        Fragment chatplceFragment = new ChatFragment();
        Fragment newsFragment = new Fragment();

        //添加界面元素到列表
        fragments.add(homeFragment);//智能营养
        fragments.add(assessmentFragment);//测评
        fragments.add(chatplceFragment);//交流广场
        fragments.add(newsFragment);//推荐


        //
        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(myFragmentAdapter);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.search:
                Toast.makeText(this,"you clicked search",Toast.LENGTH_SHORT).show();
                break;
            case R.id.love:
                Toast.makeText(this,"you clicked love",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this,"you clicked setting",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
