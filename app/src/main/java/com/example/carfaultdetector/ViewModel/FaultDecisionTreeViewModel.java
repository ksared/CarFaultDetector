package com.example.carfaultdetector.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FaultDecisionTreeViewModel extends ViewModel {

    public MutableLiveData<Integer> mutableLiveDataHalas = new MutableLiveData<>();
    protected String wynik = "";

    public String wynikHalas(boolean[] bool){
        if(bool[0]){
            if(bool[5]){
                if(bool[10]){
                    wynik = "uszkodzony silnik";
                }
                else if(bool[9]){
                    wynik = "Czujnik położenia wału lub przepustnica";
                }
                else{
                    wynik = "Usterka elektroniki silnika";
                }
            }
            else{
                wynik = "Prawdopodobne przyczyny: poduszka silnika, wtryski, turbosprężarka";
            }

        }
        else if(bool[1]){
            if(bool[6]){
                wynik = "Nieprawidłowa zbieżność";
            }
            else if(bool[4]){
                wynik = "nieprawidłowa geometria";
            }
            else if(bool[8]){
                wynik = "Uszkodzony przegub";
            }
            else{
                wynik = "uszkodzone zawieszenie";
            }
        }
        else if(bool[2]){
            if(bool[10]){
                wynik = "Zapchany dpf lub filtr powietrza";
            }
            else{
                wynik = "dziurawy układ wydechowy";
            }
        }
        else if(bool[3]){
            if(bool[4]){
                wynik = "Uszkodzone klocki lub tarcze hamulcowe";
            }
            else{
                wynik = "Uszkodzona piasta koła";
            }
        }
        else if(bool[4]){
            wynik = "Nieprawidłowa geometria";
        }
        else if(bool[5]){
            wynik = "Zbyt mało informacji do stwierdzenia usterki";
        }
        else if(bool[6]){
            if(bool[8]){
                wynik = "Uszkodzone zawieszenie";
            }
            else{
                wynik = "Zbyt mało informacji do stwierdzenia usterki";
            }
        }
        else if(bool[7]){
            if(bool[9]){
                wynik = "Uszkodzona przepustnica";
            }
            else if(bool[10]){
                wynik = "Uszkodzony czujnik obrotów wału";
            }
            else{
                wynik = "Zbyt mało informacji do stwierdzenia usterki";
            }
        }
        else if(bool[8]){
            wynik = "Uszkodzone klocki lub tarcze hamulcowe";
        }
        else if(bool[9]){
            wynik = "Możliwe uszkodzenia: przepustnica, turbosprężarka, wtryskiwacze";
        }
        else{
            wynik = "Zbyt mało informacji do stwierdzenia usterki";
        }
        return wynik;
    }

    public String wynikOdpalanie(){
        return  wynik;
    }
}
