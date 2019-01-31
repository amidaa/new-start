package feng.zhang.com.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import feng.zhang.com.ChatLab;
import feng.zhang.com.Issue;
import zhang.feng.com.eatwhat.MajorActivity;
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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private class ChatHolder extends RecyclerView.ViewHolder{
        private TextView mAuthorTextView;
        private TextView mDateTextView;
        private TextView mContentTextView;
        private Issue mIssue;

        public ChatHolder(@NonNull View itemView) {
            super(itemView);
        }
        //构造方法实例化list_item_issue布局，传给父类
        public ChatHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_issue,parent,false));
            mAuthorTextView = (TextView)itemView.findViewById(R.id.issue_author);
            mDateTextView = (TextView)itemView.findViewById(R.id.issue_date);
            mContentTextView = (TextView)itemView.findViewById(R.id.article);

        }
        public void bind(Issue issue){
            mIssue = issue;
            mAuthorTextView.setText(mIssue.getmAuthor());
            mDateTextView.setText(mIssue.getmData().toString());
            mContentTextView.setText(mIssue.getmContent());
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
        mChatRecyclerView=(RecyclerView)view.findViewById(R.id.chat_recycler_view);
        Log.d("xxx", "startUI: *****************************");
        mChatRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }
    private void updateUI(){
        ChatLab chatLab = ChatLab.get(getActivity());
        List<Issue> issues = chatLab.getmIssues();


        mAdapter = new ChatAdapter(issues);
        mChatRecyclerView.setAdapter(mAdapter);
        Log.d(null, "updateUI: **********");

    }

}
