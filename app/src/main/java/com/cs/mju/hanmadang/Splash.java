package com.cs.mju.hanmadang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by park on 2016-01-28.
 */
public class Splash extends Activity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

			/*
             *  원하는 화면을 띄워줌
			 */

            @Override
            public void run() {
                // 3초간 화면 보여주고 끝
                // 화면 끝나고 메인 실행
                Intent i = new Intent(Splash.this, MainActivity.class);
                Bundle extra = new Bundle();
                String act = "splash";
                extra.putString("who", act);
                i.putExtras(extra);

                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
