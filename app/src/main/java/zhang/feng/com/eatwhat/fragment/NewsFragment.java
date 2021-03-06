package zhang.feng.com.eatwhat.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.youth.banner.Banner;

import java.util.List;

import androidx.fragment.app.Fragment;
import zhang.feng.com.eatwhat.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<String> images;//图片
    private List<String> imageTitles;//图片名字
    private Banner mBanner;//banner

    private RadioGroup rg_tab;//两个按钮选择框
    private NewsFragmentController controller;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
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
        View view = inflater.inflate(R.layout.fragment_news, container, false);


        controller = NewsFragmentController.getInstance(this,R.id.second_fragment);
        controller.showFragment(0);
        rg_tab = (RadioGroup) view.findViewById(R.id.rg_tab);
        rg_tab.setOnCheckedChangeListener(new RadioGroupListener());


//        images = new ArrayList<>();
//        images.add("http://47.112.28.145:8080/images/banner/lifestyledemo3.jpg");
//        images.add("http://47.112.28.145:8080/images/banner/lifestyledemo6.jpg");
//        images.add("http://47.112.28.145:8080/images/banner/lifestyledemo4.jpg");
//
//
//        //设置图片标题集合
//        imageTitles = new ArrayList<>();
//        imageTitles.add("aaa");
//        imageTitles.add("bbb");
//        imageTitles.add("ccc");
//
//
//        mBanner = (Banner)view.findViewById(R.id.head_banner);
//        //设置图片加载器
//        mBanner.setImageLoader(new GlideImageLoader());
//        //设置图片集合
//        mBanner.setImages(images);
//        //设置标题集合
//        mBanner.setBannerTitles(imageTitles);
//        //设置动画效果
//        mBanner.setBannerAnimation(Transformer.RotateDown);
//        mBanner.start();

        return view;
    }



    private class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {


        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.article_page:
                    controller.showFragment(0);
                    break;
                case R.id.video_page:
                    controller.showFragment(1);
                    break;
                default: break;
            }
        }

    }





    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);

    }

}
