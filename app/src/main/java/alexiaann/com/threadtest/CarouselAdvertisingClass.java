package alexiaann.com.threadtest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

/**
 * Created by AlexiaAnn on 2016/7/31 0031.
 */
public class CarouselAdvertisingClass extends Activity implements Runnable{

    private ImageView imageView;
    private Button button;
    private int[] path;
    private String[] titleStr;
    private Button button1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            imageView.setImageResource(path[msg.arg1]);
            button.setText(titleStr[msg.arg1]);
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carousel_advertising);

        imageView = (ImageView) findViewById(R.id.showImage);
        button = (Button) findViewById(R.id.title);
        button1 = (Button) findViewById(R.id.startDownLoad);

        titleStr = new String[]{"图片一","图片二","图片三"};
        path = new int[]{R.drawable.image1,R.drawable.image2,R.drawable.image3};

        Thread thread = new Thread(this);
        thread.start();

    }

    private Bitmap GetBitmap(String urlStr){

        Bitmap bitmap = null;
        try{

            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            InputStream in = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int index = 0;
        while(!Thread.currentThread().isInterrupted()){
            index = new Random().nextInt(path.length);
            Message message = handler.obtainMessage();
            message.arg1 = index;
            handler.sendMessage(message);
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
