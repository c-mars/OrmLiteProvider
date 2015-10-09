package c.mars.ormliteprovider.loaders.api;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by Constantine Mars on 10/9/15.
 */
public interface WeatherService {
    @GET("/forecast?q=London,us&mode=json&units=metric&appid=27fcf3f35284b65bdb94f3faa1fe2fb9")
    Observable<WeatherResponse> getForecast();
}
