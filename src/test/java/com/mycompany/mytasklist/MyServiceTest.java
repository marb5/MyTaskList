package com.mycompany.mytasklist;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
/**
 *
 * @author marcin
 */
public class MyServiceTest {
    private final static String WELCOME = "Hello";
    
    private LanguageRepository getMockLanguageRepository() {
        //tworzymy obiekt, w ktorym podmieniamy dane z LanguageRepository
        //specjalnie skonfigurowane do testow
        return new LanguageRepository() {
            //przeciazenie metody, aby zawsze zwracala konkretny lang
            @Override
            Optional<Language> findById(Long id) {
                //optional of, a nie ofNullable, bo mamy pewnosc, ze obiekt
                //istnieje, bo wlasnie go tworzymy
                return Optional.of(new Language(null, WELCOME, null));
            }
        };
    }
    
    @Test
    public void testGreeting_nullName_returnsDefaultValue() throws Exception {
        //given
        var mockRepository = getMockLanguageRepository();
        //SUT - system under test
        //tworzymy serwis z mockowany repozytorium, aby miec pewnosc,
        //ze ono istnieje
        MyService SUT = new MyService(mockRepository);
        
        //when
        String result = SUT.greeting(null, "-1");
        
        //then
        assertEquals(WELCOME + ' ' + MyService.defaultName + '!', result);
    }
    
    @Test
    public void testGreeting_name_returnsName() throws Exception {
        //given
        var mockRepository = getMockLanguageRepository();
        MyService SUT = new MyService(mockRepository);
        String name = "TwojeImie";
        
        //when
        String result = SUT.greeting(name, "-1");
        
        //then
        assertEquals(WELCOME + ' ' + name + '!', result);
    }
}
