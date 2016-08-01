package alexiaann.com.threadtest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexiaAnn on 2016/7/31 0031.
 */
public class GetSDImages extends Activity {

    private GridView gridView;
    private List<String> paths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getsd_image);

        gridView = (GridView) findViewById(R.id.showImage);

        getImagePath(Environment.getExternalStorageDirectory()+"/");

        if(paths.size() < 1)
            return;
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return paths.size();
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                ImageView imageView =null;
                if(convertView == null){

                    imageView = new ImageView(GetSDImages.this);
                    imageView.setMaxWidth(150);
                    imageView.setMaxHeight(113);
                    imageView.setAdjustViewBounds(true);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    Bitmap bitmap = BitmapFactory.decodeFile(paths.get(position));
                    imageView.setImageBitmap(bitmap);

                }else{
                    imageView = (ImageView)convertView;
                }


                return imageView;
            }
        };

        gridView.setAdapter(baseAdapter);
    }


    //获取图片路径
    public void getImagePath(String filePathName){
        paths = new ArrayList<String>();

        File file = new File(filePathName);
        File[] files = file.listFiles();

        try{
            for (File fileName:files){
                if(fileName.isDirectory()){
                    getImagePath(fileName.getPath());
                }else{
                    if(isImage(fileName.getPath())){
                        paths.add(fileName.getPath());
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }


    }
    //判断该文件是否图片
    public boolean isImage(String imagePathName){
        String[] types = new String[]{"jpg","png","gif"};
        for (String type:types){
            if(imagePathName.contains(type)){
                return true;
            }
        }
        return false;
    }
}
