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
    //Driver oseba;
    EditText etName;
    EditText etAge;
    EditText etTime;

    Driving driving;  //dodamo v Driving

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    //objekti se sami generirajo, da ne pišemo na roke  //iz XML parsanje

        // oseba = new Driver("jakob", 20, 55);
        //  System.out.println(oseba);
        //  Log.i(TAG, "Moj izpis: "+oseba.toString());   //ustvarim constraint field in MyActivity

        driving = new Driving("Bistrica");  //dodamo v Driving

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etTime = findViewById(R.id.etTime);
    }

    public void onClickAdd(View view) {   //dodam metodo

        try {
        Driver inserted = new Driver(etName.getText().toString(), Integer.parseInt(etAge.getText().toString()), Double.parseDouble(etTime.getText().toString()));   //branje vnosnih polj

           driving.add(inserted);

           etName.setText("");    //pobriše vnosno polje
           etAge.setText("");
           etTime.setText("");

           Log.i(TAG, "Inserted:" + inserted.toString());     //izpis v Logcat -ni potrebno
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