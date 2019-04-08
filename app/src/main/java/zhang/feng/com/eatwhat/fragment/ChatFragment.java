package zhang.feng.com.eatwhat.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.zhuang.likeviewlibrary.LikeView;

import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zhang.feng.com.eatwhat.ParticularsActivity;
import zhang.feng.com.eatwhat.PublishActivity;
import zhang.feng.com.eatwhat.goods.ChatLab;
import zhang.feng.com.eatwhat.goods.Issue;
import zhang.feng.com.eatwhat.R;

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
    private List<Map<String,Object>> datas;//图片信息

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private class ChatHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mAuthorTextView;
        private TextView mDateTextView;
        private TextView mContentTextView;
        private Issue mIssue;
        private GridView mGridPhoto;
        private LikeView mLikeView;

        public ChatHolder(@NonNull View itemView) {
            super(itemView);
        }
        //构造方法实例化list_item_issue布局，传给父类
        public ChatHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_issue,parent,false));
            mAuthorTextView = (TextView)itemView.findViewById(R.id.issue_author);
            mDateTextView = (TextView)itemView.findViewById(R.id.issue_date);
            mContentTextView = (TextView)itemView.findViewById(R.id.article);
            mGridPhoto = (GridView)itemView.findViewById(R.id.issue_photo);
            mLikeView = (LikeView)itemView.findViewById(R.id.like_view);
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
        public void bind(Issue issue){
            mIssue = issue;
            mAuthorTextView.setText(mIssue.getmAuthor());
            mDateTextView.setText(mIssue.getmData().toString());
            mContentTextView.setText(mIssue.getmContent());
        }

        @Override
        public void onClick(View v) {

        }
    }

    private class ChatAdapter extends RecyclerView.Adapter<ChatHolder>{

        private List<Issue> mIssues;
        public ChatAdapter(List<Issue> issues){
            mIssues = issues;
        }
        public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
           return new ChatHolder(layoutInflater,parent);
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
        setHasOptionsMenu(true);//显示menu
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar)view.findViewById(R.id.chat_toolbar);
        toolbar.inflateMenu(R.menu.chat_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
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
        mChatRecyclerView=(RecyclerView)view.findViewById(R.id.chat_recycler_view);
        mChatRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);




    }
//获取菜单内容

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();//清除activity中的被调用的onCreateOptionsMenu方法
        menuInflater.inflate(R.menu.chat_toolbar,menu);
        super.onCreateOptionsMenu(menu,menuInflater);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.add:
//                Intent intent = new Intent(getActivity(), PublishActivity.class);
//                startActivity(intent);
//                break;
//            default:
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//
//    }

    private void updateUI(){
        ChatLab chatLab = ChatLab.get(getActivity());
        List<Issue> issues = chatLab.getmIssues();


        mAdapter = new ChatAdapter(issues);
        mChatRecyclerView.setAdapter(mAdapter);

    }

}
