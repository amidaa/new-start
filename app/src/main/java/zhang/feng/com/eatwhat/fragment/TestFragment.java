package zhang.feng.com.eatwhat.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import zhang.feng.com.eatwhat.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link TestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
//    private LineChartView lineChart;
    private lecho.lib.hellocharts.view.ColumnChartView columnChartView;
    private ColumnChartData mColumnChartData;    //柱状图数据
    public final static String[] xValues = new String[]{"星期天", "星期一", "星期二", "星期三", "星期四", "星期五","星期六"};


    public TestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestFragment newInstance(String param1, String param2) {
        TestFragment fragment = new TestFragment();
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
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        columnChartView= (ColumnChartView) view.findViewById(R.id.bloodpressurechart);
        initDate();
        return view;
    }



    private void initDate() {
        /*========== 柱状图数据填充 ==========*/
        List<Column> columnList = new ArrayList<>(); //柱子列表
        List<SubcolumnValue> subcolumnValueList;     //子柱列表（即一个柱子，因为一个柱子可分为多个子柱）
        List<AxisValue> axisValues = new ArrayList<>();//创建x轴数据
        for (int i = 0; i < 6; ++i) {
            subcolumnValueList = new ArrayList<>();//每个子柱的集合
            subcolumnValueList.add(new SubcolumnValue((float) Math.random() * 3000, ChartUtils.pickColor()));//每个子柱集合的数据
            axisValues.add(new AxisValue(i).setLabel(xValues[i]));
            Column column = new Column(subcolumnValueList);//创建子柱数据
            column.setHasLabels(true);                    //设置列标签
            columnList.add(column);//添加柱子数据

        }
        mColumnChartData = new ColumnChartData(columnList);        //设置数据
        /*===== 坐标轴相关设置 =====*/
        Axis axisX = new Axis(axisValues);//设置横坐标柱子下面的分类
        Axis axisY = new Axis().setHasLines(true);
        axisX.setName("日期");    //设置横轴名称
        axisY.setName("卡路里");    //设置竖轴名称
        mColumnChartData.setAxisXBottom(axisX); //设置横轴
        mColumnChartData.setAxisYLeft(axisY);   //设置竖轴
        columnChartView.setZoomEnabled(false);//不可点击
        //以上所有设置的数据、坐标配置都已存放到mColumnChartData中，接下来给mColumnChartView设置这些配置
        columnChartView.setColumnChartData(mColumnChartData);
        Viewport v = columnChartView.getMaximumViewport();//设置ｙ轴的长度
        v.top = 3003;
        columnChartView.setCurrentViewport(v);
    }
}
