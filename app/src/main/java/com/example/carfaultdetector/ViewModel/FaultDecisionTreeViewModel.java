package com.example.carfaultdetector.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FaultDecisionTreeViewModel extends ViewModel {

    //public MutableLiveData<Integer> mutableLiveDataHalas = new MutableLiveData<>();
    protected String wynik = "Zbyt mało informacji do stwierdzenia usterki";

    public String wynikHalas(Boolean[] bool){
        wynik = "Zbyt mało informacji do stwierdzenia usterki";
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


    public String wynikOdpalanie(Boolean[] bool){
        wynik = "Zbyt mało informacji do stwierdzenia usterki";

        if(bool[5]){
            wynik = "Należy uzupełnić poziom paliwa";
            return wynik;
        }
        if(bool[1]){
            wynik = "Należy sprawdzić stan akumulatora, następnie podpięcie kabli do akumulatora";
            return wynik;
        }
        if(bool[0]){
            wynik = "Uszkodzony immobilizer";
            return wynik;
        }
        if(bool[2]){
            if(bool[6]){
                wynik = "Uszkodzenie pompy oleju";
            }
            else if(bool[7]){
                wynik = "Należy sprawdzić stan naładowania akumulatora";
            }
            else{
                wynik = "Uszkodzony rozrusznik";
            }
            return wynik;
        }
        if(bool[3]){
            wynik = "Należy sprawdzić stan świec zapłonowych/żarowych i kompresji silnika";
            return wynik;
        }
        if(bool[4]){
            if(bool[6]){
                wynik = "Zbyt niski poziom oleju lub uszkodzona pompa oleju";
            }
            else if(bool[7]){
                wynik = "Uszkodzone świece żarowe";
            }
            else{
                wynik = "Uszkodzona pompa paliwa";
            }
            return wynik;
        }
        if(bool[6]){
            wynik = "Zbyt mało informacji do stwierdzenia usterki";
            return wynik;
        }
        if(bool[7]){
            wynik = "Zbyt mało informacji do stwierdzenia usterki";
        }
        return  wynik;
    }

    public String wynikInne(Boolean[] booleans){
        wynik = "Zbyt mało informacji do stwierdzenia usterki";
        if(booleans[3]){
            if(booleans[1]){
                if(booleans[2]){
                    if(booleans[7]){
                        wynik = "Uszkodzona opona";
                    }
                    else {
                        wynik = "Dziura w dolocie";
                    }
                }
                else {
                    wynik = "Uszkodzona turbina";
                }
            }
            else if(booleans[0]){
                if(booleans[4]){
                    wynik = "Uszkodzona sonda lambda";
                }
                else{
                    wynik = "Zbyt mała kompresja silnika";
                }
            }
            else if(booleans[2]){
                wynik = "Uszkodzony czujnik połozenia wału";
            }
        }
        else if(booleans[6]){
            if(booleans[8]){
                wynik = "Uszkodzony zacisk lub układ ABS";
            }
            else if(booleans[7]){
                wynik = "Niewłaściwa zbieżność lub geometria";
            }
            else{
                wynik = "Uszkodzony układ hamulcowy";
            }
        }
        else if(booleans[2]){
            if(booleans[5]){
                wynik = "Uszkodzona elektronika";
            }
            else{
                wynik = "Uszkodzona układ paliwowy";
            }
        }
        else if(booleans[7]){
            if(booleans[8]){
                wynik = "Zapieczony hamulec";
            }
            else{
                wynik = "Za małe ciśnienie w jednej z opon";
            }
        }

        return wynik;
    }
}