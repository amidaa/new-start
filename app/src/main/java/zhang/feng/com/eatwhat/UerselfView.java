package zhang.feng.com.eatwhat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import feng.zhang.com.ratiolayout.DynamicAvatarView;
import feng.zhang.com.ratiolayout.RatioLayout;

public class UerselfView extends AppCompatActivity {
    private DynamicAvatarView mDynamic;
    private RatioLayout mRatio;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRatio = (RatioLayout) findViewById(R.id.ratio);
        mDynamic = (DynamicAvatarView) findViewById(R.id.head_portrait);
        final String[] texts = {"高低贵贱(0)", "活动方案的(0)", "IT发哈(0)", "发疯(0)", "额头和(0)", "法国热啊法国热啊(0)", "防护(0)", "夫人(0)", "德娃(0)"};
        final boolean[] bgs = { false, true, false, true, false, true, false, true,false};

        mRatio.addText(texts);
        mRatio.changeTextBackground(bgs);

        mRatio.setInnerCenterListener(new RatioLayout.InnerCenterListener() {
            @Override
            public void innerCenterHominged(int position, String text) {
//                texts[position] = addNumber(texts[position]);
                mRatio.changeText(texts);
            }

            @Override
            public void innerCenter(int position, String text) {
                if (position % 2 == 0) {
                    mRatio.setPlayLoveXin(true);
                } else {
                    mRatio.setPlayLoveXin(false);
                }
            }
        });
    }

    public void exit(View view) {
        //mDynamic.exitAnim();
        mRatio.exitBubble();
    }

    public void enter(View view) {
        //mDynamic.enterAnim();
        mRatio.enterBubble();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRatio.destry();
        System.out.println("-------------------onDestroy:");
    }

}

