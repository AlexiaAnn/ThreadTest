package alexiaann.com.threadtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by AlexiaAnn on 2016/7/31 0031.
 */
public class TestClass extends Activity {

    @Bind(R.id.showImage)
    ImageView showImage;
    @Bind(R.id.title)
    Button title;
    @Bind(R.id.startDownLoad)
    Button startDownLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carousel_advertising);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.startDownLoad)
    public void onClick() {
        Toast.makeText(TestClass.this,"测试",Toast.LENGTH_SHORT).show();
    }
}
