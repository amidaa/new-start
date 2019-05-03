package zhang.feng.com.eatwhat.volleyopr;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class DefaultListener<T> implements Response.Listener<String> {
    @Override
    public void onResponse(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            String data = jsonObject.optString("message");
            String msg = "";
            if (data.equals("1")) {
                    onSuccess(0, data);
            } else {
                if (jsonObject.has("mesg")) {
                    msg = jsonObject.optString("mesg");
                } else {
                    msg = "对不起，服务器返回数据有错误！";
                }
                onResponseFailed(1, msg, new RuntimeException(msg));
            }
        } catch (JSONException e) {

            e.printStackTrace();
            onResponseFailed(2, "数据解析失败！", e);
        }
    }

    public abstract void onSuccess(int code, String data);

    public abstract void onResponseFailed(int code, String errorMsg, Exception e);

}
