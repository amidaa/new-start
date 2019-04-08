package zhang.feng.com.eatwhat.goods;

import java.util.UUID;

public class Food {
    private UUID fondNumber;//食物的编号
    private String foodName;//食物的名字
    private String fondContent;//食物的卡路里含量
    private String fondWeight;//食物的重量
    private String fondIntroduce;//食物的营养价值的相关介绍

    public Food(){
        this.fondNumber = UUID.randomUUID();
    }
    public Food(String foodName, String fondContent, String fondWeight, String fondIntroduce) {
        this.fondNumber = UUID.randomUUID();
        this.foodName = foodName;
        this.fondContent = fondContent;
        this.fondWeight = fondWeight;
        this.fondIntroduce = fondIntroduce;
    }

    public UUID getFondNumber() {
        return fondNumber;
    }

    public void setFondNumber(UUID fondNumber) {
        this.fondNumber = fondNumber;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFondContent() {
        return fondContent;
    }

    public void setFondContent(String fondContent) {
        this.fondContent = fondContent;
    }

    public String getFondWeight() {
        return fondWeight;
    }

    public void setFondWeight(String fondWeight) {
        this.fondWeight = fondWeight;
    }

    public String getFondIntroduce() {
        return fondIntroduce;
    }

    public void setFondIntroduce(String fondIntroduce) {
        this.fondIntroduce = fondIntroduce;
    }
}
