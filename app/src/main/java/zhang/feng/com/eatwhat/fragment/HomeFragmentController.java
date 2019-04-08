package zhang.feng.com.eatwhat.fragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HomeFragmentController {
    private int containerId;
    private FragmentManager fm;
    private List<Fragment> fragments;

    private static HomeFragmentController controller;


    public static HomeFragmentController getInstance(Fragment parentFragment,int containerId){
        if(controller == null){
            controller = new HomeFragmentController(parentFragment,containerId);
        }
        return controller;
    }


    private HomeFragmentController(Fragment fragment,int containerId){
        this.containerId = containerId;
        fm = fragment.getChildFragmentManager();
        initFragment();
    }

    private void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new BreakFastFragment());
        fragments.add(new LunchFragment());
        fragments.add(new DinnerFragment());
        fragments.add(new SnacksFragment());

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
