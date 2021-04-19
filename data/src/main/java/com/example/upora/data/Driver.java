package com.example.upora.data;

import java.util.Random;
import java.util.UUID;

//PROJEKT: PREVOZI (vodimo seznam voznikov, da vemo kdo je na vrsti za prevoz v službo)

                    //2. ZAHTEVE NALOGE: vsaj 1 razred mora implementirati razred Comparable
public class Driver implements Comparable<Driver> {  //extends Object -> tega ne pišemo
                    //INTERFACE
//================================================================================
final String DRIVER_UUID = UUID.randomUUID().toString().replace("-", "");
//================================================================================

    //METODA, KI GENERIRA NAKLJUČEN STRING
    //https://stackoverflow.com/questions/20536566/creating-a-random-string-with-a-z-and-0-9-in-java
    public static String generateRandomChars() {
        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        int length = 4;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));
        }
        return sb.toString();
}
        //POMOŽNA METODA, ki vrne ime (naključen string), in starost in čas (naključno generirani števili)
        public static Driver getRandomDriver(){
            int min = 10;
            int max = 100;
            String name = generateRandomChars();
            int age = new Random().nextInt(20);
            int time2 = new Random().nextInt(20);
            double time = new Double(time2);
            return new Driver(name, age, time);
        };


    //1. ZAHTEVE NALOGE: tri lastnosti različnih tipov
    //spremenljivke - protect ker želimo da so skrite
    private String name; //ime voznika
    private int age;     // starost voznika
    private double time; // čas vožnje v minutah


    // GENERIRAMO konstruktor za inicializacijo spremenljivk
    public Driver(String name, int age, double time) {  //String name, je lokalna sprem.
        this.name = name;
        this.age = age;                           //this.age -> dostopam do razredne sprem.
        this.time = time;
    }


    //GETTER
    //ker rabim čas v glavnem programu, v Driving pri iskanju max časa -> GETTER in naredim metodo ki vrne čas
    public double getTime() {
        return time;
    }
    // ker rabim ime v glavnem programu
    public String getName() {
        return name;
    }

    public int getAge() {
                            return age;
                        }


                        //4. ZAHTEVE NALOGE:
    // prekrijemo metodo iz Objecta  -> Generate-toString
    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", time=" + time +
                '}';
    }

    //3. ZAHTEVE VAJE: v metodi primerjajte objekta po poljubni lastnosti (po imenu)
    //ce zgoraj na implements Comparable gres + ALT+ENTER -> Implement method
    // ne uporabimo že ustvarjene ampak jo sami na novo napišemo
    @Override
    public int compareTo(Driver o) {  //metoda za primerjavo, prejme drugi objekt in tega primerja
        return name.compareTo(o.getName());  // primerjamo po imenu-   CompareTo vrača -1=manjša 0=enaka 1=večja
    }                                        //  comapareTO - kličem metodo nad stringom
}
