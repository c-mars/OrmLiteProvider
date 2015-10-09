package c.mars.ormliteprovider;

import android.app.Application;

/**
 * Created by Constantine Mars on 10/9/15.
 */
@SuppressWarnings("DefaultFileTemplate")
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DbHelper.init(this);
    }
}
