package com.example.testclientregister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.round_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        frameLayout = findViewById(R.id.frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new FragmentCalenderDays())
                .commit();

    }

    private void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        dialogBuilder.setIcon(R.drawable.ic_launcher_background);
        dialogBuilder.setTitle("Family List");

        ArrayList<String> list = new ArrayList<String>();
        list.add(new String("Mo'men Shaheen"));
        list.add(new String("Nesma Tharwat"));
        list.add(new String("Ahmed Mounir"));
        list.add(new String("Hamo Beka"));

        DialogAdapter dialogAdapter = new DialogAdapter(this, list);

        dialogBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
//seting adapter to dialog
        dialogBuilder.setAdapter(dialogAdapter, null);
//show dialog
        dialogBuilder.show();
    }
}
