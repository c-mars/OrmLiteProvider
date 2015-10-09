package c.mars.ormliteprovider.dbflow;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import lombok.Data;

/**
 * Created by Constantine Mars on 10/9/15.
 */
@Data
@Table(databaseName = Db.NAME)
public class Queen extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    long id;
    @Column
    String name;
    @Column
    @ForeignKey(references = @ForeignKeyReference(columnName = "colony_id", columnType = Long.class, foreignColumnName = "id"),
            saveForeignKeyModel = true)
    Colony colony;
}
