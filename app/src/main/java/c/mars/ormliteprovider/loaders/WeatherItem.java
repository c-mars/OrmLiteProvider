package c.mars.ormliteprovider.loaders;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Constantine Mars on 10/9/15.
 */
@Data
@NoArgsConstructor
public class WeatherItem {
    Main main;
    Weather[] weather;

    @Data
    public static class Main {
        Double temp;
    }

    @Data
    private static class Weather {
        String main;
        String description;
    }
}
