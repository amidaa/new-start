package zhang.feng.com.eatwhat;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

//    登录界面
    private EditText username;//用户名
    private EditText password;//密码
    private Button log_button;//登录按钮
    private Button register;//注册按钮
    private Button forget_password;//忘记密码按钮
    private Button username_clear;//用户名清除
    private Button password_clear;//密码清除
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new MyDatabaseHelper(this,"User.db",null,2);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        username = (EditText)findViewById(R.id.userName);
        password = (EditText)findViewById(R.id.passWord);
        log_button = (Button)findViewById(R.id.log);
        register = (Button)findViewById(R.id.newUser);
        forget_password = (Button)findViewById(R.id.forget);
//        username_clear = (Button)findViewById(R.id.user_right);
//        password_clear = (Button)findViewById(R.id.password_right);
        username.addTextChangedListener(new TextWatcher() {//为用户名输入框设置监听
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String user = username.getText().toString().trim();
                if("".equals(user)){
                    //用户名为空
                }else{
                    //用户名不为空
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {//为密码输入框设置监听
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String pwd = password.getText().toString().trim();
                if("".equals(pwd)){
                    //如果输入的密码为空
                }else{
                    //如果输入的密码不为空
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        log_button.setOnClickListener(new View.OnClickListener() {//为登录按钮设置监听
            @Override
            public void onClick(View view) {

                dbHelper.getWritableDatabase();

            }
        });
        register.setOnClickListener(new View.OnClickListener() {//为新用户注册设置监听
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });
        forget_password.setOnClickListener(new View.OnClickListener() {//为忘记密码按钮设置监听
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //数据库的插入操作
//                values.put("username","李丹");
//                values.put("password","zf234we");
//                values.put("age","18");
//                values.put("sex","F");
//                db.insert("Users",null,values);
//                values.put("username","lisa");
//                values.put("password","weare1234");
//                values.put("age","20");
//                values.put("sex","F");
//                db.insert("Users",null,values);
                //数据库的更新操作
//                values.put("age",19);
//                db.update("Users",values,"username=?", new String[]{"李丹"});
                //数据库的删除操作
//                db.delete("Users","age<?",new String[]{"20"});
                //数据库的查询操作
                Cursor cursor = db.query("Users",null,null,null,null,
                null,null);
                if(cursor.moveToFirst()){
                    do{
                        String name = cursor.getString(cursor.getColumnIndex("username"));
                        String psw = cursor.getString(cursor.getColumnIndex("password"));
                        Log.d("MainActivity","用户名为："+name);
                        Log.d("MainActivity","密码为："+psw);
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

    }
}
