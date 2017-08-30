package com.bwie.lifangyin1507c20170830;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 木子 on 2017/8/30.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
