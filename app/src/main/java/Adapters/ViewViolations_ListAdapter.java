package Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.MacRes.R;

import java.util.List;

import data.Violations;

public class ViewViolations_ListAdapter extends BaseAdapter {
    private Context context;
    private List<Violations> violationList;
    ViewViolations_ListAdapter.customButtonListener customListner;

    public ViewViolations_ListAdapter(Context context, List<Violations> violationList) {
        this.context = context;
        this.violationList = violationList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        final ViewViolations_ListAdapter.ViolationsHolder holder;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();

        row = inflater.inflate(R.layout.violations_row_for_list, parent, false);

        holder = new ViewViolations_ListAdapter.ViolationsHolder();
        holder.violation = violationList.get(position);
        holder.violationIdTV = row.findViewById(R.id.violationIdTV);
        holder.viewButton = row.findViewById(R.id.viewViolationBtn);

        row.setTag(holder);
        holder.violationIdTV.setText(holder.violation.getViolationID());

        if (position % 2 == 0) {
            row.setBackgroundColor(Color.rgb(213, 229, 241));
        } else {
            row.setBackgroundColor(Color.rgb(255, 255, 255));
        }

        holder.viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customListner != null) {
                    customListner.onButtonClickListner(holder.violation.getViolationID());
                }

            }
        });

        return row;
    }

    public interface customButtonListener {
        public void onButtonClickListner(String violationID);
    }

    public void setCustomButtonListner(ViewViolations_ListAdapter.customButtonListener listener) {
        this.customListner = listener;
    }

    public static class ViolationsHolder {
        Violations violation;
        TextView violationIdTV;
        Button viewButton;
    }

    @Override
    public int getCount() {
        return violationList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}

