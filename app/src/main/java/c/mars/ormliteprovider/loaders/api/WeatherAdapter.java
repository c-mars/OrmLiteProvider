package c.mars.ormliteprovider.loaders.api;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raizlabs.android.dbflow.list.FlowQueryList;

import butterknife.Bind;
import butterknife.ButterKnife;
import c.mars.ormliteprovider.R;
import c.mars.ormliteprovider.dbflow.WeatherDescTable;

/**
 * Created by Constantine Mars on 10/9/15.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private FlowQueryList<WeatherDescTable> queryList = new FlowQueryList<>(WeatherDescTable.class);

    public WeatherAdapter(Context context) {
        queryList.enableSelfRefreshes(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherDescTable weather = queryList.get(position);
        holder.textView.setText(weather.getDescription());
    }

    @Override
    public int getItemCount() {
        return queryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text)
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
