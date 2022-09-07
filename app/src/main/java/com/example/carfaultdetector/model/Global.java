package com.example.carfaultdetector.model;

public class Global {
    public static String userName = null;
    public static String email = null;
    public static String password = null;

    //To jest do telefonu - ipconfig i sprawdzic ip komputera
    //public static String url = "http://192.168.0.38:8080";

    //To jest do emulatora
    public static String url = "http://10.0.2.2:8080";

    public static int boolToInt(Boolean bool){
        if(bool){
            return 1;
        }
        return 0;
    }
}
