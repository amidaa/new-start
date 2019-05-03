package zhang.feng.com.eatwhat;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zhang.feng.com.eatwhat.goods.Food;
import zhang.feng.com.eatwhat.volleyopr.DefaultErrorListener;
import zhang.feng.com.eatwhat.volleyopr.VolleyHttpApi;

public class RecommendActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyFoodAdapter adapter = null;
    LinearLayoutManager linearLayoutManager;
    int moreNum = 2;
    private ActionMode actionMode;

    private String IMAGEPATH="http://47.112.28.145:8080/images/foodimage/";


    Toolbar toolbar;
    boolean isDrag = true;

    private ItemTouchHelper mItemTouchHelper;


    private VolleyHttpApi mVolleyHttpApi;
    private String HOST="http://47.112.28.145:8090/foodApi/findAll";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        toolbar = (Toolbar) findViewById(R.id.recommend_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mVolleyHttpApi = VolleyHttpApi.getInstance();


        recyclerView = (RecyclerView) findViewById(R.id.ultimate_recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        final ArrayList<Food> foodList = new ArrayList<>();
        updateUI(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                JSONArray jsonArray = result.optJSONArray("foods");//获取后端服务器的jsonArray对象
                String str = jsonArray.toString();//将json对象转换为json字符串
                java.lang.reflect.Type listType = new TypeToken<ArrayList<Food>>(){}.getType();
                Gson gson = new Gson();
                ArrayList<Food> foods = gson.fromJson(str, listType);//将json字符串通过gson转化为对应的对象
                if(adapter==null){
                    adapter = new MyFoodAdapter(R.layout.recycler_view_adapter,foods);
                    View headView = getLayoutInflater().inflate(R.layout.parallax_recyclerview_header,null);
                    headView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                    adapter.addHeaderView(headView);
                    //添加动画
                    adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                    adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {

                        }
                    });
                    recyclerView.setAdapter(adapter);
                }else {
                    adapter.notifyDataSetChanged();//如果数据集发生变化，就调用该方法
                }
            }
        });





   }


    public class MyFoodAdapter extends BaseQuickAdapter<Food, BaseViewHolder> {

        public MyFoodAdapter(@LayoutRes int layoutResId, @Nullable List<Food> foods){
            super(layoutResId,foods);
        }
        @Override
        protected void convert(BaseViewHolder helper, Food item) {
            helper.setText(R.id.h_food_name,"名称："+item.getFoodname()).
                    setText(R.id.food_weight,"重量："+item.getWeight()+"g").
                    setText(R.id.food_energy, "能量："+item.getEnergy()+"kcal").
                    setText(R.id.food_use,"适宜人群："+item.getUse()).
                    setText(R.id.food_appropriate,"适用："+item.getAppropriate()).
                    setText(R.id.food_fat,"脂肪："+String.valueOf(item.getFat())+"克").
                    setText(R.id.food_carbohydrate,"碳水化合物："+String.valueOf(item.getCarbohydrate())+"克").
                    setText(R.id.food_protein,"蛋白质："+String.valueOf(item.getProtein())+"克").
                    setText(R.id.food_fibrin,"纤维素："+String.valueOf(item.getFibrin())+"克");
            ImageView imageView = helper.getView(R.id.imageview);
            String imagePath = IMAGEPATH + item.getImg_path();
            Uri uri = Uri.parse(imagePath);
            Glide.with(RecommendActivity.this).load(uri).into(imageView);
        }

        //点击事件

        @Override
        public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
            super.setOnItemClickListener(listener);
        }
        //长按事件
        @Override
        public void setOnItemLongClickListener(OnItemLongClickListener listener) {
            super.setOnItemLongClickListener(listener);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }


    private void updateUI(final VolleyCallback callback){
        mVolleyHttpApi.UserInfoController(HOST,RecommendActivity.this, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response);
            }
        }, new DefaultErrorListener() {
            @Override
            protected void onErrorResponseFailed(String errorMesg, VolleyError volleyError) {
                Toast.makeText(RecommendActivity.this,errorMesg, Toast.LENGTH_SHORT).show();
            }
        });

    }
    public interface VolleyCallback {
        void onSuccess(JSONObject result);

    }

}
