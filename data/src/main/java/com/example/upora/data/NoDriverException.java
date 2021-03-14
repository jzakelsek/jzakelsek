package com.example.upora.data;

//8. ZAHTEVE NALOGE: napišite lastno izjemo, ki deduje iz razreda Exception
public class NoDriverException extends Exception{
    private String drivingName;

    //GENERATE -> CONSTRUCTOR -> EXCEPTION(s:String)
    public NoDriverException(String drivingName) {
        super();  //klic konstruktorja nadrazreda   ,   ker dedujem
        this.drivingName = drivingName;

    }
}
