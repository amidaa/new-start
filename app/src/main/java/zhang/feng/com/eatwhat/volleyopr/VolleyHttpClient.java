package zhang.feng.com.eatwhat.volleyopr;

import android.content.Context;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.Map;

public interface VolleyHttpClient {
    String HOST = "http://47.112.28.145:8090/";
    /**
     * 注册
     */
    String AppSysUserController = HOST + "demo";
    String AppSysUserLoginController = HOST+"authentication";
    /**
     *
     * @param tag
     * @param context
     * @param map
     * @param listener
     * @param errorListener
     */
    void getAppSysUserController(String tag, Context context, Map<String, String> map, DefaultListener<String> listener,
                                 DefaultErrorListener errorListener);

    void addAppUserController(Context context, JSONObject jsonObject,DefaultJsonListener<JSONObject> listener);

    void UserLoginController(Context context, JSONObject jsonObject, Response.Listener<JSONObject> listener, DefaultErrorListener errorListener);
    void VideoShowController(String url,Context context, Response.Listener<JSONObject> listener, DefaultErrorListener errorListener);
    void UserInfoController(String url,Context context, Response.Listener<JSONObject> listener, DefaultErrorListener errorListener);
    void putInformationController(String url,Context context, JSONObject jsonObject, Response.Listener<JSONObject> listener, DefaultErrorListener errorListener);
    void ChangePswController(String url,Context context, JSONObject jsonObject, Response.Listener<JSONObject> listener, DefaultErrorListener errorListener);
    void ConditionShowController(String url,JSONObject jsonObject,Context context, Response.Listener<JSONObject> listener, DefaultErrorListener errorListener);
}
