package zhang.feng.com.eatwhat.goods;

public class Issue {
    private int personid;//用户id
    private long serial_number;//序列号
    private String date;//发布时间
    private String content;//发表内容
    private String img_paths;//图片地址
    private String location;//定位
    private String mood;//心情
    private int view_number;//观看数

    public long getSerial_number() {
        return serial_number;
    }
    public void setSerial_number(long serial_number) {
        this.serial_number = serial_number;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getImg_paths() {
        return img_paths;
    }
    public void setImg_paths(String img_paths) {
        this.img_paths = img_paths;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getMood() {
        return mood;
    }
    public void setMood(String mood) {
        this.mood = mood;
    }


    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }

    public Issue(int personid, long serial_number, String date, String content, String img_paths, String location, String mood, int view_number) {
        this.personid = personid;
        this.serial_number = serial_number;
        this.date = date;
        this.content = content;
        this.img_paths = img_paths;
        this.location = location;
        this.mood = mood;
        this.view_number = view_number;
    }

    public Issue() {
        super();
        // TODO Auto-generated constructor stub
    }
    public int getView_number() {
        return view_number;
    }
    public void setView_number(int view_number) {
        this.view_number = view_number;
    }




}
