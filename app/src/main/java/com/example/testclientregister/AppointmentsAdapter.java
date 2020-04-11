package com.example.testclientregister;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.List;

public class AppointmentsAdapter extends BaseAdapter {
    private Context mContext;
    private List<AppointMntModel> appointMntModels;

    // Constructor
    public AppointmentsAdapter(Context c, List<AppointMntModel> appointMntModels) {
        this.mContext = c;
        this.appointMntModels = appointMntModels;
    }

    public int getCount() {
        return appointMntModels.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    @RequiresApi(api = Build.VERSION_CODES.M)
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            view = inflater.inflate(R.layout.date_item, null);
            TextView textView = view.findViewById(R.id.textView);
            if (appointMntModels.get(position).getState() == 0) {// that appointment is not available anymore
                textView.setBackground(mContext.getResources().getDrawable(R.drawable.date_not_available_style));
                textView.setTextColor(mContext.getColor(R.color.colorPrimary));
            } else {// that appointment is available now
                textView.setBackground(mContext.getResources().getDrawable(R.drawable.date_available_style));
                textView.setTextColor(mContext.getColor(R.color.white));
            }
            textView.setText(appointMntModels.get(position).getStartAppointMntDate());
        } else {
            view = convertView;
        }

        return view;
    }
}
