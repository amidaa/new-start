package zhang.feng.com.eatwhat.banner;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

import zhang.feng.com.eatwhat.R;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide加载图片简单用法
        Glide.with(context).load(path).into(imageView);
        //设置等待图片
        Glide.with(context).load(path).placeholder(R.mipmap.ic_launcher).into(imageView);
        //加载图片失败后设置加载图片后的显示
        Glide.with(context).load(path).error(R.mipmap.ic_launcher).into(imageView);
    }
}
