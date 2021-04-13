package com.example.upora.prevozi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


/*
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
*/


public class ActivityQr extends AppCompatActivity {

    static int VisitCountQr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);


        //============================================================================================================================
        VisitCountQr += 1;
        SharedPreferences sharedPref2 = getSharedPreferences(ActivitySettings.MY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPref2.edit();
        editor2.putInt("CountQr", VisitCountQr);
        editor2.apply();
        //============================================================================================================================




    }
/*
    public void onClickAddQr(View view) {
         final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
        Intent intent = new Intent(ACTION_SCAN);
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);
    }



        Intent intent = new Intent();
        intent.putExtra(ActivityMain.KEY_NAME, ime);
        intent.putExtra(ActivityMain.KEY_AGE, starost);
        intent.putExtra(ActivityMain.KEY_TIME, cas);

        setResult(Activity.RESULT_OK, intent);

        Log.i(TAG, "PODATKI ZA PRENOS v DRUGO AKTIVNOST:" + ime +" "+starost+" "+cas);
*/









    public void onClickExitQr(View view) {
        finish();
    }
}