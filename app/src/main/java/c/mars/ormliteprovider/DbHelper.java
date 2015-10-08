package c.mars.ormliteprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Constantine Mars on 10/8/15.
 */
public class DbHelper extends OrmLiteSqliteOpenHelper {
    public static final int VERSION=1;
    public static final String DB_NAME="cars";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public DbHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, DB_NAME, null, VERSION);
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

    private Dao<Car, Long> carDao;
    public Dao<Car, Long> getDao() throws SQLException {
        if(carDao==null){
            carDao=getDao(Car.class);
        }
        return carDao;
    }
}
