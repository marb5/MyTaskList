package com.mycompany.mytasklist.language;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 *
 * @author marcin
 */
//adnotacja sluzaca do konfigurowania servletu
//urlPatterns - adresy url przekierowujace do tego servletu, tablica stringow
@WebServlet(name = "LanguageServlet", urlPatterns = {"/api/langs"})
//Servlet obslugujacy zapytania o jezyki
public class LanguageServlet extends HttpServlet {
    //logger
    private final Logger logger = LoggerFactory.getLogger(LanguageServlet.class);
    
    private LanguageRepository repository;
    //Obiekt, w ktorym zachodzi mapowanie danych na jsona
    private ObjectMapper mapper;
    
    //wymagany dla jetty'ego konstruktor domyslny servletu, on go sobie inicjalizuje, kiedy potrzebuje
    //tworzony, aby obslugiwal serwis
    @SuppressWarnings("unused") //Servlet container potrzebuje tej adnotacji
    public LanguageServlet(){
        this(new LanguageRepository(), new ObjectMapper());
    }
    
    //konstruktor
    LanguageServlet(LanguageRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    //przeciazenie metody doGet
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Request got! Sending languages...");
        //w naglowku podajemy typ wysylanych danych
        resp.setContentType("application/json;charset=UTF-8");
        //przy pomocy metody z mappera wysylamy dane z bazy jako response
        mapper.writeValue(resp.getOutputStream(), repository.findAll());
    }
    
}
