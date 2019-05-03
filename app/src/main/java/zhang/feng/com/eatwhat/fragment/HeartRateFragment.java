package zhang.feng.com.eatwhat.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import zhang.feng.com.eatwhat.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HeartRateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HeartRateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageButton mSphygmomanometer_heartButton;//仪器测量
    private ImageButton mHeartHand;//手动输入
    private Button mConfirmButton;//确认
    private TextView mHeartText;//显示的心率数据
    private EditText mWriteEditText;//手动输入心率数据


    public HeartRateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HeartRateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HeartRateFragment newInstance(String param1, String param2) {
        HeartRateFragment fragment = new HeartRateFragment();
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
        View view = inflater.inflate(R.layout.fragment_heart_rate, container, false);
        mSphygmomanometer_heartButton = (ImageButton) view.findViewById(R.id.sphygmomanometer_heart);
        mConfirmButton = (Button)view.findViewById(R.id.heart_rate_confirm);
        mWriteEditText = (EditText)view.findViewById(R.id.heart_rate_write);
        mHeartHand = (ImageButton) view.findViewById(R.id.by_hand_heart);
        mHeartText = (TextView)view.findViewById(R.id.heart_rate_info);
        doWrite();
        return view;
    }


    public void doWrite(){
        mHeartHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHeartText.setVisibility(View.GONE);
                mWriteEditText.setVisibility(View.VISIBLE);
                mConfirmButton.setVisibility(View.VISIBLE);
            }
        });


        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHeartText.setText("心率每分钟"+mWriteEditText.getText()+"次");
                mWriteEditText.setVisibility(View.GONE);
                mConfirmButton.setVisibility(View.GONE);
                mHeartText.setVisibility(View.VISIBLE);
            }
        });
    }

}
