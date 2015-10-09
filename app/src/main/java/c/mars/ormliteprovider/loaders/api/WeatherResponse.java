package c.mars.ormliteprovider.loaders.api;

import c.mars.ormliteprovider.dbflow.WeatherTable;
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

    public void save() {
        WeatherTable weatherTable = toTable();
        weatherTable.insert();
    }

    public WeatherTable toTable() {
        if (list.length > 0) {
            return new WeatherTable(city.getName(), list[0].getMain().getTemp(), list[0].getWeather()[0].getDescription());
        }
        return null;
    }
}
