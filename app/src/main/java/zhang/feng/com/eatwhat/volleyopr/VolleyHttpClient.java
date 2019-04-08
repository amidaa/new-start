package zhang.feng.com.eatwhat.volleyopr;

import android.content.Context;

import java.util.Map;

public interface VolleyHttpClient {
    String HOST = "http://47.122.28.145:8090/";
    /**
     * 注册
     */
    String AppSysUserController = HOST + "demo/";

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

}
