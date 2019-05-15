package zzw.imtest;

import android.app.Application;
import cn.jpush.im.android.api.JMessageClient;

public class App extends Application {

    public final static String APP_KEY="be139d53989762b510f79201";

    @Override
    public void onCreate() {
        super.onCreate();
        JMessageClient.setDebugMode(true);
        JMessageClient.init(this);
    }
}
