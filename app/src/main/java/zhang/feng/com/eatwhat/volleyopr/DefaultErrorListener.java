package zhang.feng.com.eatwhat.volleyopr;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;

public abstract class DefaultErrorListener implements Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError volleyError) {
        String errorMesg = "";
        if (volleyError instanceof NetworkError) {
            errorMesg = "网络连接失败";
        } else if (volleyError instanceof ServerError) {
            errorMesg = "服务器的响应错误";
        } else if (volleyError instanceof AuthFailureError) {
            errorMesg = "请求身份验证错误";
        } else if (volleyError instanceof ParseError) {
            errorMesg = "服务器返回数据有误";
        } else if (volleyError instanceof NoConnectionError) {
            errorMesg = "网络连接失败";
        } else if (volleyError instanceof TimeoutError) {
            errorMesg = "服务器太忙或网络延迟";
        }
        onErrorResponseFailed(errorMesg, volleyError);
    }

    protected abstract void onErrorResponseFailed(String errorMesg, VolleyError volleyError);
}
