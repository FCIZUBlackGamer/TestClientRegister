package com.example.testclientregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DialogAdapter extends ArrayAdapter<String> {

    ArrayList<String> list;

    public DialogAdapter(Context context, ArrayList<String> objects) {
        super(context, R.layout.list, objects);
        this.list = objects;
    }

    private class ViewHolder{
        Button icon;
        TextView name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list, parent, false);
            viewHolder.icon = (Button) convertView.findViewById(R.id.round_icon);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.icon.setText(list.get(position).substring(0, 1));
        viewHolder.name.setText(list.get(position));

        return convertView;
    }

}
