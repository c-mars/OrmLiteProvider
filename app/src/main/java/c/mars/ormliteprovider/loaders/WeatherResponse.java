package c.mars.ormliteprovider.loaders;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Constantine Mars on 10/9/15.
 */
@Data
@NoArgsConstructor
public class WeatherResponse {
    City city;
    WeatherItem[] list;

    public Double getTemperature() {
        if (list.length > 0) {
            return list[0].getMain().getTemp();
        }

        return 0d;
    }
}
