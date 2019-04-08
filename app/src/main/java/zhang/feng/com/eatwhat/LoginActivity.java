package zhang.feng.com.eatwhat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import zhang.feng.com.eatwhat.volleyopr.VolleyHttpApi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.litepal.LitePal;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    //    登录界面
    private VolleyHttpApi mVolleyHttpApi;//网络请求
    private EditText username;//用户名
    private EditText password;//密码
    private Button log_button;//登录按钮
    private Button register;//注册按钮
    private Button forget_password;//忘记密码按钮
    private Button username_clear;//用户名清除
    private Button password_clear;//密码清除
    private String islegaluser;//判断用户输入是否正确
    private CheckBox remember;//记住密码功能
    private SharedPreferences mRememberPreferences=null;//存储用户名密码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mVolleyHttpApi = VolleyHttpApi.getInstance();//初始化网络请求
        initView();
        initStatusBar();
    }

    private void initView(){
        username = (EditText)findViewById(R.id.userName);
        password = (EditText)findViewById(R.id.passWord);
        log_button = (Button)findViewById(R.id.log);
        register = (Button)findViewById(R.id.newUser);
        forget_password = (Button)findViewById(R.id.forget);
        remember = (CheckBox)findViewById(R.id.remember);
        mRememberPreferences = getSharedPreferences("REMEMBERPAW",MODE_PRIVATE);//私有数据，只能被应用本身访问
        boolean isAutoLogin = mRememberPreferences.getBoolean("PSW",false);
        String name = mRememberPreferences.getString("username","");
        String pa = mRememberPreferences.getString("password","");
        if(isAutoLogin){
            username.setText(name);
            password.setText(pa);
            remember.setChecked(true);
        }
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
                String usn = username.getText().toString().trim();
                String psw = password.getText().toString().trim();
                List<Users> users = LitePal.select("username","password").find(Users.class);
                for(Users user:users){
                    if(usn.equals(user.getUsername())){
                        if(psw.equals(user.getPassword())){
                            //记住密码功能设置
                            if(remember.isChecked()){
//                                mRememberPreferences = getSharedPreferences("REMEMBERPAW",MODE_PRIVATE);//私有数据，只能被应用本身访问
                                SharedPreferences.Editor editor = mRememberPreferences.edit();
                                editor.putBoolean("PSW",true);//记录保存标记
                                editor.putString("username",usn);
                                editor.putString("password",psw);
                                editor.commit();//提交
                            }
                            Intent intent = new Intent(LoginActivity.this,MajorActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });



        //注册按钮设置监听
        register.setOnClickListener(new View.OnClickListener() {//为新用户注册设置监听
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });
        forget_password.setOnClickListener(new View.OnClickListener() {//为忘记密码按钮设置监听
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

    }
    //实现沉浸式状态栏

    private void initStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

}