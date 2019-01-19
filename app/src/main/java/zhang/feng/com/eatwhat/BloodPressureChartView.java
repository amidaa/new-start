package zhang.feng.com.eatwhat;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class BloodPressureChartView extends AppCompatActivity {

    private LineChartView lineChart;
    String[] data={"10-22","11-22","12-22","1-22","6-22","5-23","5-22","6-22","5-23","5-22"};//x轴的标注
    int[] score = {50,42,90,33,10,74,22,18,79,20};//图表的数据点
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisValues = new ArrayList<AxisValue>();
    //设置x轴的显示
    private  void getAxisXLables(){
        for(int i=0;i<data.length;i++){
            mAxisValues.add(new AxisValue(i).setLabel(data[i]));
        }
    }
    //图表的每个点的显示
    private void getAxisPoints(){
        for(int i=0;i<score.length;i++){
            mPointValues.add(new PointValue(i,score[i]));
        }
    }
    private void initLineChart(){
        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41"));//设置折线颜色
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状
        line.setCubic(false);//曲线是否平滑，即使曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
        //line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据，设置为true就无效
        line.setHasLines(true);//是否用线显示，如果为false，则没有曲线只有点
        line.setHasPoints(true);//是否显示圆点，如果为false，则没有原点只有点表示
        lines.add(line);
        LineChartData ldata = new LineChartData();
        ldata.setLines(lines);
        //坐标轴
        Axis axisX = new Axis();//X轴
        axisX.setHasTiltedLabels(true);//x轴字体坐标是斜的显示还是直的，true是直的显示
        axisX.setTextColor(Color.WHITE);//设置字体颜色
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(8);//最多几个x轴坐标，缩放让x轴的数据的个数7<=x<=maxis
        axisX.setValues(mAxisValues);//填充x轴的坐标名称
        ldata.setAxisXBottom(axisX);//x轴在底部
        axisX.setHasLines(true);//x轴分割线
        //y轴是根据数目大小自动设置Y轴上限
        Axis axisY = new Axis();//Y轴
        axisY.setName("");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        ldata.setAxisYLeft(axisY);//y轴设置在左边


        //设置行为属性，支持缩放，滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setMaxZoom((float)2);//最大方法比例
        lineChart.setContainerScrollEnabled(true,ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(ldata);
        lineChart.setVisibility(View.VISIBLE);

        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right = 7;
        lineChart.setCurrentViewport(v);


    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linerchart);
        lineChart = (LineChartView)findViewById(R.id.bloodpressurechart);


    }
}
