package zhang.feng.com.eatwhat;

import org.litepal.crud.LitePalSupport;

public class Users extends LitePalSupport{
    private Integer id;//用户账号
    private String username;//用户名
    private String nickname;//昵称
    private String password;//密码
    private String group_name;//组别

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Users(Integer id, String username, String nickname, String password, String group_name) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.group_name = group_name;
    }

    public Users() {
    }
}
