package c.mars.ormliteprovider.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tojc.ormlite.android.annotation.AdditionalAnnotation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Constantine Mars on 10/8/15.
 */
@DatabaseTable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @DatabaseField(generatedId = true)
    @AdditionalAnnotation.DefaultSortOrder
    private long id;

    @DatabaseField
    private String model;
}
