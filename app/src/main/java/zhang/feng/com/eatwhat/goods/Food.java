package zhang.feng.com.eatwhat.goods;

public class Food {
    private int id;//食物编号
    private String foodname;//食物名字
    private String type;//食物名字
    private float weight;//食物重量
    private float energy;//食物所含能量
    private String use;//食物功效
    private String appropriate;//食物的适宜人群
    private String describe;//描述
    private String content;//食物包含的内容
    private String img_path;//食物图片保存位置
    private double carbohydrate;//碳水化合物
    private double fat;//脂肪
    private double protein;//蛋白质
    private double fibrin;//纤维素
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFoodname() {
        return foodname;
    }
    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public float getEnergy() {
        return energy;
    }
    public void setEnergy(float energy) {
        this.energy = energy;
    }
    public String getUse() {
        return use;
    }
    public void setUse(String use) {
        this.use = use;
    }
    public String getAppropriate() {
        return appropriate;
    }
    public void setAppropriate(String appropriate) {
        this.appropriate = appropriate;
    }
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getImg_path() {
        return img_path;
    }
    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
    public Food() {
        super();
        // TODO Auto-generated constructor stub
    }
    public double getCarbohydrate() {
        return carbohydrate;
    }
    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }
    public double getFat() {
        return fat;
    }
    public void setFat(double fat) {
        this.fat = fat;
    }
    public double getProtein() {
        return protein;
    }
    public void setProtein(double protein) {
        this.protein = protein;
    }
    public double getFibrin() {
        return fibrin;
    }
    public void setFibrin(double fibrin) {
        this.fibrin = fibrin;
    }
    public Food(int id, String foodname, String type, float weight, float energy, String use, String appropriate,
                String describe, String content, String img_path, double carbohydrate, double fat, double protein,
                double fibrin) {
        super();
        this.id = id;
        this.foodname = foodname;
        this.type = type;
        this.weight = weight;
        this.energy = energy;
        this.use = use;
        this.appropriate = appropriate;
        this.describe = describe;
        this.content = content;
        this.img_path = img_path;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.protein = protein;
        this.fibrin = fibrin;
    }




}
