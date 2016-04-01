package com.github.firecrafty.technicassembler.logging;

import static com.github.firecrafty.technicassembler.logging.Level.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 *
 * @author firecrafty
 */
public class SimpleLogger {
    
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    boolean logToFile = false;
    BufferedWriter write;
    File logFile;
    String className;
    Level logLevel = INFO;
    public SimpleLogger(){}
    public SimpleLogger(String name) {
        className = name;
    }
    public SimpleLogger(File parFile) {
        setupFile(parFile);
    }
    public SimpleLogger(String name, File parFile) {
        className = name;
        setupFile(parFile);
    }
    private void log(Level level, String message) {
        String output = format.format(calendar.getTime()) + " " +  className + " [" + level.toString() + "] " + message;
        if(level == SEVERE || level == WARNING) {
            System.err.println(output);
        } else {
            System.out.println(output);
        }
        
        if (logToFile) {
            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(logFile, true)))) {
                out.println(output);
                out.close();
            } catch (IOException ex) {}
        }
    }
    public void severe(String message) {
        log(SEVERE, message);
    }
    public void warning(String message) {
        log(WARNING, message);
    }
    public void info(String message) {
        log(INFO, message);
    }
    public void config(String message) {
        log(CONFIG, message);
    }
    public void fine(String message) {
        log(FINE, message);
    }
    public void finer(String message) {
        log(FINER, message);
    }
    public void finest(String message) {
        log(FINEST, message);
    }
    private void setupFile(File parFile) {
        logFile = parFile;
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
        } catch (IOException ex) {}
        logToFile = true;
    }
    public void startLog() {
        if (logToFile) {
            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(logFile, true)))) {
                out.println("========================================");
                out.println("STARTING LOG FOR " + className.toUpperCase() + " ON " + calendar.getTime());
                out.println("========================================");
                out.close();
            } catch (IOException ex) {}
        }
    }
    public void stopLog() {
        if (logToFile) {
            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(logFile, true)))) {
                out.println("========================================");
                out.println("STOPPING LOG FOR " + className.toUpperCase());
                out.println("========================================");
                out.close();
            } catch (IOException ex) {}
        }
    }
    
}