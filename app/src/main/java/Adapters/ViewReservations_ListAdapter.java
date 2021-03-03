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

import data.Reservations;



/////make sure you got all the reservation variable


public class ViewReservations_ListAdapter extends BaseAdapter {
    private Context context;
    private List<Reservations> reservationsList;
    customButtonListener customListner;

    public ViewReservations_ListAdapter(Context context, List<Reservations> reservationsList)
    {
        this.context = context;
        this.reservationsList = reservationsList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        final ReservationHolder holder = new ReservationHolder();

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(R.layout.reservations_row_for_list, parent, false);

        holder.reservation = reservationsList.get(position);
        holder.reservationIdTV = row.findViewById(R.id.reservationIdTV);
        holder.facilityNameTV = row.findViewById(R.id.facilityNameTV);
        holder.reserveDateTV = row.findViewById(R.id.reserveDateTV);
        holder.reserveTimeTV = row.findViewById(R.id.reserveTimeTV);
        holder.viewButton = row.findViewById(R.id.viewReservationBtn);

        row.setTag(holder);

        holder.reservationIdTV.setText(holder.reservation.getReservationID());
        holder.facilityNameTV.setText(holder.reservation.getFacilityName());
        holder.reserveDateTV.setText(holder.reservation.getReserveDate());
        holder.reserveTimeTV.setText(holder.reservation.getReserveTime());

        if (position % 2 == 0) {
            row.setBackgroundColor(Color.rgb(213, 229, 241));
        } else {
            row.setBackgroundColor(Color.rgb(255, 255, 255));
        }

        holder.viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customListner != null) {
                    customListner.onButtonClickListner(holder.reservation.getReservationID());
                }

            }
        });

        return row;
    }

    public interface customButtonListener {
        public void onButtonClickListner(String id);
    }

    public void setCustomButtonListner(customButtonListener listener) {
        this.customListner = listener;
    }

    public static class ReservationHolder {
        Reservations reservation;
        TextView reservationIdTV;
        TextView facilityNameTV;
        TextView reserveDateTV;
        TextView reserveTimeTV;
        Button viewButton;
    }

    @Override
    public int getCount() {
        return reservationsList.size();
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
