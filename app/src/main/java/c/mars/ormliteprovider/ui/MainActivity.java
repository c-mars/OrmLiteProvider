package c.mars.ormliteprovider.ui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.j256.ormlite.dao.Dao;
import com.raizlabs.android.dbflow.runtime.FlowContentObserver;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.Model;

import java.sql.SQLException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import c.mars.ormliteprovider.R;
import c.mars.ormliteprovider.dbflow.Queen;
import c.mars.ormliteprovider.dbflow.WeatherTable;
import c.mars.ormliteprovider.loaders.WeatherLoader;
import c.mars.ormliteprovider.loaders.api.ApiHelper;
import c.mars.ormliteprovider.loaders.api.WeatherAdapter;
import c.mars.ormliteprovider.ormlite.Car;
import c.mars.ormliteprovider.ormlite.DbHelper;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<WeatherTable>, FlowContentObserver.OnModelStateChangedListener {

    public static final int LOADER_ID = 1;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    WeatherAdapter adapter;
    private FlowContentObserver observer = new FlowContentObserver();

    @OnClick(R.id.fab)
    void click() {
        ApiHelper.getForecast().subscribe(weatherResponse -> {
            WeatherTable weatherTable = weatherResponse.toTable();
            if (weatherTable != null) {
                weatherTable.insert();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.plant(new Timber.DebugTree());
        ButterKnife.bind(this);

        layoutManager = new LinearLayoutManager(this);
        adapter = new WeatherAdapter(this);// Adapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);

//        dbFlow();

//        ApiHelper.getForecast().subscribe(weatherResponse -> {
//            Timber.d(weatherResponse.getTemperature().toString());
//        });

//        ormLite();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        observer.registerForContentChanges(this, WeatherTable.class);
        observer.addModelChangeListener(this);
    }

    @Override
    protected void onPause() {
        observer.removeModelChangeListener(this);
        observer.unregisterForContentChanges(this);
        super.onPause();
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
//            adapter.setData(data.toArray(new String[data.size()]));
            Timber.d(cars.toString());


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Loader<WeatherTable> onCreateLoader(int id, Bundle args) {
        return new WeatherLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<WeatherTable> loader, WeatherTable data) {
        int id = loader.getId();

        observer.removeModelChangeListener(this);

        if (data != null) {
            Timber.d(data.getTemp().toString());
            data.save(); //insert()
        }
        getLoaderManager().destroyLoader(id);
        adapter.notifyDataSetChanged();
        observer.addModelChangeListener(this);
    }

    @Override
    public void onLoaderReset(Loader<WeatherTable> loader) {

    }

    @Override
    public void onModelStateChanged(Class<? extends Model> table, BaseModel.Action action) {
        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
    }
}
