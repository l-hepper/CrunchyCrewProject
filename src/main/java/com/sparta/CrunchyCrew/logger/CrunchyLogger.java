package com.sparta.CrunchyCrew.logger;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CrunchyLogger {
    public static void configure() {
        Logger rootLogger = Logger.getLogger("");

        for (var handler : rootLogger.getHandlers()) {
            rootLogger.removeHandler(handler);
        }

        try {
            FileHandler fileHandler = new FileHandler("src/main/resources/app.log");
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            rootLogger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
