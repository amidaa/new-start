package zhang.feng.com.eatwhat.volleyopr;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest {
    public static void LoginRequest(final String accountNumber,final String password){
        //请求地址
        String url = "47.112.28.145:8090/";
        String tag = "Login";


        //取得请求队列
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        //防止重复请求，所以取消tag标识的请求队列
//        requestQueue.cancelAll(tag);

        //创建StringRequest,定义字符串请求的请求方式为post
        final StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");
                    String result = jsonObject.getString("Result");
                    if (result.equals("success")) {
                        //页面跳转
                    } else {
                        //失败提示
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG",error.getMessage(),error);
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("AccountNumber",accountNumber);
                params.put("Password",password);
                return params;
            }
        };


        //设置tag标签
        request.setTag(tag);

        //将请求添加到队列中
//        requestQueue.add(request);
    }
}
