package zhang.feng.com.eatwhat.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

import zhang.feng.com.eatwhat.ParticularsActivity;
import zhang.feng.com.eatwhat.R;
import zhang.feng.com.eatwhat.goods.Article;
import zhang.feng.com.eatwhat.goods.ArticleLab;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ParticularsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ParticularsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Article mArticle;
    private TextView mTitle;//标题
    private TextView mWholeArticle;//全文


    private static final String ARG_NEWS_ID = "news_id";

//用自定义的构造方法传递UUID参数

    public static ParticularsFragment newInstance(UUID newsId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_NEWS_ID,newsId);


        ParticularsFragment fragment = new ParticularsFragment();
        fragment.setArguments(args);
        return fragment;

    }


    public ParticularsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ParticularsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ParticularsFragment newInstance(String param1, String param2) {
        ParticularsFragment fragment = new ParticularsFragment();
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
        UUID newsId = (UUID)getArguments().getSerializable(ARG_NEWS_ID);//通过argument获取文章编号
        mArticle = ArticleLab.get(getActivity()).getArticle(newsId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_particulars, container, false);
        mTitle = (TextView)view.findViewById(R.id.title);
        mWholeArticle = (TextView)view.findViewById(R.id.whole_article);
        mTitle.setText(mArticle.getmArticleName());
        mWholeArticle.setText(mArticle.getmArticleDetails());


        return view;

    }

}
