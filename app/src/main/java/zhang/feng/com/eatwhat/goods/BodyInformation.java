package zhang.feng.com.eatwhat.goods;

public class BodyInformation {
    private Integer userid;//用户编号
    private float height;//身高
    private float weight;//体重
    private Integer sex;//性别
    private float bloodpressure_h;//高压值
    private float bloodpressure_l;//低压值
    private float bloodglucose;//血糖
    private float bloodglucose_e;//空腹血糖
    private Integer walkstep;//步数
    private String illness;//疾病
    private String illnesstime;//生病时间
    private String exercise;//运动时间
    public BodyInformation(){
        super();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public float getBloodpressure_h() {
        return bloodpressure_h;
    }

    public void setBloodpressure_h(float bloodpressure_h) {
        this.bloodpressure_h = bloodpressure_h;
    }

    public float getBloodpressure_l() {
        return bloodpressure_l;
    }

    public void setBloodpressure_l(float bloodpressure_l) {
        this.bloodpressure_l = bloodpressure_l;
    }

    public float getBloodglucose() {
        return bloodglucose;
    }

    public void setBloodglucose(float bloodglucose) {
        this.bloodglucose = bloodglucose;
    }

    public float getBloodglucose_e() {
        return bloodglucose_e;
    }

    public void setBloodglucose_e(float bloodglucose_e) {
        this.bloodglucose_e = bloodglucose_e;
    }

    public Integer getWalkstep() {
        return walkstep;
    }

    public void setWalkstep(Integer walkstep) {
        this.walkstep = walkstep;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getIllnesstime() {
        return illnesstime;
    }

    public void setIllnesstime(String illnesstime) {
        this.illnesstime = illnesstime;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public BodyInformation(Integer userid, float height, float weight, Integer sex, float bloodpressure_h, float bloodpressure_l, float bloodglucose, float bloodglucose_e, Integer walkstep, String illness, String illnesstime, String exercise) {
        this.userid = userid;
        this.height = height;
        this.weight = weight;
        this.sex = sex;
        this.bloodpressure_h = bloodpressure_h;
        this.bloodpressure_l = bloodpressure_l;
        this.bloodglucose = bloodglucose;
        this.bloodglucose_e = bloodglucose_e;
        this.walkstep = walkstep;
        this.illness = illness;
        this.illnesstime = illnesstime;
        this.exercise = exercise;
    }
}
