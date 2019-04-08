package zhang.feng.com.eatwhat.volleyopr;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class DefaultListener<T> implements Response.Listener<String> {
    @Override
    public void onResponse(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            String code = jsonObject.optString("retcode");
            String msg = "";
            if (code.equals("0")) {
                String data = "";
                if (jsonObject.has("data")) {
                    data = jsonObject.optString("data");
                    onSuccess(0, data);
                } else {
                    onSuccess(0, "");
                }
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
