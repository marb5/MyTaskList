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
    public static final String defaultName = "world";
    public static final Language defaultLanguage = new Language(1L, "Hello ", "en");
    private LanguageRepository repository;
    
    public MyService() {
        this(new LanguageRepository());
    }
    
    public MyService(LanguageRepository repository) {
        this.repository = repository;
    }

    String greeting(String name, String langId) {
        //konwersja na Long w przypadku Stringa nie nullowego
        //w przeciwnym wypadku wartosc domysla id jezyka
        Long id = Optional.ofNullable(langId).map(Long::valueOf).orElse(defaultLanguage.getId());
        //pobieramy z repozytorium odpowiednia wiadomosc dla naszego jezyka
        String welcomeMsg = repository.findById(id).orElse(defaultLanguage).getMessage();
        //optional chroni przed wprowadzeniem wartosci NULL
        String welcomeName = Optional.ofNullable(name).orElse(defaultName);
        return welcomeMsg + " " + welcomeName + "!";
    }
}
