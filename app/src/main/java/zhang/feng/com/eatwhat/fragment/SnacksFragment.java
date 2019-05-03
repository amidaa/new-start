package zhang.feng.com.eatwhat.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zhang.feng.com.eatwhat.R;
import zhang.feng.com.eatwhat.acache.ACache;
import zhang.feng.com.eatwhat.goods.Breakfast;
import zhang.feng.com.eatwhat.volleyopr.DefaultErrorListener;
import zhang.feng.com.eatwhat.volleyopr.FoodHttpApi;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SnacksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SnacksFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Breakfast> mSnackFoods;
    private RecyclerView mSnackRecyclerView;
    private static String HOST="http://47.112.28.145:8090/MealApi/findType/snack&type=";
    private FoodHttpApi mFoodHttpApi;


    public SnacksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SnacksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SnacksFragment newInstance(String param1, String param2) {
        SnacksFragment fragment = new SnacksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFoodHttpApi = FoodHttpApi.getInstance();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_snacks, container, false);
        mSnackRecyclerView = (RecyclerView) view.findViewById(R.id.snacks_food_form);

        mSnackRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ACache mCache = ACache.get(getActivity());
        String foods = mCache.getAsString("snacks");
        if(foods!=null&&foods!="") {
            Type listType = new TypeToken<ArrayList<Breakfast>>() {
            }.getType();
            Gson gson = new Gson();
            mSnackFoods = gson.fromJson(foods, listType);//将json字符串通过gson转化为对应的对象
            SnackAdapter adapter = new SnackAdapter(R.layout.item_foodlist, mSnackFoods);
            View headView = getLayoutInflater().inflate(R.layout.food_header,null);
            headView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            adapter.addHeaderView(headView);
            //添加动画
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(final BaseQuickAdapter adapter, View view, final int position) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    View dialogview= LayoutInflater.from(getActivity()).inflate(R.layout.alterdialog,null);
                    builder.setView(dialogview)
                            .setCancelable(true)
                            .setNegativeButton("删除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mSnackFoods.remove(position);
                                    adapter.notifyDataSetChanged();
                                    adapter.notifyItemRangeChanged(position, mSnackFoods.size());
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                    WindowManager.LayoutParams params =
                            dialog.getWindow().getAttributes();
                    params.width = 200;
                    params.height = 150 ;
                    params.x = 80;//设置x坐标
                    params.y = 60;//设置y坐标
                    dialog.getWindow().setAttributes(params);

                }


            });



            mSnackRecyclerView.setAdapter(adapter);
        }else{
            preparDate(new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    JSONArray jsonArray = result.optJSONArray("breakfasts");//获取后端服务器的jsonArray对象

                    String str = jsonArray.toString();//将json对象转换为json字符串
                    ACache mAcache = ACache.get(getActivity());//将数据缓存到内存
                    mAcache.put("snacks",str,60*60);//保存时间为1小时
                    Type listType = new TypeToken<ArrayList<Breakfast>>() {
                    }.getType();
                    Gson gson = new Gson();
                    mSnackFoods = gson.fromJson(str, listType);//将json字符串通过gson转化为对应的对象
                    SnackAdapter adapter = new SnackAdapter(R.layout.item_foodlist, mSnackFoods);
                    View headView = getLayoutInflater().inflate(R.layout.food_header,null);
                    headView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                    adapter.addHeaderView(headView);


                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(final BaseQuickAdapter adapter, View view, final int position) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            View dialogview= LayoutInflater.from(getActivity()).inflate(R.layout.alterdialog,null);
                            builder.setView(dialogview)
                                    .setCancelable(true)
                                    .setNegativeButton("删除", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            mSnackFoods.remove(position);
                                            adapter.notifyDataSetChanged();
                                            adapter.notifyItemRangeChanged(position, mSnackFoods.size());
                                        }
                                    });

                            AlertDialog dialog = builder.create();
                            dialog.show();
                            WindowManager.LayoutParams params =
                                    dialog.getWindow().getAttributes();
                            params.width = 200;
                            params.height = 150 ;
                            params.x = 80;//设置x坐标
                            params.y = 60;//设置y坐标
                            dialog.getWindow().setAttributes(params);

                        }


                    });

                    mSnackRecyclerView.setAdapter(adapter);


                }
            });

        }

      return view;
    }

    public class SnackAdapter extends BaseQuickAdapter<Breakfast, BaseViewHolder> {


        public SnackAdapter(@LayoutRes int layoutResId, @Nullable List<Breakfast> foods){
            super(layoutResId,foods);
        }
        @Override
        protected void convert(BaseViewHolder helper, Breakfast item) {

            helper.setText(R.id.food_name,item.getFoodname()).setText(R.id.per_content,item.getWeight()+"g").setText(R.id.energy, item.getEnergy()+"kcal");
        }


//        @Override
//        public void remove(int position) {
//            foods.remove(position);
//            notifyItemRemoved(position);
//                //刷新下标，不然下标就重复
//
//
//
//        }
    }

    private void preparDate(final VolleyCallback volleyCallback){
        mSnackFoods = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        int type = random.nextInt(2);
        String url = HOST+type;
        mFoodHttpApi.FoodFindController(url, getActivity(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

               volleyCallback.onSuccess(response);

            }
        }, new DefaultErrorListener() {
            @Override
            protected void onErrorResponseFailed(String errorMesg, VolleyError volleyError) {
                Toast.makeText(getActivity(),errorMesg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface VolleyCallback {
        void onSuccess(JSONObject result);

    }

}
