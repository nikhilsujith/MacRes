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

import data.Users;

public class ViewUsers_ListAdapter extends BaseAdapter {
    private Context context;
    private List<Users> userList;
    customButtonListener customListner;

    public ViewUsers_ListAdapter(Context context, List<Users> userList) {
        this.context = context;
        this.userList = userList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        final UsersHolder holder;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//        header = getLayoutInflater().inflate(R.layout.header_view_users, userList, false);
//        userList.addHeaderView(header);
        row = inflater.inflate(R.layout.users_row_for_list, parent, false);

        holder = new UsersHolder();
        holder.user = userList.get(position);
        holder.usernameTV = row.findViewById(R.id.usernameLabelTV);
        holder.firstNameTV = row.findViewById(R.id.firstNameTV);
        holder.lastNameTV = row.findViewById(R.id.lastNameTV);
        holder.roleTV = row.findViewById(R.id.roleTV);
        holder.viewButton = row.findViewById(R.id.viewUsersBtn);

        row.setTag(holder);
        holder.usernameTV.setText(holder.user.getUsername());
        holder.firstNameTV.setText(holder.user.getFirstName());
        holder.lastNameTV.setText(holder.user.getLastName());
        holder.roleTV.setText(holder.user.getRole());

        if (position % 2 == 0) {
            row.setBackgroundColor(Color.rgb(213, 229, 241));
        } else {
            row.setBackgroundColor(Color.rgb(255, 255, 255));
        }

        holder.viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customListner != null) {
                    customListner.onButtonClickListner(holder.user.getUsername());
                }

            }
        });

        return row;
    }

    public interface customButtonListener {
        public void onButtonClickListner(String username);
    }

    public void setCustomButtonListner(customButtonListener listener) {
        this.customListner = listener;
    }

    public static class UsersHolder {
        Users user;
        TextView usernameTV;
        TextView firstNameTV;
        TextView lastNameTV;
        TextView roleTV;
        Button viewButton;
    }

    @Override
    public int getCount() {
        return userList.size();
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
