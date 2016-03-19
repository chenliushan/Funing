package polyu.comp.funing.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import polyu.comp.funing.R;

/**
 * Created by liushanchen on 16/3/18.
 */
public class WelcomeA extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        Intent intent = new Intent();
        intent.setClass(WelcomeA.this, MainActivity.class);
        startActivity(intent);
    }
}
