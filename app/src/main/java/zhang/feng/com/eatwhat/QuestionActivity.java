package zhang.feng.com.eatwhat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import zhang.feng.com.eatwhat.fragment.QuestionFragment;

import android.os.Bundle;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if(fragment == null){
            fragment = new QuestionFragment();
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }
    }
}
