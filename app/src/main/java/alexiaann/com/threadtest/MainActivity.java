package alexiaann.com.threadtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private TextView showTV;
    private Button startBtn;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            showTV.setText("线程启动"+msg.arg1+"秒");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showTV = (TextView) findViewById(R.id.showTV);
        startBtn = (Button) findViewById(R.id.start);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,"点击事件",Toast.LENGTH_SHORT).show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        for (int i =1;i < 10;i++){

                            msg.arg1 = i;
                            handler.sendMessage(msg);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        msg.arg1 = 1111;
                        handler.sendMessage(msg);
                    }
                }).start();

            }
        });
    }
}
