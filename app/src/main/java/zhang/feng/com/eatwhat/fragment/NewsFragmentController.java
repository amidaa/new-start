package zhang.feng.com.eatwhat.fragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NewsFragmentController<hideFragment> {
    private int containerId;
    private FragmentManager fm;
    private List<Fragment> fragments;


    private static NewsFragmentController controller;


    public static NewsFragmentController getInstance(Fragment parentFragment,int containerId){
        if(controller == null){
            controller = new NewsFragmentController(parentFragment,containerId);
        }
        return controller;
    }


    private NewsFragmentController(Fragment fragment,int containerId){
        this.containerId = containerId;
        fm = fragment.getChildFragmentManager();
        initFragment();
    }

    private void initFragment(){
        fragments = new ArrayList<Fragment>();
        fragments.add(new ArticleFragment());
        fragments.add(new VideoFragment());


        FragmentTransaction ft = fm.beginTransaction();
        for(Fragment fragment:fragments){
            ft.add(containerId,fragment);
        }
        ft.commit();

    }


    public void showFragment(int position){
        hideFragments();
        Fragment fragment = fragments.get(position);
        FragmentTransaction ft = fm.beginTransaction();
        ft.show(fragment);
        ft.commitAllowingStateLoss();
    }
    public void hideFragments(){
        FragmentTransaction ft = fm.beginTransaction();
        for(Fragment fragment:fragments){
            if(fragment != null){
                ft.hide(fragment);
            }
        }

        ft.commitAllowingStateLoss();
    }

    public Fragment getFragment(int position){
        return fragments.get(position);
    }
}
