package zhang.feng.com.eatwhat.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zhang.feng.com.eatwhat.R;
import zhang.feng.com.eatwhat.VideoPlayActivity;
import zhang.feng.com.eatwhat.goods.Video;
import zhang.feng.com.eatwhat.volleyopr.DefaultErrorListener;
import zhang.feng.com.eatwhat.volleyopr.VolleyHttpApi;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mVideosRecyclerView;//RecyclerView
    private VideosAdapter mAdapter;
    private String VIDEOURL = "http://47.112.28.145:8090/videoApi/all";
    private String HOST="http://47.112.28.145:8080/images/videocover/";

    VolleyHttpApi mVolleyHttpApi;//网络接口


    public VideoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VideoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mVolleyHttpApi = VolleyHttpApi.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        mVideosRecyclerView = (RecyclerView)view.findViewById(R.id.video_lists);
        mVideosRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//设置为方块排列
        updateUI(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                JSONArray jsonArray = result.optJSONArray("videos");//获取后端服务器的jsonArray对象
                String str ="";
                str= jsonArray.toString();//将json对象转换为json字符串
                Type listType = new TypeToken<ArrayList<Video>>(){}.getType();
                Gson gson = new Gson();
                ArrayList<Video> videos = gson.fromJson(str, listType);//将json字符串通过gson转化为对应的对象
                if(mAdapter==null){
                    mAdapter = new VideosAdapter(videos);
                    mVideosRecyclerView.setAdapter(mAdapter);
                }else {
                    mAdapter.notifyDataSetChanged();//如果数据集发生变化，就调用该方法
                }
            }
        });
        return view;
    }

    private class VideosHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView mGuidanceView;
        private ImageView mStartVideo;
        private TextView mGuidanceText;
        private TextView mPostTime;
        private Uri uri;
        private Video mVideo;

        public VideosHolder(@NonNull View itemView) {
            super(itemView);
        }
        public VideosHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.list_news_video, parent, false));
            mGuidanceView = (ImageView)itemView.findViewById(R.id.guidance_video);
            mGuidanceText = (TextView)itemView.findViewById(R.id.title_video);
            mPostTime = (TextView)itemView.findViewById(R.id.video_post_time);
            mStartVideo = (ImageView)itemView.findViewById(R.id.start_video);
            mStartVideo.setOnClickListener(this);
        }

        public void bind(Video video){
            mVideo = video;
            String imagePath = HOST + video.getVideoCover();
            uri = Uri.parse(imagePath);
            Glide.with(getActivity()).load(uri).into(mGuidanceView);
            mGuidanceView.setClickable(true);
            mGuidanceText.setText(video.getVideoNameString());
            if(video.getVideoDate()!=null){
                mPostTime.setText(video.getVideoDate().substring(0,10));
            }else{
                mPostTime.setText("2019-04-27");
            }


        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
            intent.putExtra("videoUrl",mVideo.getVideoUrl());
            startActivity(intent);

        }
    }


    private class VideosAdapter extends RecyclerView.Adapter<VideosHolder>{
        private List<Video> mVideos;//视频的列表

        public VideosAdapter(List<Video> videos){
            mVideos = videos;
        }

        @NonNull
        @Override
        public VideosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new VideosHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull VideosHolder holder, int position) {
            Video video = mVideos.get(position);
            holder.bind(video);

        }

        @Override
        public int getItemCount() {
            return mVideos.size();
        }
    }
    private void updateUI(final VolleyCallback callback){
        mVolleyHttpApi.VideoShowController(VIDEOURL, getActivity(),new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response);
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
