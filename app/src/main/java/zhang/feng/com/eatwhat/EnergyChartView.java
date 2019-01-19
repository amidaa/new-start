package zhang.feng.com.eatwhat;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class EnergyChartView extends AppCompatActivity {

    private PieChartView pie_chart;//饼形图控件
    private PieChartData pie_data;//饼形图数据
    List<SliceValue> values = new ArrayList<SliceValue>();
    private int[] data = {12, 34, 5, 6, 21};//定义假数据
    private int[] colorData = {Color.parseColor("#ec063d"),
            Color.parseColor("#f1c704"),
            Color.parseColor("#c9c9c9"),
            Color.parseColor("#2bc208"),
            Color.parseColor("#333333"),};
    private String[] state_chart = {"脂肪", "碳水化合物", "蛋白质", "维生素", "氨基酸"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.energychart);
        pie_chart = (PieChartView) findViewById(R.id.piechart);
        pie_chart.setOnValueTouchListener(selectListener);
        setPieChartData();
        initPieChart();

    }

    //    获取数据
    private void setPieChartData() {
        for (int i = 0; i < data.length; i++) {
            SliceValue sliceValue = new SliceValue((float) data[i], colorData[i]);
            values.add(sliceValue);
        }
    }

    //  初始化
    private void initPieChart() {
        pie_data = new PieChartData();
        pie_data.setHasLabels(true);//显示表情
        pie_data.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比
        pie_data.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
        pie_data.setHasCenterCircle(true);//是否是环形显示
        pie_data.setValues(values);//填充数据
        pie_data.setCenterCircleColor(Color.WHITE);//设置环形中间的颜色
        pie_data.setCenterCircleScale(0.5f);//设置环形的大小级别
        pie_chart.setPieChartData(pie_data);
        pie_chart.setValueSelectionEnabled(true);//选择饼图某一块变大
        pie_chart.setAlpha(0.9f);//设置透明度
        pie_chart.setCircleFillRatio(1f);//设置饼图大小
    }

    //  监听事件
    private PieChartOnValueSelectListener selectListener = new PieChartOnValueSelectListener() {
        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
            //选择对应图形后，在中间部分显示相应信息
            pie_data.setCenterText1(state_chart[0]);
            pie_data.setCenterText1Color(colorData[arcIndex]);
            pie_data.setCenterText1FontSize(10);
            pie_data.setCenterText2(value.getValue() + "("+calPerent(arcIndex) + ")");
            pie_data.setCenterText2Color(colorData[arcIndex]);
            pie_data.setCenterText2FontSize(12);

        }

        @Override
        public void onValueDeselected() {

        }
    };
    private String calPerent(int i){
        String result = "";
        int sum = 0;
        for(int j = 0;j<data.length;j++){
            sum += data[j];
        }
        result = String.format("%.2f",(float)data[i]*100/sum+"%");
        return result;
    }

}
