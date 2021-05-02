package com.mycompany.mytasklist;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author marcin
 */
//repozytorium, w ktorym przechowujemy obiekty naszych jezykow, kiedy potrzeba
//bedzie sie laczyc z baza danych
public class LanguageRepository {
    private List<Language> languages;
    
    LanguageRepository() {
        languages = new ArrayList<>();
        languages.add(new Language(1, "Hello", "en"));
        languages.add(new Language(2, "Witaj", "pl"));
    }
   
    //optional daje zabezpieczenie w przypadku, kiedy nie ma jezyka o takim id
    Optional<Language> findById(Integer id) {
        //odfiltrowujemy (przy pomocy strumienia) z naszej listy szukane id, 
        //wyrzuca pierwszy znaleziony
        return languages.stream()
                .filter(l -> l.getId()
                        .equals(id)).findFirst();
    }
}
