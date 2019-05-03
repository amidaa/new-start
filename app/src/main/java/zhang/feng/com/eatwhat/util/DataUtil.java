package zhang.feng.com.eatwhat.util;



import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {

    public String simpleDateYMD(Date date){

        String mydate="2019-3-4";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            mydate = formatter.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }

        return mydate;
    }

    public String simpleDateHM(Date date){
        String mydate="00:00";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            mydate = formatter.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }

        return mydate;
    }
}
