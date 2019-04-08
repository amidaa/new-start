package zhang.feng.com.eatwhat.volleyopr;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

public class VolleyHttpApi implements VolleyHttpClient {

    private StringRequest stringRequest;
    private static VolleyHttpApi mInstance;
    private RequestQueue mRequestQueue;

    public static VolleyHttpApi getInstance() {
        if (mInstance == null) {
            synchronized (VolleyHttpApi.class) {
                if (mInstance == null)
                    mInstance = new VolleyHttpApi();
            }
        }
        return mInstance;
    }

    /**
     * POST 请求网络
     *
     * @param tag
     * @param context
     * @param url
     * @param map
     * @param listener
     * @param errorListener
     */
    private void getStringRequest(String tag, Context context, String url, final Map<String, String> map, DefaultListener<String> listener, DefaultErrorListener errorListener) {
        stringRequest = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };

        VolleySingleton volleySingleton = VolleySingleton.getVolleySingleton(context);
        //获得队列
        mRequestQueue = volleySingleton.getRequestQueue();
        //添加tag
        stringRequest.setTag(tag);
        //将StringRequest对象添加进RequestQueue请求队列中
        mRequestQueue.add(stringRequest);

    }

    /**
     * Activity的onStop()方法
     * 取消网络请求
     *
     * @param tag
     */
    public void cancelQueue(String tag) {
        if (null != mRequestQueue) {
            mRequestQueue.cancelAll(tag);
        }
    }

    /**
     *
     * @param tag
     * @param context
     * @param map
     * @param listener
     * @param errorListener
     */
    @Override
    public void getAppSysUserController(String tag, Context context, Map<String, String> map, DefaultListener<String> listener, DefaultErrorListener errorListener) {
        getStringRequest(tag, context, AppSysUserController, map, listener, errorListener);
    }
}
