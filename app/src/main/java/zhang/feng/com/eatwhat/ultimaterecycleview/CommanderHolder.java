package zhang.feng.com.eatwhat.ultimaterecycleview;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

import zhang.feng.com.eatwhat.R;
import zhang.feng.com.eatwhat.goods.Food;

public class CommanderHolder extends UltimateRecyclerviewViewHolder {
    public ImageButton serial_button;//食物的编号
    public TextView foodName;//食物名字
    public TextView per_content;//食物的含量
    public TextView energy;//食物所含能量
    private Food mFood;
    public CommanderHolder(View itemView) {
        super(itemView);
        serial_button = (ImageButton)itemView.findViewById(R.id.c_serial_number);
        foodName = (TextView) itemView.findViewById(R.id.c_food_name);
        per_content = (TextView)itemView.findViewById(R.id.c_per_content);
        energy = (TextView)itemView.findViewById(R.id.c_energy);



    }

//    public void bind(Food food){
//        mFood = food;
//
//    }
}
