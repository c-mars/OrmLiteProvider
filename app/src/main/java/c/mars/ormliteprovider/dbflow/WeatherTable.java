package c.mars.ormliteprovider.dbflow;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import lombok.Data;
import timber.log.Timber;

/**
 * Created by Constantine Mars on 10/9/15.
 */
@Data
@Table(databaseName = Db.NAME)
public class WeatherTable extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    long id;
    @Column
    private String city;
    @Column
    private Double temp;
    @Column
    private String weather;

    public WeatherTable() {
        Timber.d("default constructor");
    }

    public WeatherTable(String city, double temp, String weather) {
        this.city = city;
        this.temp = temp;
        this.weather = weather;
    }
}
