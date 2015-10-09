package c.mars.ormliteprovider.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.j256.ormlite.dao.Dao;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.sql.SQLException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import c.mars.ormliteprovider.R;
import c.mars.ormliteprovider.dbflow.Queen;
import c.mars.ormliteprovider.loaders.ApiHelper;
import c.mars.ormliteprovider.ormlite.Car;
import c.mars.ormliteprovider.ormlite.DbHelper;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.plant(new Timber.DebugTree());
        ButterKnife.bind(this);

        layoutManager = new LinearLayoutManager(this);
        adapter = new Adapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        dbFlow();

        ApiHelper.getForecast().subscribe(weatherResponse -> {
            Timber.d(weatherResponse.getTemperature().toString());
        });

//        ormLite();
    }

    void dbFlow() {
        Queen queen = new Queen();
        queen.update();

        long count = new Select().from(Queen.class).count();
        if (count > 0) {
            Timber.d(queen.getName());
        } else {
            queen.setName("Ann");
        }
        queen.save();
    }

    void ormLite() {
        Dao<Car, Long> dao = DbHelper.getInstance().getCarDao();

        try {
            DbHelper.getInstance().resetDb();

            dao.createOrUpdate(new Car(1, "ford"));
            dao.createOrUpdate(new Car(2, "hummer"));
            dao.createOrUpdate(new Car(3, "jeep"));

            List<Car> cars = dao.queryForAll();
            List<String> data = rx.Observable.from(cars).map(Car::getModel).toList().toBlocking().first();
            adapter.setData(data.toArray(new String[data.size()]));
            Timber.d(cars.toString());


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
