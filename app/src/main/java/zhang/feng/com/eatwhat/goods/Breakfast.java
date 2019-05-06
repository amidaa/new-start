package zhang.feng.com.eatwhat.goods;

public class Breakfast {
    private Integer id;
    private String aim_illness;
    private Integer type;
    private Integer weight;
    private String foodname;
    private String image_path;
    private float energy;

    public Breakfast(Integer id, String aim_illness, Integer type, Integer weight, String foodname, String image_path, float energy) {
        this.id = id;
        this.aim_illness = aim_illness;
        this.type = type;
        this.weight = weight;
        this.foodname = foodname;
        this.image_path = image_path;
        this.energy = energy;
    }

    public Breakfast() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAim_illness() {
        return aim_illness;
    }

    public void setAim_illness(String aim_illness) {
        this.aim_illness = aim_illness;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }
}
