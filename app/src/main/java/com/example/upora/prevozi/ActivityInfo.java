package com.example.upora.prevozi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ActivityInfo extends AppCompatActivity {




    //private static final String TAG = ActivityMain.class.getSimpleName() ;

    TextView etTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);



        //Intent secondIntent = getIntent();
        //String message = secondIntent.getStringExtra("key");


      etTextView = (TextView) findViewById(R.id.textViewInfo);

      Bundle b1 = getIntent().getExtras();
      String s1 = b1.getString("key");
      etTextView.setText(s1);

      // etTextView.setText(message);

      //Log.i(TAG, value);

        }

    }




