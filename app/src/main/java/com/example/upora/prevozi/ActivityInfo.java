package com.example.upora.prevozi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ActivityInfo extends AppCompatActivity {

    TextView etTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


      etTextView = (TextView) findViewById(R.id.textViewInfo);

      Bundle b1 = getIntent().getExtras();              //kdo je sprožil toto aktivnost
      String s1 = b1.getString("key");
      etTextView.setText(s1);                            //prikažem sporočilo


        }

    }




