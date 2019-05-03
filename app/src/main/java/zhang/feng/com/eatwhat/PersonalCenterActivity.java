package zhang.feng.com.eatwhat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import zhang.feng.com.eatwhat.customview.ItemView;
import zhang.feng.com.eatwhat.goods.BodyInformation;
import zhang.feng.com.eatwhat.volleyopr.DefaultErrorListener;
import zhang.feng.com.eatwhat.volleyopr.VolleyHttpApi;

public class PersonalCenterActivity extends AppCompatActivity {
    private CircleImageView headImage;//头像
    private TextView usernameText;//名字
    private TextView velText;//
    private ItemView sexText;//性别
    private ItemView ageText;//年龄
    private ItemView heightText;//身高
    private ItemView weightText;//身高
    private ItemView symptomText;//症状
    private ItemView illnessTimeText;//患病时间
    private ItemView exerciseTimeText;//运动频率

    private SharedPreferences mSharedPreferences=null;//内存数据
    private String username = "Lisa";



    private static  String URL="http://47.112.28.145:8090/bodyinfoApi/findBodyInformation/";
    private VolleyHttpApi mVolleyHttpApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
        mVolleyHttpApi = VolleyHttpApi.getInstance();
        initView();
    }

    private void initView(){
        headImage = (CircleImageView)findViewById(R.id.h_head);
        usernameText = (TextView)findViewById(R.id.user_name);
        velText = (TextView)findViewById(R.id.user_val);
        sexText = (ItemView)findViewById(R.id.sex);
        ageText = (ItemView)findViewById(R.id.p_age);
        heightText = (ItemView)findViewById(R.id.p_height);
        weightText = (ItemView)findViewById(R.id.p_weight);
        symptomText = (ItemView)findViewById(R.id.p_symptom);
        illnessTimeText = (ItemView)findViewById(R.id.p_illness_time);
        exerciseTimeText = (ItemView)findViewById(R.id.p_exercise);

        initData(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                String information = result.optString("bodyinformation");
                Gson gson = new Gson();
                BodyInformation bodyinformation = gson.fromJson(information,BodyInformation.class);//将json字符串通过gson转化为对应的对象
                heightText.setRightText(bodyinformation.getHeight()+"");
                weightText.setRightText(bodyinformation.getWeight()+"");
                String illness = "";
                switch (bodyinformation.getIllness()){
                    case "Value A":
                        illness="无慢性病";
                        break;
                    case "Value B":
                        illness+="糖尿病 ";
                        break;
                    case "Value C":
                        illness+="高血压 ";
                        break;
                    case "Value D":
                        illness+="高血脂 ";
                        break;
                    case "Value E":
                        illness+="冠心病 ";
                        break;
                    case "Value F":
                        illness+="脑卒中 ";
                        break;
                    case "Value G":
                        illness+="肥胖 ";
                        break;
                    case "Value H":
                        illness+="痛风 ";
                        break;
                    case "Value I":
                        illness+="骨质疏松 ";
                        break;
                     default:
                         illness="其他慢性病";


                }
                symptomText.setRightText(illness);
                illnessTimeText.setRightText(bodyinformation.getIllnesstime()+"年");
                if(String.valueOf(bodyinformation.getExercise())=="null"){
                    exerciseTimeText.setRightText("未知");
                }else{
                    exerciseTimeText.setRightText(String.valueOf(bodyinformation.getExercise()));
                }

                usernameText.setText(username);
                if(bodyinformation.getSex()==0){
                    sexText.setRightText("女");
                }else{
                    sexText.setRightText("男");
                }
            }
        });



    }

    private  void initData(final VolleyCallback callback){
        mSharedPreferences = getSharedPreferences("USER",MODE_PRIVATE);//私有数据，只能被应用本身访问
        int id = mSharedPreferences.getInt("hostid",1);
        username = mSharedPreferences.getString("username","Lisa");
        Toast.makeText(PersonalCenterActivity.this,String.valueOf(id), Toast.LENGTH_SHORT).show();
        String url = URL+id;
        mVolleyHttpApi.UserInfoController(url, PersonalCenterActivity.this, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response);

            }
        }, new DefaultErrorListener() {
            @Override
            protected void onErrorResponseFailed(String errorMesg, VolleyError volleyError) {

            }
        });

    }

    public interface VolleyCallback {
        void onSuccess(JSONObject result);

    }

}
