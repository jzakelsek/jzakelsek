package com.example.upora.prevozi;   //ime tega razreda s paketom vred

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;   //alt+enter
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.upora.data.Driver;
import com.example.upora.data.Driving;


public class ActivityInsert extends AppCompatActivity {
    public static final int ACTIVITY_ID=101;  //vsaka aktivnost mora met svoj id

    private static final String TAG = ActivityInsert.class.getSimpleName();  //se generira samo, za spodnji TAG

    //----------------------------------------------------------------------------
    public static final String FORM_MODE_ID = "FORM_MODE_ID";  //key value
    public static final String POZICIJA = "POZICIJA";

    public static final int FORM_MODE_INSERT = 0;
    public static final int FORM_MODE_UPDATE = 1;

    public static final String DATA_ACTION = "DATA_ACTION";  //key value
    public static final int DATA_EXIT = 0;
    public static final int DATA_UPDATE = 1;
    //----------------------------------------------------------------------------

    int formMode;
    int poz;

    static int VisitCountInsert;

    EditText etName;
    EditText etAge;
    EditText etTime;
    Button btnAction;

    //================================================================================
    private ApplicationMy app;
    //================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);    //objekti se sami generirajo, da ne pišemo na roke  //iz XML parsanje



//================================================================================
        app = (ApplicationMy) getApplication();       //objek, svoj da lahko do njega dostopamo
        //Log.i(TAG, "app vrednost:" + app.a);
//================================================================================


//============================================================================================================================
        VisitCountInsert += 1;
        SharedPreferences sharedPref2 = getSharedPreferences(ActivitySettings.MY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPref2.edit();
        editor2.putInt("CountInsert", VisitCountInsert);
        editor2.apply();
//============================================================================================================================

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etTime = findViewById(R.id.etTime);

        btnAction = findViewById(R.id.btnAction);
        setFormModeFromIntent(); //kličemo spodnjo metodo


        updateGUI();

    }

    //---------------------------FromIntent-----
    //getIntent -> od Activity metoda
    private void setFormModeFromIntent() {
        formMode = FORM_MODE_INSERT;                  // FORM_MODE_INSERT = 0;
        Bundle extras = getIntent().getExtras();      //ta dobi namig-intent v obliki key-value
        if (extras != null) {
            formMode = extras.getInt(FORM_MODE_ID);         //FORM_MODE_ID = "FORM_MODE_ID";
            poz = extras.getInt(POZICIJA);
            Log.i(TAG, "Set form mode:" + formMode);
            //Log.i(TAG, "Pozicija:" + poz);


        }
    }


//---------------

    void updateGUI() {
        if (formMode == FORM_MODE_INSERT) {   // FORM_MODE_INSERT = 0;
            btnAction.setText("Vnos");
            }
        if(formMode==FORM_MODE_UPDATE) {      //FORM_MODE_UPDATE = 1;
        btnAction.setText("Posodobitev");




        }
    }
//----------------------
    public void onClickAdd(View view) {   //dodam metodo
        try {

            //preverjanje vnosa
            if (TextUtils.isEmpty(etName.getText())) {
                etName.setError("Empty is not allowed");
                return;
            }


/*
        Driver inserted = new Driver(etName.getText().toString(), Integer.parseInt(etAge.getText().toString()), Double.parseDouble(etTime.getText().toString()));   //branje vnosnih polj
        driving.add(inserted);
*/



            String ime=etName.getText().toString();
            int starost = Integer.parseInt(etAge.getText().toString());
            double cas = Double.parseDouble(etTime.getText().toString());

            if(formMode==FORM_MODE_UPDATE){
                Driver inserted = new Driver(ime,starost,cas);

                app.getDriving().setDriverAtPos(poz, inserted);
                //Driver tmp = app.getDriving().getDriverAtPos(poz);


                app.saveData(); //shranemo na telefon, ni treba niti

            }

            Intent intent = new Intent();
            intent.putExtra(ActivityMain.KEY_NAME, ime);
            intent.putExtra(ActivityMain.KEY_AGE, starost);
            intent.putExtra(ActivityMain.KEY_TIME, cas);

            setResult(Activity.RESULT_OK, intent);

            Log.i(TAG, "PODATKI ZA PRENOS v DRUGO AKTIVNOST:" + ime +" "+starost+" "+cas);

            finish();

            Toast.makeText(this, "Voznik je uspesno posodobljen", Toast.LENGTH_LONG).show();





            //returnValue(DATA_UPDATE); //da je OK

        } catch (Exception e){

        Toast.makeText(this,"Check inserted value", Toast.LENGTH_LONG).show();
        Log.i(TAG, "Inserted values problem!");
       }
    }


    //---------------------------------------------------------------------------------------------------------------------------------------------





    private void returnValue(int dataStatus){
        Intent data = getIntent();
        data.putExtra(DATA_ACTION, dataStatus);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Pause"+formMode);
    }

//Application -----    v tej aktivnosti ni seznama, samo tekoči podatki ki jih pošiljam v MainActivity
                        //seznam je v datoteki, do katerega dostopam preko razreda Application
//================================================================================
    //dostop do seznama iz druge aktivnosti - ActivityMain
    public void onClickInfo(View view) {
        Log.i(TAG, "V seznamu je st. voznikov: " + app.getDriving().getSize());
        Log.i(TAG, "V seznamu so naslednji vozniki: " + app.getDriving().toString());

    }

//================================================================================

    public void onClickExit(View view) {
        //finishAffinity();
        //System.exit(0);
        finish();
    }
}