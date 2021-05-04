package com.mycompany.mytasklist.language;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
/**
 *
 * @author marcin
 */

@Table( name = "LANGUAGE" )
//Data Transfer Object, obiekt przechowujace dane do przesylania z serweru
//pobrane z odpowiednich rekordow tablic bazy danych
public class LanguageDTO {
    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer id;
    @Column(name = "code")
    private String code;
    
    @SuppressWarnings("unused")
    LanguageDTO() {
        
    }
    
    public LanguageDTO(Language lang) {
        this.id = lang.getId();
        this.code = lang.getCode();
    }
    
    public LanguageDTO(Integer id, String code) {
        this.id = id;
        this.code = code;
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
}
