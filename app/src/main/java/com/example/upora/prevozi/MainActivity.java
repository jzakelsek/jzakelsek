package com.example.upora.prevozi;   //ime tega razreda s paketom vred

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;   //alt+enter
import android.view.View;
import android.widget.EditText;

import com.example.upora.data.Driver;
import com.example.upora.data.Driving;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();  //se generira samo, za spodnji TAG
    Driver jakob;
    EditText etName;
    EditText etAge;
    EditText etTime;
    Driving driving;  //dodamo v Driving

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    //objekti se generirajo
        jakob = new Driver("jakob", 20, 55);

        driving = new Driving("Bistrica");  //dodamo v Driving

        System.out.println(jakob);
        Log.i(TAG, "Moj izpis: "+jakob.toString());   //create constant field in Myactivity
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etTime = findViewById(R.id.etTime);
    }

    public void onClickAdd(View view) {
       // Log.i(TAG, etName.getText().toString());
        //Log.i(TAG, etAge.getText().toString());
       // String name = etName.getText().toString();

       try {
        Driver inserted = new Driver(etName.getText().toString(), Integer.parseInt(etAge.getText().toString()), Double.parseDouble(etTime.getText().toString()));

           driving.add(inserted);

           etName.setText("");
           etAge.setText("");
           etTime.setText("");


           Log.i(TAG, "Inserted:" + inserted.toString());
           Log.i(TAG, "Driving Driver:" + driving.toString());

    } catch (Exception e){
           Log.i(TAG, "Inserted values problem!");
       }
    }

    public void onClickInfo(View view) {
       Log.i(TAG, "V seznamu je: " + driving.size() + " elementov");



    }

    public void onClickExit(View view) {
        finishAffinity();
        System.exit(0);

    }
}