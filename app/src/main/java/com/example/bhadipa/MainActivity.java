package com.example.bhadipa;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;


import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {


    private Context mContext;
    private int mPickedColor = Color.WHITE;





    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        ImageView imageView  = findViewById(R.id.imageView4);

        // Set the application context
        mContext = getApplicationContext();

        // Set an click listener for Button widget
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get a GridView object from ColorPicker class
                GridView gv = (GridView) ColourPicker.getColorPicker(MainActivity.this);

                // Initialize a new AlertDialog.Builder object
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // Set the alert dialog content to GridView (color picker)
                builder.setView(gv);

                // Initialize a new AlertDialog object
                final AlertDialog dialog = builder.create();

                // Show the color picker window
                dialog.show();

                // Set the color picker dialog size
                dialog.getWindow().setLayout(
                        getScreenSize().x - rl.getPaddingLeft() - rl.getPaddingRight(),
                        getScreenSize().y - getStatusBarHeight() - rl.getPaddingTop() - rl.getPaddingBottom());

                // Set an item click listener for GridView widget
                gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Get the pickedColor from AdapterView
                        mPickedColor = (int) parent.getItemAtPosition(position);

                        // Set the layout background color as picked color
                        rl.setBackgroundColor(mPickedColor);

                        // close the color picker
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    // Custom method to get the screen width in pixels
    private Point getScreenSize(){
        WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        //Display dimensions in pixels
        display.getSize(size);
        return size;
    }

    // Custom method to get status bar height in pixels
    public int getStatusBarHeight() {
        int height = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }
}


