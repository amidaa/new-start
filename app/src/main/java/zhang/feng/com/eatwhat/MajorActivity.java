package zhang.feng.com.eatwhat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class MajorActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Button bloodpressure;//血压
    private Button heartrate;//心率




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.major_layout);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
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
        bloodpressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MajorActivity.this,BloodPressureChartView.class);
                startActivity(intent);
            }
        });
        heartrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MajorActivity.this,EnergyChartView.class);
                startActivity(intent);
            }
        });
    }
    private String addNumber(String text) {
        int start = text.indexOf("(");
        int end = text.indexOf(")");
        int number = Integer.parseInt(text.substring(start + 1, end));
        number++;

        return text.substring(0, start) + "(" + number + ")";
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
}
