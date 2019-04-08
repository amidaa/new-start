package zhang.feng.com.eatwhat.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.vov.vitamio.provider.MediaStore;
import io.vov.vitamio.widget.VideoView;
import zhang.feng.com.eatwhat.R;
import zhang.feng.com.eatwhat.VideoPlayActivity;
import zhang.feng.com.eatwhat.goods.Video;
import zhang.feng.com.eatwhat.goods.VideoLab;

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
    private Video mVideo;
    private VideosAdapter mAdapter;


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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        mVideosRecyclerView = (RecyclerView)view.findViewById(R.id.video_lists);
        mVideosRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));//设置为方块排列
        updateUI();
        return view;
    }

    private class VideosHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView mGuidanceView;
        private TextView mGuidanceText;
        private TextView mPostTime;
        private Uri uri;

        public VideosHolder(@NonNull View itemView) {
            super(itemView);
        }
        public VideosHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.list_news_video, parent, false));
            mGuidanceView = (ImageView)itemView.findViewById(R.id.guidance_video);
            mGuidanceText = (TextView)itemView.findViewById(R.id.title_video);
            mPostTime = (TextView)itemView.findViewById(R.id.video_post_time);
            itemView.setOnClickListener(this);
        }

        public void bind(Video video){
            mVideo = video;
            String imagePath = mVideo.getCoverImage();
            uri = Uri.parse(imagePath);
            Glide.with(getActivity()).load(uri).into(mGuidanceView);
            mGuidanceView.setClickable(true);
            mGuidanceText.setText(mVideo.getVideoName());
            mPostTime.setText(mVideo.getPostTime().toString());

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(),VideoPlayActivity.class);
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
    private void updateUI(){
        VideoLab videoLab = VideoLab.get(getActivity());
        List<Video> videos = videoLab.getVideos();


        if(mAdapter==null){
            mAdapter = new VideosAdapter(videos);
            mVideosRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();//如果数据集发生变化，就调用该方法
        }


    }



}
