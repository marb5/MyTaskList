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
    //logger
    private final Logger logger = LoggerFactory.getLogger(HttpServlet.class);
    
    //przeciazenie metody doGet
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Request got!");
        resp.getWriter().write("Hello world! servlet");
    }
}
