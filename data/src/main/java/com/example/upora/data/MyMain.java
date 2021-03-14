package com.example.upora.data;

import static com.example.upora.data.Driver.getRandomDriver;

//8.3.2021 21.30
public class MyMain {
    public static void main(String[] args) {
        System.out.println("Prevozi");

        Driving myDriving = new Driving("Vozaci iz bistrice"); // naredim skupino vozačev iz slov. bistrice

        //5. ZAHTEVE NALOGE: v main metodi napolnite podatkovno strukturo z 10 objekti, seznam sortiraj in izpiši
        Driver janko = new Driver("Janko", 38, 30);
        myDriving.add(janko);

        // boljše tako
        myDriving.add(new Driver("Ervin", 41, 55));
        myDriving.add(new Driver("Saso", 43, 30));
        myDriving.add(new Driver("Boris", 51, 59));
        myDriving.add(new Driver("Eva", 35, 55));
        myDriving.add(new Driver("Klavdija", 41, 50));
        myDriving.add(new Driver("Rok", 40, 48));
        myDriving.add(new Driver("Joze", 45, 47));
        myDriving.add(new Driver("Matej", 46, 46));
        myDriving.add(getRandomDriver());  //ta je naključno generiran

        //izpis neurejenega seznama
        //System.out.println(janko.toString());
         System.out.println("Neurejen seznam: ");
         System.out.println(myDriving.toString());   //metode .toString ne rabiš pisati

        //5.  ZAHTEVE VAJE: v metodi primerjajte objekta po poljubni lastnosti (po imenu)
        // izpis urejenega seznama po imenu
        System.out.println("Urejanje elementov po imenu ");
        myDriving.sortByName();
        System.out.println(myDriving);


        //6. UPORABA VMESNIKA -> vrne dolžino seznama (število voznikov)
        System.out.println("VMESNIK - na seznamu je voznikov: " + myDriving.size());


        //9. ZAHTEVE NALOGE: primer izjeme - lovimo
        Driver maxDriverTmp = null;    // dobimo objekt ven in ga dam v pomožno spremnljivko
        try {
            maxDriverTmp = myDriving.getMaxTimeWithException();
        } catch (NoDriverException e) {
            e.printStackTrace();
        }


        Driver minDriverTmp = myDriving.getMinTime();    //dam v pomožno sprem., spodaj izpišem še min čas vožnje
        double avgDriverTmp = myDriving.getAverageTime(); // spodaj izpišem še max čas vožnje

        if (maxDriverTmp != null) {  //da ne javi napake če je seznam prazen
            System.out.println("Najdaljsi cas voznje ima voznik: " + maxDriverTmp.getName() + ".");
            //DIREKT-> System.out.println("Najkrajsi cas voznje ima voznik: " + myDriving.getMinTime().getName() + ".");
            System.out.println("Najkrajsi cas voznje ima voznik: " + minDriverTmp.getName() + ".");
            System.out.println("Povprecni cas voznje znasa: " + avgDriverTmp+ ".");
        } else
            System.out.println("Ni voznika v seznamu.");



    }

}
