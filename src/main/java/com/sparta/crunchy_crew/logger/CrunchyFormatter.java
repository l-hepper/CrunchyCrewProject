package com.sparta.crunchy_crew.logger;

import com.sparta.crunchy_crew.Interface.ConsoleColours;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CrunchyFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                + " " + record.getSourceClassName()
                + " " + record.getLevel()
                + " " + record.getMessage()
                + "\n";
    }
}
