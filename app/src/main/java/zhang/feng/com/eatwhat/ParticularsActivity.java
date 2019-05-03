package zhang.feng.com.eatwhat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import zhang.feng.com.eatwhat.fragment.ParticularsFragment;

public class ParticularsActivity extends AppCompatActivity {

    private  static final String EXTRA_NEWS_CONTENT = "zhang.feng.com.eatwhat.news_content";


    public static Intent newIntent(Context packageContext, String newsContent){
        Intent intent = new Intent(packageContext,ParticularsActivity.class);
        intent.putExtra(EXTRA_NEWS_CONTENT,newsContent);//传入匹配的字符串键值
        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.particulars_layout);


        FragmentManager fm = getSupportFragmentManager();//获取FragmentManager
        Fragment fragment = fm.findFragmentById(R.id.frame_container);
        if(fragment==null){
            String newsContent = (String) getIntent().getSerializableExtra(EXTRA_NEWS_CONTENT);
            fragment = ParticularsFragment.newInstance(newsContent);
            fm.beginTransaction().add(R.id.frame_container,fragment).commit();//创建并提交fragment事务
        }

    }
}
