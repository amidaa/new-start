package zhang.feng.com.eatwhat;
/*
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class ImageCircleView extends android.support.v7.widget.AppCompatImageView {
   private static final Xfermode MASK_XFERMODE;
   private Bitmap mask;//位图
   private Paint paint;//画布
   static {
       PorterDuff.Mode localMode = PorterDuff.Mode.DST_IN;
       MASK_XFERMODE = new PorterDuffXfermode(localMode);
   }
    public ImageCircleView(Context context) {
        super(context);
    }
    public ImageCircleView(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    private Bitmap createCircleMap(){
        int width = getWidth();//获取图片的宽度
        int height = getHeight();//获取图片的高度
        int min = Math.min(width,height);//图片长度不一致，按小的进行裁剪
        Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;//设置颜色制式
        Bitmap localBitmap = Bitmap.createBitmap(min,min,localConfig);
        Canvas canvas = new Canvas(localBitmap);//创建一个画板

        Paint paint = new Paint(1);//创建画笔
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);//防止出现边缘锯齿
        paint.setFilterBitmap(true);//对画布进行滤波处理
        canvas.drawCircle(min/2,min/2,min/2,paint);//绘制一个圆
        return localBitmap;
    }
    @Override
    protected void onDraw(Canvas canvas){
        Drawable drawable = getDrawable();
        if(null==drawable){
            super.onDraw(canvas);
        }else try {
            if (null == this.paint) {
                this.paint = new Paint();
                this.paint.setFilterBitmap(true);
                this.paint.setXfermode(MASK_XFERMODE);
            }
            int w = getWidth();//获取图片资源的宽度
            int h = getHeight();//获取图片资源的高度
            drawable.setBounds(0, 0, w, h);
            drawable.draw(canvas);
            if (null == this.mask || this.mask.isRecycled()) {
                this.mask = createCircleMap();
            }
            canvas.drawBitmap(this.mask, 0.0F, 0.0F, paint);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.graphics.PorterDuff;

/**
 * Created by Administrator on 2016/1/2 0002.
 */
//public class PorterDuffViewImage extends ImageView
public class ImageCircleView extends androidx.appcompat.widget.AppCompatImageView
{

    private Paint mpaint;
    private static Xfermode xfermode;
    private static Bitmap bitmap;
    private RectF rect;


    public ImageCircleView(Context context) {
        super(context);
        init();
    }

    public ImageCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Canvas canvas1 = null;
        BitmapDrawable drawable = (BitmapDrawable)getDrawable();
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        if (bitmap == null){
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            rect = new RectF(0,0,width,height);
            bitmap = Bitmap.createBitmap(width, height, config);
            canvas1 = new Canvas(bitmap);
            canvas1.drawOval(rect, paint);
        }
        mpaint.setXfermode(xfermode);
        canvas.drawBitmap(bitmap,0,0,mpaint);
    }

    private void init(){
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        mpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }



}