package zhang.feng.com.eatwhat.adapter;

import android.view.View;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

public class StringViewHolder extends UltimateRecyclerviewViewHolder {
    public TextView tv;
    public StringViewHolder(View itemView, boolean isItem) {
        super(itemView);
        if(isItem){
            tv = (TextView) itemView;
        }
    }
}
