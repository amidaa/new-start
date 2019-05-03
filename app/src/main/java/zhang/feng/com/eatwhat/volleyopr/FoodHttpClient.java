package zhang.feng.com.eatwhat.volleyopr;

import android.content.Context;

import com.android.volley.Response;

import org.json.JSONObject;

public interface FoodHttpClient {
//   食物列表获取

    void FoodFindController(String url,Context context, Response.Listener<JSONObject> listener, DefaultErrorListener errorListener);

    void FoodAddController(String url,Context context, JSONObject jsonObject, Response.Listener<JSONObject> listener, DefaultErrorListener errorListener);

}
