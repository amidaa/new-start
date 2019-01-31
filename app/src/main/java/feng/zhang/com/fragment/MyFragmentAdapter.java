package feng.zhang.com.fragment;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyFragmentAdapter extends FragmentPagerAdapter {
    private FragmentManager fragmentManager;
    private List<Fragment> fragments;
    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }
    public MyFragmentAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
