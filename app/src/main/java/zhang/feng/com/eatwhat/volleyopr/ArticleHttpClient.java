package zhang.feng.com.eatwhat.volleyopr;

import android.content.Context;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.Map;

public interface ArticleHttpClient {
    String HOST = "http://47.112.28.145:8090/articleApi/";
    /**
     * 文章的显示
     */
    String ArticleShowController = HOST;
    String ArticleAddController = HOST+"authentication";

    void ArticleFindController(Context context, Response.Listener<JSONObject> listener,DefaultErrorListener errorListener);

    void ArticleAddController(Context context, JSONObject jsonObject, Response.Listener<JSONObject> listener, DefaultErrorListener errorListener);

}
