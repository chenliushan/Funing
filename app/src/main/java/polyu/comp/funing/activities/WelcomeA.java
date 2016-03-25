package polyu.comp.funing.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import polyu.comp.funing.R;
import polyu.comp.funing.constant.CommonConstant;
import polyu.comp.funing.utils.CommonUtils;

/**
 * Created by liushanchen on 16/3/18.
 */
public class WelcomeA extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        CommonConstant.applicationDir=getApplicationContext().getCacheDir().getPath();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                intent.setClass(WelcomeA.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
        
    }
}
