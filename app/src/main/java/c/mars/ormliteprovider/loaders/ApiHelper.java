package c.mars.ormliteprovider.loaders;

import retrofit.RestAdapter;

/**
 * Created by Constantine Mars on 10/9/15.
 */
public class ApiHelper {
    public static rx.Observable<WeatherResponse> getForecast() {
        RestAdapter retrofit = new RestAdapter.Builder()
                .setEndpoint("http://api.openweathermap.org/data/2.5")
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        rx.Observable<WeatherResponse> response = service.getForecast();
        return response;
    }
}
