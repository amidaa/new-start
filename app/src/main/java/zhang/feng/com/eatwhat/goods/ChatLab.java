package zhang.feng.com.eatwhat.goods;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zhang.feng.com.eatwhat.R;
import zhang.feng.com.eatwhat.util.DataUtil;

public class ChatLab {
    private static ChatLab sChatLab;
    private List<Issue> mIssues;
    private List<String> datas;//图片信息
    private Context mContext;

    public static ChatLab get(Context context){
        if(sChatLab==null){
            sChatLab = new ChatLab(context);
        }
        return sChatLab;
    }
    private ChatLab(Context context){
        mIssues = new ArrayList<>();
//        datas = new ArrayList(Environment.getExternalStorageDirectory()+"/DCIM/",Environment.getExternalStorageDirectory()+"/DCIM/");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        DataUtil util = new DataUtil();
        String s_data = util.simpleDateHM(date);
        for (int number = 0;number<100;number++){
            Issue issue = new Issue();
            issue.setmAuthor("huahua"+number);
            issue.setmContent("今天心情很好");
            issue.setmData(s_data);
//            issue.setPhotoPath(datas);

            mIssues.add(issue);
        }
    }
    public List<Issue> getmIssues(){
        return mIssues;
    }


//    返回指向某个具体位置的File对象
    public File getPhotoFile(Issue issue){
        File fileDir = mContext.getFilesDir();
        return new File(fileDir,issue.getPhotoFilename());
    }
}
