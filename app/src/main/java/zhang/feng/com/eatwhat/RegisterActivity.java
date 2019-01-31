package zhang.feng.com.eatwhat;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText username;//姓名
    private EditText password;//密码
    private EditText nickname;//昵称
    private EditText re_password;//确认密码
    private Button affirm;//确定按钮
    private Button cancel;//取消按钮
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        init();

    }
    private void init(){
        username = (EditText) findViewById(R.id.register_username);
        password = (EditText)findViewById(R.id.register_pwd);
        nickname = (EditText)findViewById(R.id.register_nick);
        re_password = (EditText)findViewById(R.id.register_surepwd);
        affirm = (Button)findViewById(R.id.affirm);
        cancel = (Button)findViewById(R.id.cancel);
        //为确认按钮设置监听
        affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String psw = password.getText().toString().trim();
                String nick_name = nickname.getText().toString().trim();
                String re_psw = re_password.getText().toString().trim();
                if(name.equals("")){
                  Toast.makeText(RegisterActivity.this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
                }else if(psw.equals("")) {
                    Toast.makeText(RegisterActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }else if(!psw.equals(re_psw)){
                    Toast.makeText(RegisterActivity.this,"两次密码输入不一致！",Toast.LENGTH_SHORT).show();
                }else{
                    Users users = new Users();
                    users.setUsername(name);
                    users.setPassword(psw);
                    users.setAge(20);
                    users.setSex("M");
                    users.save();
                    Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                }

            }
        });
        //为取消按钮设置监听
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }
}
