package c.mars.ormliteprovider.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.raizlabs.android.dbflow.sql.language.Select;

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
        WeatherResponse response = ApiHelper.getForecast().toBlocking().first();
        Timber.d(response.getTemperature().toString());
        return response.toTable();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        long count = new Select().from(WeatherTable.class).count();
        if (count > 0) {
            WeatherTable weatherTable = new Select().from(WeatherTable.class).querySingle();
            deliverResult(weatherTable);
            return;
        }

//        if table empty
        forceLoad();
    }

}