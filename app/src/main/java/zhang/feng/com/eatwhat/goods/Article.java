package zhang.feng.com.eatwhat.goods;

public class Article {
    private Integer articleId;//文章的id
    private String authorUser;//作者
    private Integer authorId;//作者的ID
    private String title;//文章标题
    private String content;//文章的内容
    private String createTime;//文章发布时间


    public Article() {
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getAuthorUser() {
        return authorUser;
    }

    public void setAuthorUser(String authorUser) {
        this.authorUser = authorUser;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Article(Integer articleId, String authorUser, Integer authorId, String title, String content, String createTime) {
        this.articleId = articleId;
        this.authorUser = authorUser;
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }
}
