package c.mars.ormliteprovider.loaders.api;

import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by Constantine Mars on 10/9/15.
 */
public class ApiHelper {

    public static Observable<WeatherResponse> getForecast() {
        RestAdapter retrofit = new RestAdapter.Builder()
                .setEndpoint("http://api.openweathermap.org/data/2.5")
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        return service.getForecast();
    }
}
