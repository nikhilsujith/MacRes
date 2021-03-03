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

import data.Facilities;

public class ViewFacilities_ListAdapter extends BaseAdapter {
    private Context context;
    private List<Facilities> facilityList;
    customButtonListener customListner;

    public ViewFacilities_ListAdapter(Context context, List<Facilities> facilityList) {
        this.context = context;
        this.facilityList = facilityList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        final FacilitiesHolder holder;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();

        row = inflater.inflate(R.layout.facilities_row_for_list, parent, false);

        holder = new FacilitiesHolder();
        holder.facility = facilityList.get(position);
        holder.facilityNameTV = row.findViewById(R.id.facilityNameTV);
        holder.viewButton = row.findViewById(R.id.viewFacilityBtn);

        row.setTag(holder);
        holder.facilityNameTV.setText(holder.facility.getFacilityName());

        if (position % 2 == 0) {
            row.setBackgroundColor(Color.rgb(213, 229, 241));
        } else {
            row.setBackgroundColor(Color.rgb(255, 255, 255));
        }

        holder.viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customListner != null) {
                    customListner.onButtonClickListner(holder.facility.getFacilityName());
                }

            }
        });

        return row;
    }

    public interface customButtonListener {
        public void onButtonClickListner(String facilityName);
    }

    public void setCustomButtonListner(customButtonListener listener) {
        this.customListner = listener;
    }

    public static class FacilitiesHolder {
        Facilities facility;
        TextView facilityNameTV;
        Button viewButton;
    }

    @Override
    public int getCount() {
        return facilityList.size();
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
