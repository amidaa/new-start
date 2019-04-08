package zhang.feng.com.eatwhat.fragment;


import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zhang.feng.com.eatwhat.R;
import zhang.feng.com.eatwhat.goods.Food;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SnacksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SnacksFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Food> mSnackFoods;
    private RecyclerView mSnackRecyclerView;


    public SnacksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SnacksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SnacksFragment newInstance(String param1, String param2) {
        SnacksFragment fragment = new SnacksFragment();
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
       View view = inflater.inflate(R.layout.fragment_snacks, container, false);
       preparDate();
       mSnackRecyclerView = (RecyclerView) view.findViewById(R.id.snacks_food_form);
       SnackAdapter adapter = new SnackAdapter(R.layout.item_foodlist,mSnackFoods);
       mSnackRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       mSnackRecyclerView.setAdapter(adapter);
       return view;
    }

    public class SnackAdapter extends BaseQuickAdapter<Food, BaseViewHolder> {

        public SnackAdapter(@LayoutRes int layoutResId, @Nullable List<Food> foods){
            super(layoutResId,foods);
        }
        @Override
        protected void convert(BaseViewHolder helper, Food item) {

            helper.setText(R.id.food_name,item.getFoodName());
        }
    }

    private void preparDate(){
        mSnackFoods = new ArrayList<>();
        for(int i=0;i<2;i++){
            Food food = new Food();
            food.setFoodName("snack"+i);
            food.setFondWeight("1kg");
            mSnackFoods.add(food);
        }

    }

}
