package zhang.feng.com.eatwhat.fragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.today.step.lib.ISportStepInterface;
import com.today.step.lib.TodayStepManager;
import com.today.step.lib.TodayStepService;

import org.json.JSONArray;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;
import zhang.feng.com.eatwhat.R;
import zhang.feng.com.eatwhat.customview.ProgressView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WalkStepFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WalkStepFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int REFRESH_STEP_WHAT = 0;

    //循环取当前时刻的步数中间的间隔时间
    private long TIME_INTERVAL_REFRESH = 3000;

    private Handler mDelayHandler = new Handler(new TodayStepCounterCall());
    private int mStepSum;//总步数

    private ISportStepInterface iSportStepInterface;//提供的接口模块



    private TextView mCarlTextView;//当前消耗的能量


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ProgressView walk_progressView;


    public WalkStepFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WalkStepFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WalkStepFragment newInstance(String param1, String param2) {
        WalkStepFragment fragment = new WalkStepFragment();
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
        View view = inflater.inflate(R.layout.fragment_walk_step, container, false);

        //初始化计步模块
        TodayStepManager.startTodayStepService(getActivity().getApplication());

        mCarlTextView = (TextView)view.findViewById(R.id.cal_about);
        //开启计步Service，同时绑定Activity进行aidl通信
        Intent intent = new Intent(getActivity(), TodayStepService.class);
        getActivity().startService(intent);
        getActivity().bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //Activity和Service通过aidl进行通信
                iSportStepInterface = ISportStepInterface.Stub.asInterface(service);
                try {
                    mStepSum = iSportStepInterface.getCurrentTimeSportStep();
                    updateStepCount();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                mDelayHandler.sendEmptyMessageDelayed(REFRESH_STEP_WHAT, TIME_INTERVAL_REFRESH);

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);

        //计时器
        mhandmhandlele.post(timeRunable);



        walk_progressView = (ProgressView)view.findViewById(R.id.walk_progress);
        walk_progressView.setCurrentProgress(2000);
        walk_progressView.setMaxProgress(10000);


        return view;

    }

    class TodayStepCounterCall implements Handler.Callback {

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_STEP_WHAT: {
                    //每隔500毫秒获取一次计步数据刷新UI
                    if (null != iSportStepInterface) {
                        int step = 0;
                        try {
                            step = iSportStepInterface.getCurrentTimeSportStep();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        if (mStepSum != step) {
                            mStepSum = step;
                            updateStepCount();
                            updateCal();
                        }
                    }
                    mDelayHandler.sendEmptyMessageDelayed(REFRESH_STEP_WHAT, TIME_INTERVAL_REFRESH);

                    break;
                }
            }
            return false;
        }
    }

    private void updateStepCount() {
        Log.e("xx", "updateStepCount : " + mStepSum);
        walk_progressView.setCurrentProgress(mStepSum);

    }

    private void updateCal(){
        if (null != iSportStepInterface) {
            try {
                JSONArray jsonArray = new JSONArray(iSportStepInterface.getTodaySportStepArray());//获取jsonarray

                for(int i=0;i<jsonArray.length();i++) {
                    JSONObject subObject = jsonArray.getJSONObject(i);
                    mCarlTextView.setText("消耗卡路里" + subObject.getString("kaluli")+"千卡");//获取其中的kaluli
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /*****************计时器*******************/
    private Runnable timeRunable = new Runnable() {
        @Override
        public void run() {

            currentSecond = currentSecond + 1000;
            if (!isPause) {
                //递归调用本runable对象，实现每隔一秒一次执行任务
                mhandmhandlele.postDelayed(this, 1000);
            }
        }
    };
    //计时器
    private Handler mhandmhandlele = new Handler();
    private boolean isPause = false;//是否暂停
    private long currentSecond = 0;//当前毫秒数
/*****************计时器*******************/

    /**
     * 根据毫秒返回时分秒
     *
     * @param time
     * @return
     */
    public static String getFormatHMS(long time) {
        time = time / 1000;//总秒数
        int s = (int) (time % 60);//秒
        int m = (int) (time / 60);//分
        int h = (int) (time / 3600);//秒
        return String.format("%02d:%02d:%02d", h, m, s);
    }

}
