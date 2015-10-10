package c.mars.ormliteprovider.dbflow;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Constantine Mars on 10/10/15.
 */
@Data
@NoArgsConstructor
@Table(databaseName = Db.NAME)
public class WeatherDescTable extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    long id;
    @Column
    String main;
    @Column
    String description;

    public WeatherDescTable(String main, String description) {
        this.main = main;
        this.description = description;
    }
}
