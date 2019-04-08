package zhang.feng.com.eatwhat.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zhang.feng.com.eatwhat.R;
import zhang.feng.com.eatwhat.goods.Food;

public class FoodHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageButton mSerialNumber;
    private TextView mFoodName;
    private TextView mPerContent;
    private TextView mEnergy;
    private Food mFood;

    public FoodHolder(@NonNull View itemView) {
        super(itemView);
    }
    public FoodHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_foodlist, parent, false));
        mSerialNumber = (ImageButton) itemView.findViewById(R.id.serial_number);
        mFoodName = (TextView) itemView.findViewById(R.id.food_name);
        mPerContent = (TextView)itemView.findViewById(R.id.per_content);
        mEnergy = (TextView)itemView.findViewById(R.id.energy);

        itemView.setOnClickListener(this);
    }

    public void bind(Food food){
        mFood = food;


    }

    @Override
    public void onClick(View v) {


    }


}
