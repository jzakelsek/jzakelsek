package com.example.upora.prevozi;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.upora.data.Driving;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ApplicationMy extends Application {


   // private static final String MY_PREF="NASTAVITVE";
    //že imam v ActivitySettings

    private static final String APP_ID ="APP_UUID_KEY";

    static int VisitCountApplicationMy;

    private Driving driving;
    //================================================================================
    private Gson gson;
    //================================================================================

    public  static  final String TAG=Application.class.getSimpleName();
    private static final String MY_FILE_NAME = "podatki.json";
    private File file;
    //================================================================================

    @Override
    public void onCreate() {
        super.onCreate();

        //============================================================================================================================
        VisitCountApplicationMy += 1;
        SharedPreferences sharedPref2 = getSharedPreferences(ActivitySettings.MY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPref2.edit();
        editor2.putInt("CountApplicationMy", VisitCountApplicationMy);
        editor2.apply();

        //============================================================================================================================


        //UUID - tukaj ne MainActivity (Manifest)
        // vsak zagon im svoj ID
        //================================================================================


        final String idAPP;

        SharedPreferences sharedPref = getSharedPreferences(ActivitySettings.MY_PREFERENCES, Context.MODE_PRIVATE);
        if(sharedPref.contains(APP_ID)) //če že ima id - app je že bila v uporabi
        {
            idAPP=sharedPref.getString(APP_ID, "DEFAULT VALUE");  //če ni not nič da privzeto vrednost
            //Log.d(TAG, "APP_UUID " + idAPP.toString());
        }

        else {   //prvi zagon
             idAPP = UUID.randomUUID().toString().replace("-", "");  //ustvarim UUID
            //Log.d(TAG, "APP_UUID " + idAPP.toString());

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("APP_UUID", idAPP);
            editor.apply();   //da se shrani
        }




//======================tukaj imam seznam ki ga serializiram ==========================================================
        if(!readFromFile()) //če ne preberem , ustvarim novi Driving
            driving = new Driving();  //dodamo v Driving  //  driving = new Driving("Bistrica");  //dodamo v Driving
//================================================================================

    }



  // UUID uporabimo da lahko ločimo objekte med seboj, ali če so podatki na internetu
    // prednost pred ključem je unikatnost, uporabimo da vidimo kaj se dogaja z aplikacijo-trendi




//================================================================================
    //Apache knjiznica   - DRIVING  se spremeni v string
    private void saveToFile() {
        try {                     //SERIALIZACIJA - driving se spremeni v string
            FileUtils.writeStringToFile(getFile(), getGson().toJson(driving));
        } catch (IOException e) {
            Log.d(TAG, "Can not save " + file.getPath());
        }
    }


    private boolean readFromFile() {
        if (!getFile().exists())  return false;  //če datoteka obstaja vrni false
        try {
            driving = getGson().fromJson(FileUtils.readFileToString(getFile()), Driving.class);
        } catch (IOException e) {
            return false;  //če pride do napake, se ustwari nov
        }
        return true;
    }

//================================================================================

    private Gson getGson() {
        if (gson == null)
            gson = new GsonBuilder().setPrettyPrinting().create();
        return gson;
    }

    private File getFile() {
        if (file == null) {
            File filesDir = getFilesDir();
            file = new File(filesDir, MY_FILE_NAME);
        }
        return file;
    }

    //ker je private
    public Driving getDriving() {
        return driving;
    }

    public void saveData() {
        saveToFile();
    }
}
