package com.mycompany.mytasklist;

/**
 *
 * @author marcin
 */
//tworzenie serwisu, reprezentuje on kolejna wartwe, odpowiadajaca za logike biznesowa
//przygotowuje nasze powitanie
class MyService {
    //zmienna przechowujaca, co zwrocic w przypadku braku parametru name
    private static final String defaultGreeting = "world";
    
    //metoda zwraca odpowiedznie przywitanie z serwera, w zaleznosci od parametru name
    String greeting(String name) {
        if (name != null)
            return name;
        return defaultGreeting;
    }
}
