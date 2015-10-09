package c.mars.ormliteprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Constantine Mars on 10/8/15.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@SuppressWarnings("DefaultFileTemplate")
public class DbHelper extends OrmLiteSqliteOpenHelper {
    public static final int VERSION=1;
    public static final String DB_NAME="cars";
    private static DbHelper instance;
    private Dao<Car, Long> carDao;

    //    don't use in client code
    public DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    //    don't use in client code
    public DbHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, DB_NAME, null, VERSION);
    }

    public static void init(Context context) {
        instance = OpenHelperManager.getHelper(context, DbHelper.class);
    }

    public static DbHelper getInstance() {
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Car.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Car.class, false);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetDb() throws SQLException {
        TableUtils.dropTable(getConnectionSource(), Car.class, false);
        TableUtils.createTable(getConnectionSource(), Car.class);
    }

    public Dao<Car, Long> getCarDao() {
        if(carDao==null){
            try {
                carDao = getDao(Car.class);
            } catch (SQLException e) {
                e.printStackTrace();
                carDao = null;
            }
        }
        return carDao;
    }
}
