package com.example.upora.prevozi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.upora.data.Driver;
import com.example.upora.data.Driving;

//zanimiva poveza za gumbe: http://angrytools.com/android/button/

public class ActivityMain extends AppCompatActivity {


    private static final int INSERT_ACTIVITY_REQUEST=101;
    public static String KEY_NAME ="kljuc1";
    public static String KEY_AGE ="kljuc2";
    public static String KEY_TIME ="kljuc3";

    private static final int BARCODE_READER_REQUEST_CODE=202;

    Driving driving = new Driving("Bistrica");  //dodamo v Driving
    Driver inserted;



    public static String ime;
    public static int starost;
    public static double cas;

    private static final String TAG = ActivityMain.class.getSimpleName() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    //-------------1-------------StartActivityForResult-------------------------------------------------
    public void onClickOpenInsertFormForResult(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityInsert.class);  //prehod med formama
        //i.putExtra(ActivityInsert.FORM_MODE_ID, ActivityInsert.FORM_MODE_INSERT);  //dodatne informacije prenašamo z intentoom
        startActivityForResult(intent, INSERT_ACTIVITY_REQUEST);      //zaženemo drugo aktivnost ki vrne rezultat da vemo za katere aktivnosti, ko pridem nazaj, spodaj onResaultActivity


    }    //ko to pokličem se zažene Aktivnost Insert



    //Intent i = new Intent(getBaseContext(), ActivityInsert.class);  //prehod med formama




    //------------------onClick-------OpenInsertForm-----------------------------------------------------
    public void onClickOpenInsertForm(View view) {
        Intent i = new Intent(getBaseContext(), ActivityInsert.class);  //prehod med formama
        i.putExtra(ActivityInsert.FORM_MODE_ID, ActivityInsert.FORM_MODE_INSERT);
        startActivity(i);  // novi namen,
    }

    //------------------------------------------------------------------------------
    public void onClickOpenUpdateForm(View view) {
        Intent i = new Intent(getBaseContext(), ActivityInsert.class);  //prehod med formama
        i.putExtra(ActivityInsert.FORM_MODE_ID, ActivityInsert.FORM_MODE_UPDATE);   //key value
        startActivity(i);
    }


    // ko se vrnemo, po končani aktivnosti
    //---------------------------------onActivityResult--------------------------2----------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);    //data podatki

        if (resultCode==ActivityInsert.RESULT_OK) {            //če je rezultat ok, oz s tem vemo da ni prišlo do napake pri vnosu pri prejšnji Aktivnosti
            if (requestCode==INSERT_ACTIVITY_REQUEST) {         // da vemo za katero aktivnost gre

               // Intent intent = getIntent();
                //int tmp = intent.getIntExtra(KEY_AGE, 0);


               ime = data.getStringExtra(KEY_NAME);                   // iz data s ključem preberem podatke
               starost = data.getIntExtra(KEY_AGE,0);
               cas = data.getDoubleExtra(KEY_TIME,0);

                /*//kaj dobim nazaj
                Log.i(TAG, "IMEEEEEEEEEEEEEEEEEEEEE:" + ime);
                Log.i(TAG, "STAROSTTTTTTTTTTTTTTTTT:" + starost);
                Log.i(TAG, "CASSSSSSSSSSSSSSSSSSSSS:" + cas);
                */

                inserted = new Driver(ime,starost,cas);   //ustvarim novega voznika
                driving.add(inserted);                      //ga dam v seznam


                Log.i(TAG, "Inserted:" + inserted.toString());     //izpis v Logcat -ni potrebno
                Log.i(TAG, "Driving Driver:" + driving.toString());
            }

        }

    }
    //---------------------------------------------------------------------------------------------



    public void onClickOpenWeb(View view) {

        String goStr=String.format(getString(R.string.toast_go), "Janko");
        Toast.makeText(getBaseContext(),goStr,Toast.LENGTH_LONG).show();

        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.24ur.com/"));
        startActivity(i);

    }

    //----------------------ActivityInfoForm------------------

    String value="AVTOR: Janko Zakelsek, Ptujska Gora, starost 38 let, RIT izredni ";
    public void onClickOpenActivityInfoForm(View view) {
        Intent i = new Intent(this, ActivityInfo.class);   //odprem aktivnost oz intent
        i.putExtra("key", value);         //pošljem ključ in vrednost
        startActivity(i);                         //zaženem aktivnost
    }


    public void ExitMainActivity(View view) {
        finish();
    }


    public void onClickOpenInsertFormForResultQr(View view) {

        Intent intent = new Intent(ActivityMain.this, ActivityQr.class);
        startActivityForResult(intent, BARCODE_READER_REQUEST_CODE);

    }
}