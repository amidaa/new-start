package zhang.feng.com.eatwhat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import zhang.feng.com.eatwhat.acache.ACache;
import zhang.feng.com.eatwhat.adapter.GridViewAddImageAdapter;
import zhang.feng.com.eatwhat.util.DataUtil;
import zhang.feng.com.eatwhat.volleyopr.DefaultErrorListener;
import zhang.feng.com.eatwhat.volleyopr.VolleyHttpApi;

public class PublishActivity extends AppCompatActivity {
    public static final int TAKE_PHOTO = 1;//拍照
    public static final int GALLERY_PHOTO = 2;//从相册中选择照片
    private EditText mCharacterEditText;//发布的文字内容
    private ImageButton mPhotoButton;//拍照按钮
    private File mPhotoFile;//照片文件
    private GridView gw;
    private ImageButton faceImage;
    private ImageButton noImage;
    private ImageButton smileImage;
    private ImageButton daiImage;
    private ImageButton failImage;
    private ImageButton successImage;
    private ImageButton funnyImage;
    private Integer hostId;//账号
    private String content;//输入的内容
    private String imagepath="";//图片名字

    private String mood = "";//心情
    private List<Map<String,Object>> datas;//图片地址的所有信息
    private GridViewAddImageAdapter mGridViewAddImageAdapter;
    private final String IMAGE_DIR = Environment.getExternalStorageDirectory()+"/";
    private final String PHOTO_FILE_NAME = "temp_photo.jpg";

    private VolleyHttpApi mVolleyHttpApi;//网络通信接口
    private String HOST="http://47.112.28.145:8090/ConditionApi";//接口地址


    private SharedPreferences mRememberPreferences=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);

        gw = (GridView)findViewById(R.id.my_photos);

        faceImage = (ImageButton)findViewById(R.id.the_face);
        noImage = (ImageButton)findViewById(R.id.the_nocart);
        smileImage = (ImageButton)findViewById(R.id.the_smile);
        daiImage = (ImageButton)findViewById(R.id.the_scary);
        failImage = (ImageButton)findViewById(R.id.the_fail);
        successImage = (ImageButton)findViewById(R.id.the_success);
        funnyImage = (ImageButton)findViewById(R.id.the_funny);

        faceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((ImageButton) v).setImageResource(R.drawable.face_activite);
                mood = "LONELY";

            }
        });

        noImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((ImageButton) v).setImageResource(R.drawable.nochaer_active);
                mood = "AMAZE";

            }
        });

        smileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((ImageButton) v).setImageResource(R.drawable.smile_activy);
                mood = "HAPPY";

            }
        });

        daiImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((ImageButton) v).setImageResource(R.drawable.dai_active);
                mood = "SLEEPY";

            }
        });

        failImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((ImageButton) v).setImageResource(R.drawable.fail_activity);
                mood = "SAD";

            }
        });

        successImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((ImageButton) v).setImageResource(R.drawable.success_active);
                mood = "ADMIRING";

            }
        });
        funnyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((ImageButton) v).setImageResource(R.drawable.funny_active);
                mood = "OVERWHELMED";

            }
        });


        datas = new ArrayList<>();
        mGridViewAddImageAdapter = new GridViewAddImageAdapter(datas,this);
        gw.setAdapter(mGridViewAddImageAdapter);

        mRememberPreferences =this.getSharedPreferences("USER",MODE_PRIVATE);//私有数据，只能被应用
        hostId = mRememberPreferences.getInt("hostid",10000);


        mVolleyHttpApi = VolleyHttpApi.getInstance();


        gw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,GALLERY_PHOTO);
            }
        });


        final androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar)findViewById(R.id.publish_toolbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        toolbar.inflateMenu(R.menu.pl_toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setTitle("动态");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.launch:
                        ACache mCache = ACache.get(PublishActivity.this);
                        content = mCharacterEditText.getText().toString();//填写内容




                        JSONObject information = new JSONObject();
                        try {
                            Random ran =new Random(System.currentTimeMillis());
                            DataUtil dataUtil = new DataUtil();
                            information.put("personid",hostId);
                            information.put("serial_number",ran.nextInt(100));
                            information.put("content",content);
                            information.put("img_paths",imagepath);
                            information.put("date",dataUtil.simpleDateYMD(new Date()));
                            information.put("location","");
                            information.put("mood",mood);
                            information.put("view_number",2);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        mCache.put(hostId+"condtion",information.toString(), 2 * ACache.TIME_DAY);//使用ASimpleCache缓存到本地
                        mVolleyHttpApi.putInformationController(HOST, PublishActivity.this, information, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if(response.optString("condition")!=null){
                                }else {
                                    Toast.makeText(PublishActivity.this,"网络开小差啦，请稍后重试", Toast.LENGTH_SHORT).show();
                                }



                            }
                        }, new DefaultErrorListener() {
                            @Override
                            protected void onErrorResponseFailed(String errorMesg, VolleyError volleyError) {
                                Toast.makeText(PublishActivity.this,errorMesg, Toast.LENGTH_SHORT).show();

                            }
                        });

                       JSONObject images = new JSONObject();
                        try {
                            images.put("images",datas);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        mCache.put("imagescache",images,2 * ACache.TIME_DAY);

                        //保存现场
                        finish();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        mCharacterEditText = (EditText)findViewById(R.id.thought);
        mPhotoButton = (ImageButton)findViewById(R.id.take_photo);
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File dir = new File(IMAGE_DIR);
                try {
                    if(!dir.exists()){
                        dir.mkdir();//创建一个文件夹
                    }
                    mPhotoFile = new File(dir,
                            System.currentTimeMillis() + "_" + PHOTO_FILE_NAME);
                    //从文件中创建uri
                }catch (Exception e){
                    e.printStackTrace();
                }
                Uri uri;

                if(Build.VERSION.SDK_INT>=24){
                    uri = FileProvider.getUriForFile(PublishActivity.this,"com.zhang.feng.eatwhat.fileprovider",mPhotoFile);

                }else{
                    uri = Uri.fromFile(mPhotoFile);
                }

                //启动相机程序

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                startActivityForResult(intent,TAKE_PHOTO);
            }
        });

    }

    //拍完照的结果会返回到onActivityResult中

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PHOTO:

                    if (mPhotoFile != null) {
                        uploadImage(mPhotoFile);
                        imagepath += mPhotoFile.getName()+"#";
                    } else {
                        Toast.makeText(this, "相机异常请稍后再试！", Toast.LENGTH_SHORT).show();

                    }

                    break;
                case GALLERY_PHOTO:
                    if (data != null) {
                        //得到图片的全路径
                        Uri uri = data.getData();
                        String[] proj = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
                        if(cursor!=null){
                            if(cursor.moveToFirst()){
                                //获得用户选择的图片的索引值
                                int column_index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                                //根据索引值获取图片路径
                                String path = cursor.getString(column_index);
                                File f = new File(path);
                                uploadImage(f);
                                imagepath += f.getName()+"#";
                            }
                            cursor.close();
                        }



                    }
                default:
                    break;
            }
        }
    }


    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            if(msg.what == 0xAAAAAAAA){
                photoPath(msg.obj.toString());
            }
        }
    };
    //上传图片

    private void uploadImage(final File mFile){

        new Thread(){
            @Override
            public void run(){
                if(mFile.exists()){
                    Log.d("images", "源文件存在" +mFile.getPath());
                }else{
                    Log.d("images", "源文件不存在" + mFile.getPath());
                }

                Luban.with(PublishActivity.this)
                        .load(mFile)//传入要压缩的图片列表
                        .ignoreBy(100)//忽略不压缩的图片大小
                        .setTargetDir(getExternalCacheDir().getAbsolutePath())//压缩后图片存储位置
                        .filter(new CompressionPredicate() {
                            @Override
                            public boolean apply(String path) {
                                return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                            }
                        })
                        .setCompressListener(new OnCompressListener() {
                            @Override
                            public void onStart() {
                                // TODO 压缩开始前调用，可以在方法内启动 loading UI
                            }

                            @Override
                            public void onSuccess(File file) {
                                Message message = new Message();
                                message.what = 0xAAAAAAAA;
                                message.obj = file.getAbsolutePath();
                                mHandler.sendMessage(message);
                            }

                            @Override
                            public void onError(Throwable e) {
                                // TODO 当压缩过程出现问题时调用

                                Log.d("hhh", "onError: somethingwrong");
                            }
                        }).launch();

            }
        }.start();

    }

    public void photoPath(String path){
        Map<String,Object> map = new HashMap<>();
        map.put("path",path);
        datas.add(map);
        mGridViewAddImageAdapter.notifyDataSetChanged();
    }


}
