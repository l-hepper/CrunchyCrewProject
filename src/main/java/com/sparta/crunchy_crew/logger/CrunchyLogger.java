package com.sparta.crunchy_crew.logger;

import java.io.IOException;
import java.util.logging.*;

public class CrunchyLogger {

    public static void configure() {
        Logger rootLogger = Logger.getLogger("");

        for (var handler : rootLogger.getHandlers()) {
            rootLogger.removeHandler(handler);
        }

        try {
            setupLog("src/main/resources/warning.log", Level.WARNING, rootLogger);
            setupLog("src/main/resources/detailed.log", Level.ALL, rootLogger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setupLog(String pattern, Level all, Logger rootLogger) throws IOException {
        FileHandler logHandler = new FileHandler(pattern);
        logHandler.setLevel(all);
        logHandler.setFormatter(new CrunchyFormatter());
        rootLogger.addHandler(logHandler);
    }
}
