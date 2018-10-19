package zhang.feng.com.eatwhat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

            }
        });

    }
}
