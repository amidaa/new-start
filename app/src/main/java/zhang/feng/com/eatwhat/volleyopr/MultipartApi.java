//package zhang.feng.com.eatwhat.volleyopr;
//
//import android.content.Context;
//import android.widget.Toast;
//
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class MultipartApi {
//    private static MultipartApi mInstance;
//    private RequestQueue mRequestQueue;
//    int DEFAULT_TIMEOUT_MS = 10000;
//    int DEFAULT_MAX_RETRIES = 3;
//
//    public static MultipartApi getInstance() {
//        if (mInstance == null) {
//            synchronized (MultipartApi.class) {
//                if (mInstance == null)
//                    mInstance = new MultipartApi();
//            }
//        }
//        return mInstance;
//    }
//
//    private void multipartVolleyRequest(String url, Context context, Integer[] num, Response.Listener<JSONObject> listener, DefaultErrorListener errorListener) throws JSONException {
//        VolleySingleton volleySingleton = VolleySingleton.getVolleySingleton(context);
//        //获得队列
//        mRequestQueue = volleySingleton.getRequestQueue();
//        MultipartRequest multipartRequest = new MultipartRequest(
//                url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String string) {
//                //string为服务器返回的字符串
//                Toast.makeText(context, "更新成功", Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context,"失败",Toast.LENGTH_SHORT).show();
//            }
//        });
//// 添加header
//        multipartRequest.addHeader("header-name", "value");
//// 通过MultipartEntity来设置参数
//        MultipartEntity multi = multipartRequest.getMultiPartEntity();
////传参数
//        multi.addStringPart("userId", userId());
////传二进制byte[]
//        multi.addBinaryPart("logo", bytes);
////传文件(以图片为例)
//        multi.addFilePart("logo", BitmapToUtils.saveFile(head),"image/png");
//        queue.add(multipartRequest);
//        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
//                DEFAULT_TIMEOUT_MS, DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        mRequestQueue.add(jsonObjectRequest);
//    }
//
//
//}
