package c.mars.ormliteprovider.loaders.api;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Constantine Mars on 10/9/15.
 */
@Data
@NoArgsConstructor
public class WeatherItem {
    Long id;

    Main main;
    Weather[] weather;

    @Data
    public static class Main {
        Long id;
        Double temp;
    }

    @Data
    public static class Weather {
        String main;
        String description;
    }
}
