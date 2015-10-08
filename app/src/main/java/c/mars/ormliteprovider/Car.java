package c.mars.ormliteprovider;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Constantine Mars on 10/8/15.
 */
@DatabaseTable @Data @NoArgsConstructor @AllArgsConstructor
public class Car {
    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String model;
}
