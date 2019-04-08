package zhang.feng.com.eatwhat.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import zhang.feng.com.eatwhat.PersonalCenterActivity;
import zhang.feng.com.eatwhat.QuestionActivity;
import zhang.feng.com.eatwhat.R;
import zhang.feng.com.eatwhat.RecommendActivity;
import zhang.feng.com.eatwhat.SearchActivity;
import zhang.feng.com.eatwhat.SettingsActivity;
import zhang.feng.com.eatwhat.banner.GlideImageLoader;
import zhang.feng.com.eatwhat.dialog.CustomDialog;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private androidx.drawerlayout.widget.DrawerLayout mDrawerLayout;

    private RadioGroup rg_tabs;//四个按钮选择框
    private RadioGroup person_tabs;//四个用户信息的选择框
    private HomeFragmentController diet_controller;
    private PersonInfomationController person_controller;//用户信息的界面控制器


    private List<String> images;//图片
    private List<String> imageTitles;//图片名字
    private Banner mBanner;//banner


    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar)view.findViewById(R.id.toolbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        toolbar.inflateMenu(R.menu.toolbar);
        toolbar.setNavigationIcon(R.drawable.logo_start);
        toolbar.setTitle("首页");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search:
                        Intent search_intent = new Intent(getActivity(), SearchActivity.class);
                        startActivity(search_intent);
                        break;
                    case R.id.love:
                        Intent love_intent = new Intent(getActivity(), SearchActivity.class);
                        startActivity(love_intent);
                        break;
                    case R.id.setting:
                        Intent setting_intent = new Intent(getActivity(), SettingsActivity.class);
                        startActivity(setting_intent);
                        break;

                    default:
                        break;
                }
                return true;
            }
        });

//      浮动按钮的设置
        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v,"Data delete",Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getActivity(),"Data restored",Toast.LENGTH_SHORT).show();
//                    }
//                }).show();

                Intent recommend_intent = new Intent(getActivity(), RecommendActivity.class);
                startActivity(recommend_intent);
            }
        });

        //侧滑栏的设置
        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView)view.findViewById(R.id.nav_view);

        navigationView.setCheckedItem(R.id.nav_personal_center);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_personal_center:
                        mDrawerLayout.closeDrawers();
                        Intent personal_intent = new Intent(getActivity(), PersonalCenterActivity.class);
                        startActivity(personal_intent);
                        return true;
                    case R.id.nav_goal:
                        mDrawerLayout.closeDrawers();
                        Intent goal_intent = new Intent(getActivity(), PersonalCenterActivity.class);
                        startActivity(goal_intent);
                        return true;
                    case R.id.nav_talk:
                        mDrawerLayout.closeDrawers();
                        Intent talk_intent = new Intent(getActivity(), PersonalCenterActivity.class);
                        startActivity(talk_intent);
                        return true;
                    case R.id.nav_setting:
                        mDrawerLayout.closeDrawers();
                        Intent setting_intent = new Intent(getActivity(), PersonalCenterActivity.class);
                        startActivity(setting_intent);
                        return true;
                }
                return true;

            }
        });

        diet_controller = HomeFragmentController.getInstance(this,R.id.food_fragment);
        diet_controller.showFragment(0);
        rg_tabs = (RadioGroup) view.findViewById(R.id.rg_tab);
        rg_tabs.setOnCheckedChangeListener(new RadioGroupListener());



        person_controller = PersonInfomationController.getInstance(this,R.id.person_information);
        person_controller.showFragment(0);
        person_tabs = (RadioGroup)view.findViewById(R.id.person_tab);
        person_tabs.setOnCheckedChangeListener(new PersonRadioGroupListener());



        images = new ArrayList<>();
        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        images.add("https://img03.sogoucdn.com/app/a/100520024/a052fe72d9c204e2770ef7b3d2e8d161");
        images.add("https://img03.sogoucdn.com/app/a/100520024/39e5889e31170c0effe972f1e2468bd2");


        //设置图片标题集合
        imageTitles = new ArrayList<>();
        imageTitles.add("aaa");
        imageTitles.add("bbb");
        imageTitles.add("ccc");


        mBanner = (Banner)view.findViewById(R.id.home_banner);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //设置标题集合
        mBanner.setBannerTitles(imageTitles);
        //设置动画效果
        mBanner.setBannerAnimation(Transformer.RotateDown);
        mBanner.start();

        showDialog();



        return view;
    }

    private class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {


        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.breakfast:
                    diet_controller.showFragment(0);
                    break;
                case R.id.lunch:
                    diet_controller.showFragment(1);
                    break;
                case R.id.dinner:
                    diet_controller.showFragment(2);
                    break;
                case R.id.snacks:
                    diet_controller.showFragment(3);
                    break;
                default: break;
            }
        }

    }
    private class PersonRadioGroupListener implements RadioGroup.OnCheckedChangeListener {


        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.bloodpressure:
                    person_controller.showFragment(0);
                    break;
                case R.id.bloodglucose:
                    person_controller.showFragment(1);
                    break;
                case R.id.heartrate:
                    person_controller.showFragment(2);
                    break;
                case R.id.walkway:
                    person_controller.showFragment(3);
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



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R.menu.toolbar,menu);
        super.onCreateOptionsMenu(menu,menuInflater);
    }

    public void showDialog(){
        //点击弹出对话框
        final CustomDialog customDialog = new CustomDialog(getActivity());
        customDialog.setTitle("信息完善");
        customDialog.setMessage("请完善您的相关信息，以便我们好为您接下来的饮食进行更加合理的规划");
        customDialog.setYesOnclickListener("确定", new CustomDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                customDialog.dismiss();
                Intent intent = new Intent(getActivity(), QuestionActivity.class);
                startActivity(intent);
            }
        });
        customDialog.show();

    }


}
