package mr.booroondook.custom_arrayadapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private String[] lastNames;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lastNames = getResources().getStringArray(R.array.last_names);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(new CustomAdapter());
    }

    private class CustomAdapter extends ArrayAdapter<String> {
        CustomAdapter() {
            super(MainActivity.this, R.layout.list_cell, R.id.unit_name, lastNames);
        }

        @NonNull
        @Override
        public View getView(int position,
                            @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            ViewHolder viewHolder = (ViewHolder) view.getTag();

            if (viewHolder == null) {
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            }

            if (Objects.requireNonNull(((CustomAdapter) listView.getAdapter())
                    .getItem(position)).length() % 2 == 0) {
                viewHolder.getIconStatus().setImageResource(R.drawable.ic_check_24dp);
                viewHolder.getUnitStatus().setText(R.string.adopted);
            } else {
                viewHolder.getIconStatus().setImageResource(R.drawable.ic_close_24dp);
                viewHolder.getUnitStatus().setText(R.string.fired);
            }
            return view;
        }
    }

    private static class ViewHolder {
        private final ImageView iconStatus;
        private final TextView unitStatus;

        ViewHolder(View view) {
            this.iconStatus = view.findViewById(R.id.icon_status);
            this.unitStatus = view.findViewById(R.id.unit_status);
        }

        ImageView getIconStatus() {
            return iconStatus;
        }

        TextView getUnitStatus() {
            return unitStatus;
        }
    }
}
