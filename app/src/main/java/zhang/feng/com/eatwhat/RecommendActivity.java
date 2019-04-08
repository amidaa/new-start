package zhang.feng.com.eatwhat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import zhang.feng.com.eatwhat.goods.Food;
import zhang.feng.com.eatwhat.ultimaterecycleview.CommanderHolder;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.marshalchen.ultimaterecyclerview.ObservableScrollState;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.marshalchen.ultimaterecyclerview.itemTouchHelper.SimpleItemTouchHelperCallback;
import com.marshalchen.ultimaterecyclerview.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.List;

public class RecommendActivity extends AppCompatActivity {
    private UltimateRecyclerView mUltimateRecyclerView;
    private CommanderHolder mHolder;
    private Handler mHandler;
    private View headView;
    private UltimateViewAdapter mAdapter;
    private List<Food> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.activity_recommend);
        mHandler = new Handler();
        mUltimateRecyclerView = (UltimateRecyclerView)findViewById(R.id.recommend_recyclerView);
        //设置布局的格式
        mUltimateRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        headView = LayoutInflater.from(this).inflate(R.layout.head_view,null);
        mAdapter = new CommanderAdapter(data);
        mUltimateRecyclerView.setAdapter(mAdapter);
//      为每一个item加入头部的布局,相当于栏目条
        StickyRecyclerHeadersDecoration stickyRecyclerHeadersDecoration = new StickyRecyclerHeadersDecoration(mAdapter);
        mUltimateRecyclerView.addItemDecoration(stickyRecyclerHeadersDecoration);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter){
            @Override
            public int getMovementFlags(RecyclerView recyclerView,RecyclerView.ViewHolder viewHolder){
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//控制拖动方向
                final int swipeFlags = ItemTouchHelper.LEFT;//控制滑动删除的方向，左滑删除
                return makeMovementFlags(dragFlags,swipeFlags);
            }

            @Override
            public boolean isItemViewSwipeEnabled(){
                return true;//控制开启或者关闭item能够滑动删除的功能
            }


            @Override
            public boolean isLongPressDragEnabled() {
                return true;//控制长按拖动功能
            }


        };
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mUltimateRecyclerView.mRecyclerView);



        mUltimateRecyclerView.setParallaxHeader(headView);//设置头部
        mUltimateRecyclerView.enableDefaultSwipeRefresh(true);//开启下拉刷新
//        mUltimateRecyclerView.isLoadMoreEnabled();//开启上拉载入更多
        //下拉刷新的监听函数
        mUltimateRecyclerView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mUltimateRecyclerView.setRefreshing(false);
                    }
                },2000);
            }
        });


    }

    class CommanderAdapter extends UltimateViewAdapter<CommanderHolder>{
        private List<Food> mFoodList;


        public CommanderAdapter(List<Food> foods){
            mFoodList = foods;
        }



        @Override
        public CommanderHolder newFooterHolder(View view) {
            return null;
        }

        @Override
        public CommanderHolder newHeaderHolder(View view) {
            return new CommanderHolder(view);
        }

        @Override
        public CommanderHolder onCreateViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(RecommendActivity.this).inflate(R.layout.commander_item,null);
            return new CommanderHolder(view);
        }
//      返回item个数，不包含头部和载入view
        @Override
        public int getAdapterItemCount() {
            return mFoodList==null?0:mFoodList.size();
        }

        @Override
        public long generateHeaderId(int position) {
            if(customHeaderView!=null){
                position-=1;
            }
            String s = position+"";
            return s.charAt(0);
        }
        //为每一项item生成头部的view

        @Override
        public void onBindViewHolder(@NonNull CommanderHolder holder, int position) {

            if (position < getItemCount() && (customHeaderView != null ? position <= mFoodList.size() : position < mFoodList.size()) && (customHeaderView != null ? position > 0 : true)){
                position-=customHeaderView==null?0:1;
                holder.foodName.setText(mFoodList.get(position).getFoodName());
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {

            View view = LayoutInflater.from(RecommendActivity.this).inflate(R.layout.commander_item,null);
            return new CommanderHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

            if(customHeaderView!=null){
                i-=1;
            }
            ((CommanderHolder)viewHolder).foodName.setText("header"+(i+"").charAt(0));
            ((CommanderHolder)viewHolder).foodName.setTextColor(Color.parseColor("#FFEEFF"));
        }
          //重写拖动移位位置功能
        @Override
        public void onItemMove(int fromPosition,int toPosition){
            swapPositions(data,fromPosition,toPosition);
            super.onItemMove(fromPosition,toPosition);
        }


        @Override
        public void onItemDismiss(int position){
            data.remove(position);
            super.onItemDismiss(position);
        }
    }

    private void initData(){
        data = new ArrayList<>();
        for(int i=0;i<10;i++){
            Food food = new Food();
            food.setFoodName("zhognguo"+i);
            data.add(food);
        }
    }
}
