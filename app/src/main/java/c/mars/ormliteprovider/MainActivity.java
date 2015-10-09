package c.mars.ormliteprovider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
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
