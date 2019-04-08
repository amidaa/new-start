package zhang.feng.com.eatwhat.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import zhang.feng.com.eatwhat.R;

public class ItemView extends RelativeLayout {
    private TextView mContent;
    private TextView mRight;
    private ImageView mIvLeft;

    private int leftIcon;
    private String leftTitle;
    private String rightText;
    private View bottomLine;
    private View rightArrow;
    public ItemView(Context context) {
        this(context, null);
    }

    public ItemView(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ItemView);//从布局文件中获取内容
        Boolean isShowBottomLine = ta.getBoolean(R.styleable.ItemView_show_bottom_line, true);//得到是否显示底部下划线属性
        Boolean isShowLeftIcon = ta.getBoolean(R.styleable.ItemView_show_left_icon, true);//得到是否显示左侧图标属性标识
        Boolean isShowRightArrow = ta.getBoolean(R.styleable.ItemView_show_right_arrow, true);//得到是否显示右侧图标属性标识
        leftIcon=ta.getResourceId(R.styleable.ItemView_left_icon,R.drawable.back);//设置左侧图标
        leftTitle=ta.getString(R.styleable.ItemView_left_text);//设置左侧标题文字
        rightText = ta.getString(R.styleable.ItemView_right_text);//设置右侧文字描述
        ta.recycle();//共享资源，使用完必须收回它

        LayoutInflater.from(context).inflate(R.layout.itemview,this);
        //获取控件ID
        mIvLeft = (ImageView) findViewById(R.id.iv_left);
        mContent = (TextView) findViewById(R.id.left_text);
        mRight = (TextView)findViewById(R.id.right_text);

        //设置获取到的内容
        mIvLeft.setImageResource(leftIcon);
        mContent.setText(leftTitle);
        mRight.setText(rightText);
    }

    /**
     * 设置左边图片
     */
    public void setLeftIcon(int resourceId) {
        mIvLeft.setImageResource(resourceId);
    }

    /**
     * 设置内容
     */
    public void setContent(String name) {
        mContent.setText(name);
    }

    public void setRightText(String content) {
        mRight.setText(content);
    }

}
