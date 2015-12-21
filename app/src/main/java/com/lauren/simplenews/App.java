package com.lauren.simplenews;

import android.app.Application;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/19
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Fresco.initialize(getApplicationContext());
    }
}
