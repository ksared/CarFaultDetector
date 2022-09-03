package com.example.carfaultdetector.model;

public class Global {
    public static String userName = null;
    public static String email = null;
    public static String password = null;

    public static int boolToInt(Boolean bool){
        if(bool){
            return 1;
        }
        return 0;
    }
}
