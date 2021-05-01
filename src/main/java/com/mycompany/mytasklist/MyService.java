package com.mycompany.mytasklist;
import java.util.Optional;

/**
 *
 * @author marcin
 */
//tworzenie serwisu, reprezentuje on kolejna wartwe, odpowiadajaca za logike biznesowa
//przygotowuje nasze powitanie
class MyService {
    //zmienna przechowujaca, co zwrocic w przypadku braku parametru name
    private static final String defaultName = "world";
    private static final Language defaultLanguage = new Language(1L, "Hello ", "en");
    private LanguageRepository repository;
    
    public MyService() {
        this(new LanguageRepository());
    }
    
    public MyService(LanguageRepository repository) {
        this.repository = repository;
    }
    
    //metoda zwraca odpowiedznie przywitanie z serwera, w zaleznosci od parametru name
    String greeting(String name) {
        return greeting(name, defaultLanguage.getId());
    }
    
    String greeting(String name, Long id) {
        String welcomeMsg = repository.findById(id).orElse(defaultLanguage).getMessage();
        //optional chroni przed wprowadzeniem wartosci NULL
        String welcomeName = Optional.ofNullable(name).orElse(defaultName);
        return welcomeMsg + " " + welcomeName + "!";
    }
}
