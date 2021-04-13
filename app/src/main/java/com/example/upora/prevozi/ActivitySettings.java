package com.example.upora.prevozi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivitySettings extends AppCompatActivity {

    public static final String MY_PREFERENCES = "NASTAVITVE";
    //private static final String MY_PREF="NASTAVITVE";
    private static final String MY_KEY = "KEY";


    private static final String TAG = ActivityInsert.class.getSimpleName();  //se generira samo, za spodnji TAG

    EditText eMail1;
    EditText userName1;
    EditText phoneNumber1;
    EditText password1;
    Button btnSettings;

    static int VisitCountActivitySettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //============================================================================================================================
        VisitCountActivitySettings += 1;
        SharedPreferences sharedPref2 = getSharedPreferences(ActivitySettings.MY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPref2.edit();
        editor2.putInt("CountActivitySettings", VisitCountActivitySettings);
        editor2.apply();
        //============================================================================================================================

        eMail1 = findViewById(R.id.eMail);
        userName1 = findViewById(R.id.userName);
        phoneNumber1 = findViewById(R.id.phoneNumber);
        password1 = findViewById(R.id.password);

        btnSettings = findViewById(R.id.btnSettings);

        TextView TextView_AppID;
        TextView_AppID = (TextView) findViewById(R.id.textView_appID);


        TextView TextView_Stat = (TextView) findViewById(R.id.textView_Stat);



        SharedPreferences sharedPrefUUID = getSharedPreferences(ActivitySettings.MY_PREFERENCES, Context.MODE_PRIVATE);
        String id_applikacije = sharedPrefUUID.getString("APP_UUID","");


        int countInfo = sharedPrefUUID.getInt("CountInfo",0);
        int countApplicationMy = sharedPrefUUID.getInt("CountApplicationMy",0);
        int countActivitySettings = sharedPrefUUID.getInt("CountActivitySettings",0);
        int countMain = sharedPrefUUID.getInt("CountMain",0);
        int countInsert = sharedPrefUUID.getInt("CountInsert",0);
        int countQr = sharedPrefUUID.getInt("CountQr",0);

        //String s1="JAnko";
        TextView_AppID.setText("APP UUID: "+id_applikacije);

        TextView_Stat.setText("STATISTIKA: " +" APP odprta: "+countApplicationMy+", AKTIVNOSTI odprte: ActivityInfo: "+ countInfo+", ActivityInsert: "+countInsert+", ActivityMain: "+countMain+", ActivityQR: "+countQr+", ActivitySettings: "+countActivitySettings);
        //TextView_Stat.setText(countInfo);

    }

    private static final String MAIL = "mail";

    public void onClickAddSettings(View view) {
       // String name = eMail1.getText().toString();



        SharedPreferences sharedPref = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("eMail", eMail1.getText().toString());
        editor.putString("userName", userName1.getText().toString());
        editor.putString("phoneNumber", phoneNumber1.getText().toString());
        editor.putString("password", password1.getText().toString());

        editor.apply();



        Log.i(TAG, "Elektronska posta: " + eMail1.getText().toString() + " Uporabnisko ime: " + userName1.getText());
       // Log.i(TAG, "Izpis: " + eMail1.getText().toString() + " " + userName1.getText().toString());
        //System.out.println(eMail1.getText().toString());

    }






}