package com.example.upora.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

//uporaba vmesnika
public class Driving implements Sizable{

    private String name;    //ime skupine vozačev (BISTRICA)
    private ArrayList<Driver> driverArrayList;  // novi seznam z imemnom driverArrayList... private-skrijemo sprem.
                                               // driver ArryList je tipa ArryList



    //================================================================================
    // V KONSTUKTOR DODAM UUID

    //generiramo konstuktor
    public Driving(String name) {
        this.name = name;
        driverArrayList = new ArrayList<>();  // vse v javi je privzeto null, zato moramo narediti objekt tipa ArryList -> drugače napaka
        final String DRIVING_UUID = UUID.randomUUID().toString().replace("-", "");
    }

    //================================================================================


    //privzeti konstruktor - Aktivnost AplicationMy -onCreate
    public Driving() {
    this("Bistrica");


    }



                          //v listo damo stvari-voznike
    public void add(Driver driver){
        driverArrayList.add(driver);
    }


    // ZAHTEVE NALOGE: demosntriraj uporabo vmesnika
    public int size(){
        int size = driverArrayList.size();
        return size;
    }


    //7. ZAHTEVE NALOGE: napiši vsaj tri statične metode: getMax, getMin, getAvarage
    //9. ZAHTEVE NALOGE: ta metoda  vključuje izjemo
        public Driver getMaxTimeWithException() throws NoDriverException {    //throws označi možne izjeme
        if(driverArrayList.size()==0) throw  new NoDriverException(name);  //throw del ki sproži izjemo
        Driver tmp = driverArrayList.get(0);
        for (int i=1;i<driverArrayList.size();i++) {
            if (driverArrayList.get(i).getTime() > tmp.getTime()) tmp=driverArrayList.get(i);
        }                                         //rabim čas ->Driver -> GETTER ...
        return tmp;
}

    public Driver getMinTime()  {
        if(driverArrayList.size()==0) return null;
        Driver tmp = driverArrayList.get(0);
        for (int i=1;i<driverArrayList.size();i++) {
            if (driverArrayList.get(i).getTime() < tmp.getTime()) tmp=driverArrayList.get(i);
        }                          //ta čas < od trenutnega ?   JA   potem v tmp dam ta čas
        return tmp;
    }

    public double getAverageTime() {
        //if(driverArrayList.size()==0) return null;
        Driver tmp = driverArrayList.get(0);
        //double tmp2=0;
        double time=0;
        for (int i=1;i<driverArrayList.size();i++) {

            time += driverArrayList.get(i).getTime();
        }
        double tmp2=time/driverArrayList.size();
        return tmp2;
    }



    //4. ZAHTEVE NALOGE: povozite metodo toString
    //POVOZIMO METODO toString iz Objekta in jo na novo napišemo -> GENERATE -> toString
    @Override
    public String toString() {
        return "Driving{" +
                "name='" + name + '\'' +
                ", driverArryList=" + driverArrayList +  //toString kliče listo driverArryList
                '}';
    }


    // 3. ZAHTEVE NALOGE: primerjamo objekte po imenu
    // da ne vzamemo liste direkt v glavnem programu
    // ZA COMPARABLE, sort je statična metoda ker je Collections ime razreda in ni objekt
    public void sortByName() {
        Collections.sort(driverArrayList);  //not dam seznam, seznam ima elemente, ki implementirajo Comparable
                                             // lahko uporabiš Comperator, če sta npr. dva elementa enakega imena...
    }

    //to rabimo da dolžino v Activity Insert
    public int getSize() {
    return driverArrayList.size();
                          }
                      }
