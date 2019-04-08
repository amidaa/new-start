package zhang.feng.com.eatwhat.adapter;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

import java.io.File;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.feng.com.eatwhat.R;

public class GridViewAddImageAdapter extends BaseAdapter {
    private List<Map<String,Object>> datas;//图片信息
    private Context mContext;//上下文
    private LayoutInflater mLayoutInflater;

    private int maxImages = 4;//最大可上传的图片数

    public GridViewAddImageAdapter(){
    }

    public GridViewAddImageAdapter(List<Map<String,Object>>datas,Context context){
        this.datas = datas;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public int getMaxImages(){
        return maxImages;
    }

    public void setMaxImages(int maxImages){
        this.maxImages = maxImages;
    }



//    让GridView中的数据数目加一最后一个显示加号，当达到最大张数时不再显示+


    @Override
    public int getCount() {
        int count;
        if(datas==null){
            count=1;
        }else{
            count = datas.size()+1;
        }
        if(count>=maxImages){
            return count = datas.size();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder viewHolder = null;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.item_published,null);
            viewHolder = new MyViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (MyViewHolder)convertView.getTag();
        }


        if(datas!=null&&position<datas.size()){
            final File file = new File(datas.get(position).get("path").toString());
            Log.d("xxxxxxxxxx","getView: "+datas.get(position).get("path").toString());
            Glide.with(mContext).load(file).priority(Priority.HIGH).into(((MyViewHolder) viewHolder).ivImage);
            ((MyViewHolder) viewHolder).ivImage.setScaleType(ImageView.ScaleType.FIT_XY);
            ((MyViewHolder) viewHolder).ivImage.setVisibility(View.VISIBLE);
        }else{
            /**代表+号的需要+号图片显示图片**/
            Glide.with(mContext)
                    .load(R.mipmap.picture)
                    .priority(Priority.HIGH)
                    .centerCrop()
                    .into(viewHolder.ivImage);
            viewHolder.ivImage.setScaleType(ImageView.ScaleType.FIT_XY);
            viewHolder.btdel.setVisibility(View.GONE);
        }
        return convertView;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public  ImageView ivImage;
        public TextView btdel;//删除
        public View itemview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
            btdel = (TextView) itemView.findViewById(R.id.bt_del);
            this.itemview = itemView;
        }
    }


    public void notifyDataChanged(List<Map<String,Object>>datas){
        this.datas = datas;
        this.notifyDataSetChanged();
    }
}
