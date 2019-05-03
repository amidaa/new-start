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
import zhang.feng.com.eatwhat.ParticularsActivity;
import zhang.feng.com.eatwhat.R;
import zhang.feng.com.eatwhat.goods.Article;
import zhang.feng.com.eatwhat.volleyopr.ArticleHttpApi;
import zhang.feng.com.eatwhat.volleyopr.DefaultErrorListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArticleHttpApi mArticleHttpApi;//网络api

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String IMAGEPATH="http://47.112.28.145:8080/images/articleimage/";


    private RecyclerView mArticlesRecyclerView;//RecyclerView
    private ArticlesAdapter mAdapter;


    public ArticleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArticleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticleFragment newInstance(String param1, String param2) {
        ArticleFragment fragment = new ArticleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArticleHttpApi = ArticleHttpApi.getInstance();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private class ArticlesHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView mGuidanceView;
        private TextView mGuidanceText;
        private TextView mPostTime;
        private Article mArticle;

        public ArticlesHolder(@NonNull View itemView) {
            super(itemView);
        }
        public ArticlesHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.list_news_article, parent, false));
            mGuidanceView = (ImageView)itemView.findViewById(R.id.guidance_photo);
            mGuidanceText = (TextView)itemView.findViewById(R.id.guidance_article);
            mPostTime = (TextView)itemView.findViewById(R.id.post_time);
            itemView.setOnClickListener(this);
        }

        public void bind(Article article){
            mArticle = article;
            String imagePath = IMAGEPATH + article.getAuthorUser();
            Uri uri = Uri.parse(imagePath);
            Glide.with(getActivity()).load(uri).into(mGuidanceView);
            mGuidanceText.setText(article.getTitle());
            mPostTime.setText(article.getCreateTime().substring(0,10));

        }

        @Override
        public void onClick(View v) {
            Intent intent = ParticularsActivity.newIntent(getActivity(),mArticle.getContent());
            startActivity(intent);

        }
    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                JSONArray jsonArray = result.optJSONArray("article");//获取后端服务器的jsonArray对象
                String str = jsonArray.toString();//将json对象转换为json字符串
                Type listType = new TypeToken<ArrayList<Article>>(){}.getType();
                Gson gson = new Gson();
                ArrayList<Article> articles = gson.fromJson(str, listType);//将json字符串通过gson转化为对应的对象
                if(mAdapter==null){
                    mAdapter = new ArticlesAdapter(articles);
                    mArticlesRecyclerView.setAdapter(mAdapter);
                }else {
                    mAdapter.notifyDataSetChanged();//如果数据集发生变化，就调用该方法
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_article, container, false);
        mArticlesRecyclerView = (RecyclerView)view.findViewById(R.id.articles_content);
        mArticlesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                JSONArray jsonArray = result.optJSONArray("article");//获取后端服务器的jsonArray对象
                String str ="";
                str= jsonArray.toString();//将json对象转换为json字符串
                Type listType = new TypeToken<ArrayList<Article>>(){}.getType();
                Gson gson = new Gson();
                ArrayList<Article> articles = gson.fromJson(str, listType);//将json字符串通过gson转化为对应的对象
                if(mAdapter==null){
                    mAdapter = new ArticlesAdapter(articles);
                    mArticlesRecyclerView.setAdapter(mAdapter);
                }
            }
        });
        return view;
    }
    private class ArticlesAdapter extends RecyclerView.Adapter<ArticlesHolder>{
        private List<Article> mArticles;//文章的列表

        public ArticlesAdapter(List<Article> articles){
            mArticles = articles;
        }

        @NonNull
        @Override
        public ArticlesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ArticlesHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ArticlesHolder holder, int position) {
            Article article = mArticles.get(position);
            holder.bind(article);

        }

        @Override
        public int getItemCount() {
            return mArticles.size();
        }
    }


    private void updateUI(final VolleyCallback callback){
        mArticleHttpApi.ArticleFindController(getActivity(), new Response.Listener<JSONObject>() {


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
