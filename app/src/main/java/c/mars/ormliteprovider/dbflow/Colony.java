package c.mars.ormliteprovider.dbflow;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Constantine Mars on 10/9/15.
 */
@Table(databaseName = Db.NAME)
public class Colony extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    long id;
    @Column
    String name;
}
