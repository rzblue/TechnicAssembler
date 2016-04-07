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
    /**
     * The calendar used for logging
     */
    private Calendar calendar = Calendar.getInstance();
    /**
     * The format used to write the timestamp
     */
    private SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    /**
     * Log to file?
     */
    private boolean logToFile;
    /**
     * The {@code BufferedWriter} used to write to the logfile
     */
    private BufferedWriter write;
    /**
     * The logfile
     */
    private File logFile;
    /**
     * The name that appears at the beginning of each log
     */
    private String className;
    /**
     * Basic {@code SimpleLogger} without frills
     */
    public SimpleLogger(){}
    /**
     * {@code SimpleLogger} with a nice looking name
     * @param name The name of the class to show at the beginning of each log entry
     */
    public SimpleLogger(String name) {
        className = name;
    }
    /**
     * {@code SimpleLogger} with a log file attached
     * @param parFile File to write the log to
     */
    public SimpleLogger(File parFile) {
        setupFile(parFile);
    }
    /**
     * {@code SimpleLogger} with both name and log file
     * @param name The name of the class to show at the beginning of each log entry
     * @param parFile File to write the log to
     */
    public SimpleLogger(String name, File parFile) {
        className = name;
        setupFile(parFile);
    }
    /**
     * Logs a message. Called by individual level log methods.
     * @param level Level to show in the log
     * @param message Message to log
     */
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
    /**
     * Logs a severe message
     * @param message Message to log
     */
    public void severe(String message) {
        log(SEVERE, message);
    }
    /**
     * Logs a warning message
     * @param message Message to log
     */
    public void warning(String message) {
        log(WARNING, message);
    }
    /**
     * Logs an informational message
     * @param message Message to log
     */
    public void info(String message) {
        log(INFO, message);
    }
    /**
     * Logs a config related message
     * @param message Message to log
     */
    public void config(String message) {
        log(CONFIG, message);
    }
    /**
     * Logs a "fine" message
     * @param message Message to log
     */
    public void fine(String message) {
        log(FINE, message);
    }
    /**
     * Logs a "finer" message
     * @param message Message to log
     */
    public void finer(String message) {
        log(FINER, message);
    }
    /**
     * Logs the "finest" method
     * @param message Message to log
     */
    public void finest(String message) {
        log(FINEST, message);
    }
    /**
     * Setup the logfile. Called when {@code logToFile} is true
     * @param parFile File to setup
     */
    private void setupFile(File parFile) {
        logFile = parFile;
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
        } catch (IOException ex) {}
        logToFile = true;
    }
    /**
     * Writes the beginning message to the logfile
     */
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
    /**
     * Writes the ending message to the logfile
     */
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