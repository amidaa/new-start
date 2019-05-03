package zhang.feng.com.eatwhat.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhuang.likeviewlibrary.LikeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import zhang.feng.com.eatwhat.PublishActivity;
import zhang.feng.com.eatwhat.R;
import zhang.feng.com.eatwhat.acache.ACache;
import zhang.feng.com.eatwhat.goods.Issue;
import zhang.feng.com.eatwhat.volleyopr.DefaultErrorListener;
import zhang.feng.com.eatwhat.volleyopr.VolleyHttpApi;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mChatRecyclerView;
    private ChatAdapter mAdapter;
    private SharedPreferences mRememberPreferences = null;//存储用户id


    private List<Map<String,Object>> imagepaths;//图片地址的所有信息

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private VolleyHttpApi mConditionHttpApi;//网络请求
    private static String HOST = "http://47.112.28.145:8090/ConditionApi/findFriendConditions";
    private static String FINDHOST = "http://47.112.28.145:8090/friendApi/findFriendList/";
    private String IMAGE = "http://47.112.28.145:8080/images/conditionimage/";

    private ArrayList<Issue> conditions;//动态信息
    private Integer hostId;//账号


    private class ChatHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mAuthorTextView;
        private TextView mDateTextView;
        private TextView mContentTextView;
        private Issue mIssue;
        private GridView mGridPhoto;
        private LikeView mLikeView;
        private GridViewImageAdapter mGridViewImageAdapter;
        private ImageButton attentionImage;
        private CircleImageView headImage;



        public ChatHolder(@NonNull View itemView) {
            super(itemView);
        }

        //构造方法实例化list_item_issue布局，传给父类
        public ChatHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_issue, parent, false));

            headImage = (CircleImageView) itemView.findViewById(R.id.head_portrait);

            mAuthorTextView = (TextView) itemView.findViewById(R.id.issue_author);
            mDateTextView = (TextView) itemView.findViewById(R.id.issue_date);
            mContentTextView = (TextView) itemView.findViewById(R.id.article);
            mGridPhoto = (GridView) itemView.findViewById(R.id.issuephoto);
            mLikeView = (LikeView) itemView.findViewById(R.id.like_view);
            attentionImage = (ImageButton)itemView.findViewById(R.id.attention);
            itemView.setOnClickListener(this);
            mLikeView.setOnLikeListeners(new LikeView.OnLikeListeners() {
                @Override
                public void like(boolean isCancel) {
                    //
                    //如果设置了like_canCancel为false，则isCancel可以不管，此时表示likeview被点击了
                    //如果设置了like_canCancel为true,表示可以取消点赞，那么isCancel为true时，表示取消点赞，为false时，表示点赞

                }
            });

        }

        public void bind(Issue issue) {
            mIssue = issue;
//            mAuthorTextView.setText(mIssue.getPersonId());
            if (mIssue.getDate() != null) {
                mDateTextView.setText(mIssue.getDate().substring(0, 10));
            } else {
                mDateTextView.setText("2019-4-5");
            }
            if(mIssue.getImg_paths()!=null&&mIssue.getImg_paths()!="") {

                imagepaths = new ArrayList<>();
                List<String> paths = Arrays.asList(mIssue.getImg_paths().split("#"));
                if(paths!=null&&paths.size()!=0){
                    for(int i=0;i<paths.size();i++){
                        Map<String,Object> map = new HashMap<>();
                        map.put("path",paths.get(i));
                        imagepaths.add(map);
                    }
                }


                mGridViewImageAdapter = new GridViewImageAdapter(getActivity(),imagepaths);
                mGridPhoto.setAdapter(mGridViewImageAdapter);
            }
            if(mIssue.getMood()!=null){
                switch (mIssue.getMood()){
                    case "HAPPY":
                        attentionImage.setImageResource(R.drawable.happy_active);
                        break;
                    case "SAD":
                        attentionImage.setImageResource(R.drawable.fail_activity);
                        break;
                    case "ADMIRING":
                        attentionImage.setImageResource(R.drawable.nochaer_active);
                        break;
                    case "TERRIFIED":
                        attentionImage.setImageResource(R.drawable.dai_active);
                        break;
                    case "AMAZED":
                        attentionImage.setImageResource(R.drawable.nochaer_active);
                        break;
                    case "ANGRY":
                        attentionImage.setImageResource(R.drawable.sad_active);
                        break;
                    case "SLEEPY":
                        attentionImage.setImageResource(R.drawable.dai_active);
                        break;
                    case "DEPRESSED":
                        attentionImage.setImageResource(R.drawable.dai_active);
                        break;
                    case "OVERWHELMED":
                        attentionImage.setImageResource(R.drawable.funny_active);
                        break;
                    case "LONELY":
                        attentionImage.setImageResource(R.drawable.face_activite);
                        break;
                    default:
                        break;

                }
            }


            mContentTextView.setText(mIssue.getContent());



        }

        @Override
        public void onClick(View v) {

        }
    }

    private class ChatAdapter extends RecyclerView.Adapter<ChatHolder> {

        private List<Issue> mIssues;

        public ChatAdapter(List<Issue> issues) {
            mIssues = issues;
        }

        public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ChatHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
            Issue issue = mIssues.get(position);
            holder.bind(issue);

        }

        @Override
        public int getItemCount() {

            return mIssues.size();
        }
    }

    public ChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        conditions = new ArrayList<>();
        setHasOptionsMenu(true);//显示menu
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mConditionHttpApi = VolleyHttpApi.getInstance();
        mRememberPreferences = getActivity().getSharedPreferences("USER", MODE_PRIVATE);//私有数据，只能被应用本身访问

        hostId = mRememberPreferences.getInt("hostid", 1);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) view.findViewById(R.id.chat_toolbar);
        toolbar.inflateMenu(R.menu.chat_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add:
                        Intent intent = new Intent(getActivity(), PublishActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);会出现菜单混乱问题
        mChatRecyclerView = (RecyclerView) view.findViewById(R.id.chat_recycler_view);
        mChatRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        String conditionstr = "";
        ACache mACache = ACache.get(getActivity());
        conditionstr = mACache.getAsString("conditions");

        if (conditionstr == null || conditionstr == "") {
            updateUI(new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    JSONArray jsonArray = result.optJSONArray("friends");//获取后端服务器的jsonArray对象
                    String str = "";
                    str = jsonArray.toString();//将json对象转换为json字符串
                    ACache mCache = ACache.get(getActivity());
                    mCache.put("conditions", str, 60 * 10);
                    Type listType = new TypeToken<ArrayList<Issue>>() {
                    }.getType();
                    Gson gson = new Gson();
                    conditions = gson.fromJson(str, listType);//将json字符串通过gson转化为对应的对象
                    if (mAdapter == null) {
                        mAdapter = new ChatAdapter(conditions);
                        mChatRecyclerView.setAdapter(mAdapter);
                    }else{
                        mAdapter.notifyDataSetChanged();
                    }
                }
            });
        } else {
            Type listType = new TypeToken<ArrayList<Issue>>() {
            }.getType();
            Gson gson = new Gson();
            conditions = gson.fromJson(conditionstr, listType);//将json字符串通过gson转化为对应的列表
            if (mAdapter == null) {
                mAdapter = new ChatAdapter(conditions);
                mChatRecyclerView.setAdapter(mAdapter);
            }
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();


        String conditionstr = "";
        ACache aCache = ACache.get(getActivity());
        conditionstr = aCache.getAsString("conditions");

        if (conditions==null) {
            Type listType = new TypeToken<ArrayList<Issue>>() {
            }.getType();
            Gson gson = new Gson();
            conditions = gson.fromJson(conditionstr, listType);//将json字符串通过gson转化为对应的列表
        }

        ACache mACache = ACache.get(getActivity());
        String issuestr = mACache.getAsString(hostId+"condtion");
        Gson gson = new Gson();
        Issue issue =  gson.fromJson(issuestr,Issue.class);
        if(issue!=null&&mAdapter!=null) {
            if(issue.getSerial_number()!=conditions.get(conditions.size()-1).getSerial_number()){
                conditions.add(0,issue);//将新添加的数放到第一个位置
                mAdapter.notifyDataSetChanged();
            }


        }

    }
    //获取菜单内容

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();//清除activity中的被调用的onCreateOptionsMenu方法
        menuInflater.inflate(R.menu.chat_toolbar, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }


    private void updateUI(final VolleyCallback callback) {
        ACache mCache = ACache.get(getActivity());
        JSONObject friendsCache = mCache.getAsJSONObject("friendsnum");
        String friends = "";
        if (friendsCache != null) {
            friends = friendsCache.optString("result");
        }

        if (friends != "") {
            Type listType = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            Gson gson = new Gson();
            ArrayList<Integer> list = gson.fromJson(friends, listType);//将json字符串通过gson转化为对应的对象
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("friendsNum", new JSONArray(list));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mConditionHttpApi.ConditionShowController(HOST, jsonObject, getActivity(), new Response.Listener<JSONObject>() {


                @Override
                public void onResponse(JSONObject response) {
                    callback.onSuccess(response);
                }
            }, new DefaultErrorListener() {
                @Override
                protected void onErrorResponseFailed(String errorMesg, VolleyError volleyError) {
                    Toast.makeText(getActivity(), errorMesg, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            getFriends(new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    String friends = result.optString("result");
                    if (friends != "") {
                        Type listType = new TypeToken<ArrayList<Integer>>() {
                        }.getType();
                        Gson gson = new Gson();
                        ArrayList<Integer> list = gson.fromJson(friends, listType);//将json字符串通过gson转化为对应的对象
                        ACache mCache = ACache.get(getActivity());
                        mCache.put("friendsnum", result, 1 * ACache.TIME_DAY);
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("friendsNum", new JSONArray(list));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mConditionHttpApi.ConditionShowController(HOST, jsonObject, getActivity(), new Response.Listener<JSONObject>() {


                            @Override
                            public void onResponse(JSONObject response) {
                                callback.onSuccess(response);
                            }
                        }, new DefaultErrorListener() {
                            @Override
                            protected void onErrorResponseFailed(String errorMesg, VolleyError volleyError) {
                                Toast.makeText(getActivity(), errorMesg, Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
            });
        }

    }

    private void getFriends(final VolleyCallback volleyCallback) {

        String url = FINDHOST + hostId;
        mConditionHttpApi.UserInfoController(url, getActivity(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                volleyCallback.onSuccess(response);
            }
        }, new DefaultErrorListener() {
            @Override
            protected void onErrorResponseFailed(String errorMesg, VolleyError volleyError) {
            }
        });
    }

    public interface VolleyCallback {
        void onSuccess(JSONObject result);

    }

    public class GridViewImageAdapter extends BaseAdapter {
        private List<Map<String,Object>> datas;//图片信息;
        private LayoutInflater layoutInflater;
        private Context mContext;

        public GridViewImageAdapter(Context context,
                                    List<Map<String,Object>> list) {

            this.datas = list;
            this.mContext = context;
            layoutInflater = LayoutInflater.from(context);

        }

        /**
         * 数据总数
         */
        @Override
        public int getCount() {

            return datas.size();
        }

        /**
         * 获取当前数据
         */
        @Override
        public Object getItem(int position) {

            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            MyViewHolder viewHolder = null;
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.item_published,null);
                viewHolder = new MyViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (MyViewHolder)convertView.getTag();
            }
            if(datas!=null && position<datas.size()){
                if(position==0){
                    ACache mACache = ACache.get(getActivity());
                    JSONObject jsonObject = mACache.getAsJSONObject("imagescache");
                    if(jsonObject!=null){
                        String fileimage = jsonObject.optString("imagescache");
                        Type listType = new TypeToken<List<Map<String,Object>>>() {
                        }.getType();
                        Gson gson = new Gson();
                        conditions = gson.fromJson(fileimage, listType);//将json字符串通过gson转化为对应的对象
                        final File file = new File(datas.get(position).get("path").toString());

                        Glide.with(mContext).load(file).priority(Priority.HIGH).into(((MyViewHolder) viewHolder).ivImage);
                        ((MyViewHolder) viewHolder).ivImage.setScaleType(ImageView.ScaleType.FIT_XY);
                        ((MyViewHolder) viewHolder).ivImage.setVisibility(View.VISIBLE);

                    }else {
                        String imagePath = IMAGE + datas.get(position).get("path").toString();
                        Uri uri = Uri.parse(imagePath);
                        Glide.with(mContext).load(uri).into(viewHolder.ivImage);
                        viewHolder.ivImage.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                } else{
                    /**获取网络图片**/

                    String imagePath = IMAGE + datas.get(position).get("path").toString();
                    Uri uri = Uri.parse(imagePath);
                    Glide.with(mContext).load(uri).into(viewHolder.ivImage);
                    viewHolder.ivImage.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }





            return convertView;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public  ImageView ivImage;
            public View itemview;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
                this.itemview = itemView;
            }
        }

    }
}
