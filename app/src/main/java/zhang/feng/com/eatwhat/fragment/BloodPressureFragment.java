package zhang.feng.com.eatwhat.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import zhang.feng.com.eatwhat.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BloodPressureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BloodPressureFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button mBy_handButton;//手动输入按钮
    private Button mSphygmomanometerButton;//第三方测量器接口
    private Button mConfirmButton;//输入确认按钮

    private EditText mHighPressure;//高压填写文本
    private EditText mLowPressure;//低压填写文本

    private TextView mHighPressureTextView;//显示高压的文本
    private TextView mLowPressureTextView;//显示低压的文本

    private ImageView mHighImageView;
    private ImageView mLowImageView;




    public BloodPressureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BloodPressureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BloodPressureFragment newInstance(String param1, String param2) {
        BloodPressureFragment fragment = new BloodPressureFragment();
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
        View view = inflater.inflate(R.layout.fragment_blood_pressure, container, false);
        mBy_handButton = (Button)view.findViewById(R.id.by_hand);
        mSphygmomanometerButton = (Button)view.findViewById(R.id.sphygmomanometer);
        mConfirmButton = (Button)view.findViewById(R.id.input_confirm);


        mHighPressure = (EditText)view.findViewById(R.id.write_high);
        mLowPressure = (EditText)view.findViewById(R.id.write_low);

        mHighPressureTextView = (TextView)view.findViewById(R.id.high_pressure_text);
        mLowPressureTextView = (TextView)view.findViewById(R.id.low_pressure_text);


        mHighImageView = (ImageView)view.findViewById(R.id.high_pressure_img);
        mLowImageView = (ImageView)view.findViewById(R.id.low_pressure_img);
        doWrite();

        return  view;
    }


    public void doWrite(){
        mBy_handButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHighPressure.setVisibility(View.VISIBLE);
                mLowPressure.setVisibility(View.VISIBLE);
                mConfirmButton.setVisibility(View.VISIBLE);
                mHighPressureTextView.setVisibility(View.GONE);
                mLowPressureTextView.setVisibility(View.GONE);
                mHighImageView.setVisibility(View.GONE);
                mLowImageView.setVisibility(View.GONE);


            }
        });

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mHighPressure!=null){
                    mHighPressureTextView.setText("高压:"+mHighPressure.getText()+"mmHg");
                }
                if(mLowPressure!=null){
                    mLowPressureTextView.setText("低压:"+mLowPressure.getText()+"mmHg");
                }

                mHighPressure.setVisibility(View.GONE);
                mLowPressure.setVisibility(View.GONE);
                mConfirmButton.setVisibility(View.GONE);
                mHighPressureTextView.setVisibility(View.VISIBLE);
                mLowPressureTextView.setVisibility(View.VISIBLE);
                mHighImageView.setVisibility(View.VISIBLE);
                mLowImageView.setVisibility(View.VISIBLE);
            }
        });
    }

}
