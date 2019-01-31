package feng.zhang.com;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatLab {
    private static ChatLab sChatLab;
    private List<Issue> mIssues;

    public static ChatLab get(Context context){
        if(sChatLab==null){
            sChatLab = new ChatLab(context);
        }
        return sChatLab;
    }
    private ChatLab(Context context){
        mIssues = new ArrayList<>();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        for (int number = 0;number<10;number++){
            Issue issue = new Issue();
            issue.setmAuthor("huahua"+number);
            issue.setmContent("今天心情很好");
            issue.setmData(date);
            mIssues.add(issue);
        }
    }
    public List<Issue> getmIssues(){
        return mIssues;
    }
}
