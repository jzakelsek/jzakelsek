package com.example.upora.prevozi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ActivityInfo extends AppCompatActivity {

    TextView etTextView;
    static int VisitCountInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //============================================================================================================================
        VisitCountInfo += 1;
        SharedPreferences sharedPref2 = getSharedPreferences(ActivitySettings.MY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPref2.edit();
        editor2.putInt("CountInfo", VisitCountInfo);
        editor2.apply();
        //============================================================================================================================


      etTextView = (TextView) findViewById(R.id.textViewInfo);

      Bundle b1 = getIntent().getExtras();              //kdo je sprožil toto aktivnost
      String s1 = b1.getString("key");
      etTextView.setText(s1);                            //prikažem sporočilo


        }

    }




