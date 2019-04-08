package zhang.feng.com.eatwhat;

import androidx.appcompat.app.AppCompatActivity;
import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;
import scut.carson_ho.searchview.bCallBack;

import android.os.Bundle;

public class SearchActivity extends AppCompatActivity {
    //初始化搜索框变量
    private SearchView mSearchView;//搜索控件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //绑定搜索框组件

        mSearchView = (SearchView)findViewById(R.id.search_view);


        //设置点击键盘上的搜索按键后的操作
        mSearchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {

            }
        });


        //设置点击返回按键后的操作
        mSearchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                finish();
            }
        });
    }
}
