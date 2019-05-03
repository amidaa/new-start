package zhang.feng.com.eatwhat.volleyopr;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class VolleyHttpApi implements VolleyHttpClient {

    private StringRequest stringRequest;
    private static VolleyHttpApi mInstance;
    private RequestQueue mRequestQueue;
    int DEFAULT_TIMEOUT_MS = 10000;
    int DEFAULT_MAX_RETRIES = 3;

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

//            @Override
//            public String getBodyContentType() {
//                return "application/json";
//            }

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String,String>();
//                params.put("Content-Type", "application/json; charset=utf-8");
//                return params;
//            }
        };

        VolleySingleton volleySingleton = VolleySingleton.getVolleySingleton(context);
        //获得队列
        mRequestQueue = volleySingleton.getRequestQueue();
        //添加tag
        stringRequest.setTag(tag);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                DEFAULT_TIMEOUT_MS, DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
////        RequestQueue requestQueue = VolleyManager.getInstance(context).getRequestQueue();
//        requestQueue.add(stringRequest);
        //将StringRequest对象添加进RequestQueue请求队列中
        mRequestQueue.add(stringRequest);

    }
    private void getVolleyGetRequest( String url,Context context,Response.Listener<JSONObject> listener, DefaultErrorListener errorListener) throws JSONException {
        VolleySingleton volleySingleton = VolleySingleton.getVolleySingleton(context);
        //获得队列
        mRequestQueue = volleySingleton.getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                listener,errorListener);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DEFAULT_TIMEOUT_MS, DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(jsonObjectRequest);
    }
    private void addVolleyPostRequest( String url,Context context,JSONObject jsonObject,Response.Listener<JSONObject> listener, DefaultErrorListener errorListener) throws JSONException {
        VolleySingleton volleySingleton = VolleySingleton.getVolleySingleton(context);
        //获得队列
        mRequestQueue = volleySingleton.getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                listener,errorListener);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DEFAULT_TIMEOUT_MS, DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(jsonObjectRequest);
    }

    private void getVolleyPostRequest( String url,Context context,JSONObject json,DefaultJsonListener<JSONObject> listener) throws JSONException {
        VolleySingleton volleySingleton = VolleySingleton.getVolleySingleton(context);
        //获得队列
        mRequestQueue = volleySingleton.getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json,
                listener,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
                        Log.i("--注册失败--", "onResponse: " + e);
                    }
                });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DEFAULT_TIMEOUT_MS, DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(jsonObjectRequest);
    }

    private void getVolleyRequest(String url,Context context,JSONObject json,Response.Listener<JSONObject> listener,DefaultErrorListener errorListener) throws JSONException{
        VolleySingleton volleySingleton = VolleySingleton.getVolleySingleton(context);
        //获得队列
        mRequestQueue = volleySingleton.getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json,
                listener,errorListener);

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DEFAULT_TIMEOUT_MS, DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(jsonObjectRequest);
    }

    private void getVolleyArrayRequest(String url,Context context,JSONObject json,Response.Listener<JSONObject> listener,DefaultErrorListener errorListener) throws JSONException{
        VolleySingleton volleySingleton = VolleySingleton.getVolleySingleton(context);
        //获得队列
        mRequestQueue = volleySingleton.getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json,
                listener,errorListener);

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DEFAULT_TIMEOUT_MS, DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(jsonObjectRequest);
    }
    private void volleyPutRequest( String url,Context context,JSONObject jsonObject,Response.Listener<JSONObject> listener, DefaultErrorListener errorListener) throws JSONException {
        VolleySingleton volleySingleton = VolleySingleton.getVolleySingleton(context);
        //获得队列
        mRequestQueue = volleySingleton.getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject,
                listener,errorListener);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DEFAULT_TIMEOUT_MS, DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(jsonObjectRequest);
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

    @Override
    public void addAppUserController(Context context, JSONObject jsonObject,DefaultJsonListener<JSONObject> listener) {
        try {
            getVolleyPostRequest(AppSysUserController,context,jsonObject,listener);
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    @Override
    public void UserLoginController(Context context, JSONObject jsonObject, Response.Listener<JSONObject> listener,DefaultErrorListener errorListener) {
        try {
            getVolleyRequest(AppSysUserLoginController,context,jsonObject,listener,errorListener);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void VideoShowController(String url, Context context, Response.Listener<JSONObject> listener, DefaultErrorListener errorListener) {
        try {
            getVolleyGetRequest(url,context,listener,errorListener);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void UserInfoController(String url,Context context, Response.Listener<JSONObject> listener, DefaultErrorListener errorListener) {
        try {
            getVolleyGetRequest(url,context,listener,errorListener);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void putInformationController(String url, Context context, JSONObject jsonObject, Response.Listener<JSONObject> listener, DefaultErrorListener errorListener) {
        try {
            addVolleyPostRequest(url,context,jsonObject,listener,errorListener);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void ChangePswController(String url, Context context, JSONObject jsonObject, Response.Listener<JSONObject> listener, DefaultErrorListener errorListener) {
        try {
            volleyPutRequest(url,context,jsonObject,listener,errorListener);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
//    显示朋友动态

    @Override
    public void ConditionShowController(String url,JSONObject jsonObject, Context context, Response.Listener<JSONObject> listener, DefaultErrorListener errorListener) {
        try {
            getVolleyRequest(url,context,jsonObject,listener,errorListener);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
