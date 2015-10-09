package c.mars.ormliteprovider.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import c.mars.ormliteprovider.loaders.api.ApiHelper;
import c.mars.ormliteprovider.loaders.api.WeatherResponse;
import timber.log.Timber;

/**
 * Created by Constantine Mars on 10/9/15.
 */
public class WeatherLoader extends AsyncTaskLoader<WeatherResponse> {

    public WeatherLoader(Context context) {
        super(context);
    }

    @Override
    public WeatherResponse loadInBackground() {
        WeatherResponse response = ApiHelper.getForecast().toBlocking().first();
        Timber.d(response.getTemperature().toString());
        return response;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

}