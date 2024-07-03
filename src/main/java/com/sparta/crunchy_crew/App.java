package com.sparta.crunchy_crew;

import com.sparta.crunchy_crew.logger.CrunchyLogger;

import java.util.logging.Logger;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        CrunchyLogger.configure();
        
    }
}
