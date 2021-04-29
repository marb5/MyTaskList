package com.mycompany.mytasklist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //System.out.println("Hello World");
        //Dodanie loggera, sluzocego do wyswietlania danych
        Logger logger = LoggerFactory.getLogger(App.class);
        logger.info("Hello World message");
    }
}