package zhang.feng.com.eatwhat;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import zhang.feng.com.eatwhat.volleyopr.DefaultErrorListener;
import zhang.feng.com.eatwhat.volleyopr.DefaultListener;
import zhang.feng.com.eatwhat.volleyopr.VolleyHttpApi;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private VolleyHttpApi volleyHttp;//实现网络请求
    private final String tag="REGISTER";
    private EditText username;//姓名
    private EditText password;//密码
    private EditText nickname;//昵称
    private EditText re_password;//确认密码
    private Context mContext;//
    private Button affirm;//确定按钮
    private Button cancel;//取消按钮
//    private androidx.appcompat.widget.Toolbar back_toolbar;//带有返回按钮的toolBar
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        volleyHttp = VolleyHttpApi.getInstance();
        mContext = this;
        init();
        initStatusBar();

    }
    private void init(){
        username = (EditText) findViewById(R.id.register_username);
        password = (EditText)findViewById(R.id.register_pwd);
        nickname = (EditText)findViewById(R.id.register_nick);
        re_password = (EditText)findViewById(R.id.register_surepwd);
        affirm = (Button)findViewById(R.id.affirm);
        cancel = (Button)findViewById(R.id.cancel);
//        back_toolbar = (Toolbar) findViewById(R.id.back_home);
//        setSupportActionBar(back_toolbar);
//        back_toolbar.setNavigationIcon(R.mipmap.back);
//        back_toolbar.setTitle("注册");
//        back_toolbar.setTitleTextColor(Color.parseColor("#00E5EE"));//设置标题颜色
//        //为导航图标设置监听
//        back_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        //为确认按钮设置监听
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

                    HashMap<String, String> map = new HashMap<>();
                    map.put("username", name);
                    map.put("password", psw);
                    volleyHttp.getAppSysUserController(tag, mContext, map, new DefaultListener<String>() {
                        @Override
                        public void onSuccess(int code, String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                String dept = jsonObject.optString("dept");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onResponseFailed(int code, String errorMsg, Exception e) {
                            Toast.makeText(RegisterActivity.this,errorMsg,Toast.LENGTH_SHORT).show();
                        }
                    }, new DefaultErrorListener() {
                        @Override
                        protected void onErrorResponseFailed(String errorMesg, VolleyError volleyError) {
                            Toast.makeText(RegisterActivity.this,errorMesg,Toast.LENGTH_SHORT).show();
                        }
                    });

//                    Users users = new Users();
//                    users.setUsername(name);
//                    users.setPassword(psw);
//                    users.setAge(20);
//                    users.setSex("M");
//                    users.save();
                }

            }
        });
        //为取消按钮设置监听
        cancel.setOnClickListener(new View.OnClickListener() {
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

    @Override
    protected void onStop() {
        super.onStop();
        if (null != volleyHttp)
            volleyHttp.cancelQueue(tag);
    }

//    private void getJSONVolley(){
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        String JSONDataUrl = "http://pipes.yahooapis.com/pipes/pipe.run?_id=giWz8Vc33BG6rQEQo_NLYQ&_render=json";
//        final ProgressDialog progressDialog = ProgressDialog.show(this, "This is title", "...Loading...");
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                Request.Method.GET,
//                JSONDataUrl,
//                null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        System.out.println("response="+response);
//                        if (progressDialog.isShowing()&&progressDialog!=null) {
//                            progressDialog.dismiss();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError arg0) {
//                        System.out.println("sorry,Error");
//                    }
//                });
//        requestQueue.add(jsonObjectRequest);
//    }
}
