package com.mycompany.mytasklist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
/**
 *
 * @author marcin
 */
//adnotacja sluzaca do konfigurowania servletu
//urlPatterns - adresy url przekierowujace do tego servletu, tablica stringow
@WebServlet(name = "MojServlet", urlPatterns = {"/api/*"})
public class MyServlet extends HttpServlet {
    //zmienna dla nazwy parametru name
    private static final String NAME_PARAM = "name";
    //logger
    private final Logger logger = LoggerFactory.getLogger(HttpServlet.class);
    //obiekt serwisu, aby klasa spelniala signle responsibility principle
    //reprezentuje on warstwe biznesowa aplikacji
    private MyService service;
    
    //wymagany dla jetty'ego konstruktor domyslny servletu, on go sobie inicjalizuje, kiedy potrzebuje
    //tworzony, aby obslugiwal serwis
    @SuppressWarnings("unused") //Servlet container potrzebuje tej adnotacji
    public MyServlet(){
        this(new MyService());
    }
    
    //konstruktor
    MyServlet(MyService service) {
        this.service = service;
    }
    
    //przeciazenie metody doGet
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //uchwyt do zapytania i opdpowiedzi z serwera
        handleRequest(req, resp);
    }
    
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //odpowiedz serwera
        logger.info("Request got!");
 
        //pobieramy parametr name
        String parameterName = req.getParameter("name");
 
        //jezeli taki zostal podany to wypisujemy wiadomosc z imieniem, inaczej hello world
        //nasz service zwraca imie, badz domyslne slowo zamienne
        String greeting = service.greeting(parameterName);
        resp.getWriter().write(greeting);
 
    }
}
