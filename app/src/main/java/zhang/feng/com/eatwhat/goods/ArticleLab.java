package zhang.feng.com.eatwhat.goods;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ArticleLab {
    private static ArticleLab sArticleLab;
    private List<Article> mArticles;
//    public static ArticleLab get(Context context){
//        if(sArticleLab ==null){
//            sArticleLab = new ArticleLab(context);
//        }
//        return sArticleLab;
//    }
//    private ArticleLab (Context context){
//            mArticles = new ArrayList<>();
//        Date date = new Date(System.currentTimeMillis());
//            for(int number = 0;number<=10;number++){
//                Article article = new Article();
//                article.setmArticleName("冬季健康养生小妙招"+number);
//                article.setmArticleAuthor("作者"+number);
//                article.setmBriefIntroduction("冬季是传染病多发的季节"+number);
//                article.setmDate(date);
//                mArticles.add(article);
//            }
//    }
    public List<Article> getmArticles(){return mArticles;}//获取数组

//    public Article getArticle(UUID id){
//        for(Article article:mArticles){
//            if(article.getmID().equals(id)){
//                return article;
//            }
//        }
//        return null;
//    }
}
