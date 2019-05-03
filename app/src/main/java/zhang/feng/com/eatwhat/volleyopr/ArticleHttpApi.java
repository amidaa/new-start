package zhang.feng.com.eatwhat.volleyopr;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ArticleHttpApi implements ArticleHttpClient {
    private static ArticleHttpApi mInstance;
    private RequestQueue mRequestQueue;
    int DEFAULT_TIMEOUT_MS = 10000;
    int DEFAULT_MAX_RETRIES = 3;

    public static ArticleHttpApi getInstance() {
        if (mInstance == null) {
            synchronized (ArticleHttpApi.class) {
                if (mInstance == null)
                    mInstance = new ArticleHttpApi();
            }
        }
        return mInstance;
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
    @Override
    public void ArticleFindController(Context context, Response.Listener<JSONObject> listener, DefaultErrorListener errorListener) {

        try {
            getVolleyGetRequest(ArticleShowController,context,listener,errorListener);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void ArticleAddController(Context context, JSONObject jsonObject, Response.Listener<JSONObject> listener, DefaultErrorListener errorListener) {

    }
}
