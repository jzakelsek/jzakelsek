package com.example.upora.prevozi;   //ime tega razreda s paketom vred

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;   //alt+enter
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ActivityInsert extends AppCompatActivity {
    public static final int ACTIVITY_ID=101;  //vsaka aktivnost mora met svoj id

    private static final String TAG = ActivityInsert.class.getSimpleName();  //se generira samo, za spodnji TAG

    //----------------------------------------------------------------------------
    public static final String FORM_MODE_ID = "FORM_MODE_ID";  //key value
    public static final int FORM_MODE_INSERT = 0;
    public static final int FORM_MODE_UPDATE = 1;

    public static final String DATA_ACTION = "DATA_ACTION";  //key value
    public static final int DATA_EXIT = 0;
    public static final int DATA_UPDATE = 1;
    //----------------------------------------------------------------------------






    int formMode;

    //Driver oseba;
    EditText etName;
    EditText etAge;
    EditText etTime;
    Button btnAction;

    int st_voznikov;

   // Driving driving;  //dodamo v Driving

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);    //objekti se sami generirajo, da ne pišemo na roke  //iz XML parsanje

        // oseba = new Driver("jakob", 20, 55);
        //  System.out.println(oseba);
        //  Log.i(TAG, "Moj izpis: "+oseba.toString());   //ustvarim constraint field in MyActivity

        //driving = new Driving("Bistrica");  //dodamo v Driving

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etTime = findViewById(R.id.etTime);

        btnAction = findViewById(R.id.btnAction);
        setFormModeFromIntent(); //kličemo spodnjo metodo


        updateGUI();

    }

    //---------------------------FromIntent---------------------------------------------------
    //getIntent -> od Activity metoda
    private void setFormModeFromIntent() {
        formMode = FORM_MODE_INSERT;
        Bundle extras = getIntent().getExtras();        //ta dobi namig-intent v obliki key-value
        if (extras != null) {
            formMode = extras.getInt(FORM_MODE_ID);
            Log.i(TAG, "Set form mode:" + formMode);

        }
    }


    //------------------------------------------------------------------------------

    void updateGUI() {
        if (formMode == FORM_MODE_INSERT) {
            btnAction.setText("Insert");
            }
        if(formMode==FORM_MODE_UPDATE) {
        btnAction.setText("Update");
        }
    }


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

            Intent intent = new Intent();
            intent.putExtra(ActivityMain.KEY_NAME, ime);
            intent.putExtra(ActivityMain.KEY_AGE, starost);
            intent.putExtra(ActivityMain.KEY_TIME, cas);

            setResult(Activity.RESULT_OK, intent);

            Log.i(TAG, "PODATKI ZA PRENOS v DRUGO AKTIVNOST:" + ime +" "+starost+" "+cas);


           //etName.setText("");    //pobriše vnosno polje
           //etAge.setText("");
           //etTime.setText("");


            finish();
          // Log.i(TAG, "Inserted:" + inserted.toString());     //izpis v Logcat -ni potrebno
          // Log.i(TAG, "Driving Driver:" + driving.toString());

            //Toast.makeText(this, "New driver is inserted", Toast.LENGTH_LONG).show();

            //returnValue(DATA_UPDATE); //da je OK

        } catch (Exception e){

        Toast.makeText(this,"Check inserted value", Toast.LENGTH_LONG).show();
        Log.i(TAG, "Inserted values problem!");
       }
    }


    public void onClickInfo(View view) {
       Log.i(TAG, "V seznamu je: " + "?" + " elementov");

/*
        String value="AVTOR: Janko Zakelsek, Ptujska Gora, starost 38 let, RIT izredni ";
        public void onClickOpenActivityInfoForm(View view) {
            Intent i = new Intent(this, ActivityInfo.class);   //odprem aktivnost oz intent
            i.putExtra("key", value);
            startActivity(i);

  */
    }

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

    public void onClickExit(View view) {
        //finishAffinity();
        //System.exit(0);
        finish();
    }
}