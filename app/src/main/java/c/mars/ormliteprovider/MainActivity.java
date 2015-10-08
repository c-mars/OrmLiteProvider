package c.mars.ormliteprovider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
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
    RecyclerView.Adapter adapter;

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
