package alexiaann.com.threadtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

/**
 * Created by AlexiaAnn on 2016/7/31 0031.
 */
public class AnimationClass extends Activity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_image);

        button = (Button) findViewById(R.id.startDownLoad);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button.startAnimation(AnimationUtils.loadAnimation(AnimationClass.this,R.anim.anim_alpha));

            }
        });
    }
}
