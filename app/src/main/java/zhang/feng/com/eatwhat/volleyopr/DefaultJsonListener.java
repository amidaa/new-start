package zhang.feng.com.eatwhat.volleyopr;

import android.util.Log;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class DefaultJsonListener<T> implements Response.Listener<JSONObject>  {
    @Override
    public void onResponse(JSONObject object) {
        Log.e("error","#####&&&&&&&哈哈哈哈哈哈哈哈哈哈哈"+object);

        try {
            if (object.getInt("message")==1) {
                String data = "1";
                onSuccess(1,data);
            }else {
                String data = "0";
                onSuccess(0,data);
            }
        } catch (JSONException e) {

            e.printStackTrace();
            onResponseFailed(2, "数据解析失败！", e);
        }
    }

    public abstract void onSuccess(int code, String data);

    public abstract void onResponseFailed(int code, String errorMsg, Exception e);

}
