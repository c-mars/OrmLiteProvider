package c.mars.ormliteprovider.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import c.mars.ormliteprovider.dbflow.WeatherTable;
import c.mars.ormliteprovider.loaders.api.ApiHelper;
import c.mars.ormliteprovider.loaders.api.WeatherResponse;
import timber.log.Timber;

/**
 * Created by Constantine Mars on 10/9/15.
 */
public class WeatherLoader extends AsyncTaskLoader<WeatherTable> {

    public WeatherLoader(Context context) {
        super(context);
    }

    @Override
    public WeatherTable loadInBackground() {

//            long count = new Select().from(WeatherTable.class).count();
//        don't load if already cached
//            if (count > 0) {
//                return new Select().from(WeatherTable.class).querySingle();
//            }


//        force load
        WeatherResponse response = ApiHelper.getForecast().toBlocking().first();
        Timber.d(response.getTemperature().toString());
        response.saveWeatherList();
        return response.toTable();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
//        start background processing
        forceLoad();
    }
}