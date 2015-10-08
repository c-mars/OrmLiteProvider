package c.mars.ormliteprovider;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.plant(new Timber.DebugTree());

        DbHelper helper= OpenHelperManager.getHelper(this, DbHelper.class);
        try {
            Dao<Car, Long> dao = helper.getDao();

            dao.create(new Car(1, "ford"));
            dao.create(new Car(2, "hummer"));
            dao.create(new Car(3, "jeep"));

            List<Car> cars = dao.queryForAll();
            Timber.d(cars.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
