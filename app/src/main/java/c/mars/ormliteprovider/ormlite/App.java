package c.mars.ormliteprovider.ormlite;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Constantine Mars on 10/9/15.
 */
@SuppressWarnings("DefaultFileTemplate")
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DbHelper.init(this);
        FlowManager.init(this);
    }

    @Override
    public void onTerminate() {
        FlowManager.destroy();
        super.onTerminate();
    }
}
