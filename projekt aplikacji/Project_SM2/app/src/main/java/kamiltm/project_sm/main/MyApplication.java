package kamiltm.project_sm.main;

import android.app.Application;
import android.content.Context;

/**
 * Created by Kamil Lenartowicz on 2018-01-02.
 */

public class MyApplication extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}