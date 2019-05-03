package zhang.feng.com.eatwhat;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import zhang.feng.com.eatwhat.volleyopr.DefaultErrorListener;
import zhang.feng.com.eatwhat.volleyopr.VolleyHttpApi;

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText userNameText;//用户名输入框
    private EditText originPswText;//原密码
    private EditText newPswText;//新密码
    private EditText checkPswText;//确认密码
    private Button confirm;//确认按钮
    private Button cancle;//取消按钮
    private static String HOST = "http://47.112.28.145:8090/demo/changePassword";//修改密码网络连接地址
    private VolleyHttpApi mVolleyHttpApi;//网络连接


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        mVolleyHttpApi = VolleyHttpApi.getInstance();
        initStatusBar();
        init();


    }


    private void init(){

        userNameText = (EditText)findViewById(R.id.user);
        originPswText = (EditText)findViewById(R.id.origin_number);
        newPswText = (EditText)findViewById(R.id.new_password);
        checkPswText = (EditText)findViewById(R.id.check_password);

        confirm = (Button)findViewById(R.id.my_sure);
        cancle = (Button)findViewById(R.id.my_cancle);
        userNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userNameText.getText().toString().trim();
                String originPsw = originPswText.getText().toString().trim();
                String newPsw = newPswText.getText().toString().trim();
                String rePsw = checkPswText.getText().toString().trim();
                if(username.equals("")){
                    Toast.makeText(ChangePasswordActivity.this,"用户名不能为空！",Toast.LENGTH_SHORT);
                }else if("".equals(originPsw)){
                    Toast.makeText(ChangePasswordActivity.this,"原密码不能为空！",Toast.LENGTH_SHORT);
                }else if("".equals(newPsw)){
                    Toast.makeText(ChangePasswordActivity.this,"新密码不能为空！",Toast.LENGTH_SHORT);
                }else if("".equals(rePsw)){
                    Toast.makeText(ChangePasswordActivity.this,"确认密码不能为空！",Toast.LENGTH_SHORT);
                }else if(!rePsw.equals(newPsw)){
                    Toast.makeText(ChangePasswordActivity.this,"两次密码输入不一致，请重新输入！",Toast.LENGTH_SHORT);
                }else{
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("username",username);
                        jsonObject.put("originPassword",originPsw);
                        jsonObject.put("newPassword",newPsw);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mVolleyHttpApi.ChangePswController(HOST, ChangePasswordActivity.this, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            int result = response.optInt("result");
                            if (result==1){
                                Toast.makeText(ChangePasswordActivity.this,"密码修改成功，请返回登录界面重新登陆",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(ChangePasswordActivity.this,"密码修改失败，请重试",Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new DefaultErrorListener() {
                        @Override
                        protected void onErrorResponseFailed(String errorMesg, VolleyError volleyError) {
                            Toast.makeText(ChangePasswordActivity.this,"网络开小差啦，请稍后重试",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
