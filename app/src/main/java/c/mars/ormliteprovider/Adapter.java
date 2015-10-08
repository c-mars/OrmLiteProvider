package c.mars.ormliteprovider;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Constantine Mars on 10/8/15.
 */
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("DefaultFileTemplate")
@Data
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private String[] data = {"a", "b", "c", "d"};

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
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
