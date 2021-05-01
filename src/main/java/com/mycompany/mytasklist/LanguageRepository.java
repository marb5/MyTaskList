package com.mycompany.mytasklist;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author marcin
 */
public class LanguageRepository {
    private List<Language> languages;
    
    LanguageRepository() {
        languages = new ArrayList<>();
        languages.add(new Language(1L, "Hello", "en"));
        languages.add(new Language(2L, "Witaj", "pl"));
    }
    
    //optional daje zabezpieczenie w przypadku, kiedy nie ma jezyka o takim id
    Optional<Language> findById(Long id) {
        //odfiltrowujemy z naszej listy szukane id, wyrzuca pierwszy znaleziony
        return languages.stream()
                .filter(l -> l.getId
                        .equals(id)).findFirst();
    }
}
