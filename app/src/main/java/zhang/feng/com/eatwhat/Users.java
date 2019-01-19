package zhang.feng.com.eatwhat;

import org.litepal.crud.LitePalSupport;

public class Users extends LitePalSupport{
    private String username;//用户名
    private String password;//密码
    private int age;//年龄
    private String sex;//性别

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
