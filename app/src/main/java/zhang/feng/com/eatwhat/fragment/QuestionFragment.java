package zhang.feng.com.eatwhat.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zkk.view.rulerview.RulerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import zhang.feng.com.eatwhat.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RadioGroup rg_gender;
    private RadioButton rg_male;//男
    private RadioButton rg_female;//女
    private RadioGroup rg_age;//年龄
    private RadioButton rg_21_30;//21-30岁年龄段
    private RadioButton rg_31_40;//31-40岁年龄段
    private RadioButton rg_41_50;//41-50岁年龄段
    private RadioButton rg_51_60;//51-60岁年龄段
    private RadioButton rg_60_;//60及以上岁年龄段

    private TextView mHeightTextView;//身高
    private TextView mWeightTextView;//体重
    private TextView tx_height;//身高
    private TextView tx_weight;//体重

    private RulerView ruler_height;//身高标尺;
    private RulerView ruler_weight;//体重标尺

    private RadioGroup rg_exercise_level;//运动频率
    private RadioButton rg_exercise_level_never;//从不
    private RadioButton rg_exercise_level_a_little;//很少
    private RadioButton rg_exercise_sometime;//偶尔
    private RadioButton rg_exercise_onece_or_twice;//一周一次或者两次
    private RadioButton rg_exercise_level_more_than_three;//3-4次以及更多


    private RadioGroup rg_illness_time;//患病时间
    private RadioButton rg_illness_more_than_five;//5年以上
    private RadioButton rg_illness_three_to_five;//3-5年
    private RadioButton rg_illness_one_to_three;//1-3年
    private RadioButton rg_illness_in_a_year;//一年内


    private CheckBox illness_box_no;//没有慢性病
    private CheckBox illness_box_diabetes;//糖尿病
    private CheckBox illness_box_hypertension;//高血压
    private CheckBox illness_box_hyperlipemia;//高血脂
    private CheckBox illness_box_coronary;//冠心病
    private CheckBox illness_box_CVA;//脑卒中
    private CheckBox illness_box_fat;//肥胖
    private CheckBox illness_box_gout;//痛风
    private CheckBox illness_box_OP;//骨质疏松


    private String gender;
    private String age;
    private String sports;
    private String illness_time;
    private ArrayList<String> illness = new ArrayList<>();
    private String height;
    private String weight;






    private Button btn_submit;//提交按钮



    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(String param1, String param2) {
        QuestionFragment fragment = new QuestionFragment();
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
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        init(view);
        return view;
    }

    public void init(View view){
        //        性别
        rg_gender= view.findViewById(R.id.rg_gender);
        rg_male = view.findViewById(R.id.rb_male);
        rg_female= view.findViewById(R.id.rb_female);
//        年龄
        rg_age= view.findViewById(R.id.age);
        rg_21_30= view.findViewById(R.id.rb_age_21_30);
        rg_31_40= view.findViewById(R.id.rb_age_31_40);
        rg_41_50= view.findViewById(R.id.rb_age_41_50);
        rg_51_60= view.findViewById(R.id.rb_age_51_60);
        rg_60_ = view.findViewById(R.id.rb_age_more_than_60);
//身高,体重

        mHeightTextView = view.findViewById(R.id.height);
        mWeightTextView = view.findViewById(R.id.weight);
        tx_height = view.findViewById(R.id.height_content);
        tx_weight = view.findViewById(R.id.weight_content);


//
        ruler_height = view.findViewById(R.id.ruler_height);
        ruler_height.setOnValueChangeListener(new RulerView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                tx_height.setText(value+"");

            }
        });
        /**
         *
         * @param selectorValue 未选择时 默认的值 滑动后表示当前中间指针正在指着的值
         * @param minValue   最大数值
         * @param maxValue   最小的数值
         * @param per   最小单位  如 1:表示 每2条刻度差为1.   0.1:表示 每2条刻度差为0.1 在demo中 身高mPerValue为1  体重mPerValue 为0.1
         */
        ruler_height.setValue(165, 80, 250, 1);


       ruler_weight = view.findViewById(R.id.ruler_weight);
        ruler_weight.setOnValueChangeListener(new RulerView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                tx_weight.setText(value+"");

            }
        });

        ruler_weight.setValue((float)50.0,(float)30.0,(float)150.0, (float)0.1);


//        运动频率
        rg_exercise_level = view.findViewById(R.id.rg_exercise_level);
        rg_exercise_level_a_little= view.findViewById(R.id.rb_exercise_a_little);
        rg_exercise_level_never= view.findViewById(R.id.rb_exercise_never);
        rg_exercise_sometime = view.findViewById(R.id.rb_exercise_sometime);
        rg_exercise_onece_or_twice = view.findViewById(R.id.rb_exercise_once_or_twice);
        rg_exercise_level_more_than_three= view.findViewById(R.id.rb_exercis_more_than_three);
//患病时间
        rg_illness_time= view.findViewById(R.id.illness_time);
        rg_illness_more_than_five= view.findViewById(R.id.illness_time_more_than_five);
        rg_illness_three_to_five= view.findViewById(R.id.illness_time_three_to_five);
        rg_illness_one_to_three= view.findViewById(R.id.illness_time_one_to_three);
        rg_illness_in_a_year= view.findViewById(R.id.illness_time_in_a_year);
//        患病情况
        illness_box_diabetes= view.findViewById(R.id.cb_illness_diabetes);
        illness_box_no= view.findViewById(R.id.cb_illness_no);
        illness_box_hypertension= view.findViewById(R.id.cb_illness_hypertension);
        illness_box_hyperlipemia= view.findViewById(R.id.cb_illness_hyperlipemia);
        illness_box_coronary= view.findViewById(R.id.cb_illness_coronary);
        illness_box_CVA= view.findViewById(R.id.cb_illness_CVA);
        illness_box_fat= view.findViewById(R.id.cb_illness_fat);
        illness_box_gout= view.findViewById(R.id.cb_illness_gout);
        illness_box_OP= view.findViewById(R.id.cb_illness_OP);

//        建议
        btn_submit= view.findViewById(R.id.btn_submit);

//        存储信息的结构



        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("gender",gender);
                bundle.putString("age",age);
                bundle.putString("sports",sports);
                bundle.putString("illness_time",illness_time);
                bundle.putStringArrayList("illness",illness);
                bundle.putString("height",height);
                bundle.putString("weight",weight);

            }
        });

        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_male:
                        gender = rg_male.getText().toString();
                        break;
                    case R.id.rb_female:
                        gender = rg_female.getText().toString();
                        break;
                }
            }
        });

        rg_age.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_age_21_30:
                        age = rg_21_30.getText().toString();
                        break;
                    case R.id.rb_age_31_40:
                        age = rg_31_40.getText().toString();
                        break;
                    case R.id.rb_age_41_50:
                        age = rg_41_50.getText().toString();
                        break;
                    case R.id.rb_age_51_60:
                        age = rg_51_60.getText().toString();
                        break;
                    case R.id.rb_age_more_than_60:
                        age = rg_60_.getText().toString();
                        break;
                }
            }
        });


        rg_exercise_level.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_exercise_never:
                        sports = rg_exercise_level_never.getText().toString();
                        break;
                    case R.id.rb_exercise_once_or_twice:
                        sports = rg_exercise_onece_or_twice.getText().toString();
                        break;
                    case R.id.rb_exercise_a_little:
                        sports = rg_exercise_level_a_little.getText().toString();
                        break;
                    case R.id.rb_exercis_more_than_three:
                        sports = rg_exercise_level_more_than_three.getText().toString();
                        break;
                    case R.id.rb_exercise_sometime:
                        sports = rg_exercise_sometime.getText().toString();
                        break;
                }
            }
        });



        rg_illness_time.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.illness_time_more_than_five:
                        illness_time = rg_illness_more_than_five.getText().toString();
                        break;
                    case R.id.illness_time_three_to_five:
                        illness_time = rg_illness_three_to_five.getText().toString();
                        break;
                    case R.id.illness_time_one_to_three:
                        illness_time = rg_illness_one_to_three.getText().toString();
                        break;
                    case R.id.illness_time_in_a_year:
                        illness_time = rg_illness_in_a_year.getText().toString();
                        break;
                }
            }
        });

        illness_box_no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                illness.add(illness_box_no.getText().toString());
            }
        });
        illness_box_diabetes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                illness.add(illness_box_diabetes.getText().toString());
            }
        });
        illness_box_hyperlipemia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                illness.add(illness_box_hyperlipemia.getText().toString());
            }
        });
        illness_box_hypertension.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                illness.add(illness_box_hypertension.getText().toString());
            }
        });
        illness_box_OP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                illness.add(illness_box_OP.getText().toString());
            }
        });
        illness_box_gout.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                illness.add(illness_box_gout.getText().toString());
            }
        });


        illness_box_fat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                illness.add(illness_box_fat.getText().toString());
            }
        });


        illness_box_CVA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                illness.add(illness_box_CVA.getText().toString());
            }
        });
        illness_box_coronary.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                illness.add(illness_box_coronary.getText().toString());
            }
        });

        height = tx_height.getText().toString();
        weight = tx_weight.getText().toString();




    }


}
