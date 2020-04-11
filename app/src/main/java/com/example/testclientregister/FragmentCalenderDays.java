package com.example.testclientregister;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class FragmentCalenderDays extends Fragment {
    CalendarView calendarView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_days, container, false);
        calendarView = view.findViewById(R.id.calendarView);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        calendarView.setSelectedDates(getSelectedDays());

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                Toast.makeText(getActivity(), clickedDayCalendar.getTime().toString(), Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, FragmentListAppointmentsInDay.setDate(clickedDayCalendar.getTime().toString()))
                        .commit();
            }
        });

        calendarView.setOnPreviousPageChangeListener(() -> {
            Toast.makeText(getActivity(), "Load previous days of month", Toast.LENGTH_SHORT).show();
        });

        calendarView.setOnForwardPageChangeListener(() -> {
            Toast.makeText(getActivity(), "Load next days of month", Toast.LENGTH_SHORT).show();
        });
    }

    private List<Calendar> getSelectedDays() {
        List<Calendar> calendars = new ArrayList<>();

        Calendar calendar = new GregorianCalendar();
        int today = calendar.get(Calendar.DAY_OF_MONTH);
        int numDaysInMonth = getNumDaysInThisMonth(calendar, Calendar.MONTH, Calendar.YEAR);

        for (int i = -today + 1; i < numDaysInMonth - today; i++) {
            calendar = new GregorianCalendar();
            calendar.add(Calendar.DAY_OF_MONTH, i);
            calendars.add(calendar);
        }

        return calendars;
    }

    private int getNumDaysInThisMonth(Calendar calendar, int month, int year) {
        calendar.set(year, month, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
