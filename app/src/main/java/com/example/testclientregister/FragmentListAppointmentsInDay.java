package com.example.testclientregister;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FragmentListAppointmentsInDay extends Fragment {

    private static final String DEBUG_TAG = "TOUCH EVENT";
    GridView gridView;
    AppointmentsAdapter appointmentsAdapter;
    TextView tvDate;
    static String date;

    static FragmentListAppointmentsInDay setDate(String newDate) {
        date = newDate;
        return new FragmentListAppointmentsInDay();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_days, container, false);
        gridView = view.findViewById(R.id.gridview);
        tvDate = view.findViewById(R.id.tv_date);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), date, Toast.LENGTH_SHORT).show();
        int actionBarHeight = 0;
        // Calculate ActionBar height
        TypedValue tv = new TypedValue();
        if (getActivity().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
            Log.e("actionBarHeight", actionBarHeight + "");
        }
        // Calculate Window height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        Log.e("Height", height + "");
        Log.e("Final Height", ((actionBarHeight + 150) * 3) + "");
        int width = displayMetrics.widthPixels;


        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.height = (int) (height - (actionBarHeight + 150) * 3); // is in pixels
        gridView.setLayoutParams(layoutParams);

        List<AppointMntModel> appointMntModels = new ArrayList<>();
        Calendar cal = Calendar.getInstance(); // creates calendar

        for (int i = 0; i < 30; i++) {
            AppointMntModel appointMntModel = new AppointMntModel();
            if (i % 2 == 0) {
                appointMntModel.setState(0);
            } else {
                appointMntModel.setState(1);
            }
            cal.setTime(new Date()); // sets calendar time/date
            cal.add(Calendar.MINUTE, 15 * i);

            appointMntModel.setStartAppointMntDate(cal.getTime().toString());// returns new date object, 15 min in the future
            appointMntModels.add(appointMntModel);
        }
        appointmentsAdapter = new AppointmentsAdapter(getActivity(), appointMntModels);
        gridView.setAdapter(appointmentsAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), appointMntModels.get(position).getStartAppointMntDate(), Toast.LENGTH_SHORT).show();
            }
        });

        gridView.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            public void onSwipeTop() {
//                Log.d(DEBUG_TAG,"Swipe to UP");
            }

            public void onSwipeRight() {
                Log.d(DEBUG_TAG,"Swipe to RIGHT");

            }

            public void onSwipeLeft() {
                Log.d(DEBUG_TAG,"Swipe to LEFT");

            }

            public void onSwipeBottom() {
//                Log.d(DEBUG_TAG,"Swipe to DOWN");
            }

        });
    }


}
