package com.github.firecrafty.technicassembler.logging;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.github.firecrafty.technicassembler.logging.Level.*;

/**
 * @author firecrafty
 */
public class SimpleLogger {
    /**
     * The format used to write the timestamp
     */
    private final SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.ENGLISH);
    /**
     * Log to file?
     *
     * @deprecated
     */
    private boolean logToFile;
    /**
     * The {@code BufferedWriter} used to write to the logfile
     */
    /**
     * The logfile
     */
    private File logFile;
    /**
     * The name that appears at the beginning of each log
     */
    private String className;

    /**
     * {@code SimpleLogger} with both name and log file
     *
     * @param name    The name of the class to show at the beginning of each log
     *                entry
     * @param parFile File to write the log to
     */

    public SimpleLogger(String name, File parFile) {
        className = name;
        logFile = parFile;
        setupLogger();
    }

    private void setupLogger() {
        File output = new File(this.logFile.getName() + ".log");
        try {
            System.out.println("Setting up logger: " + output.getAbsolutePath());
            OutputStream fout = new BufferedOutputStream(new FileOutputStream(output));
            System.setOut(new PrintStream(new MultiOutputStream(System.out, fout), true));
            System.setErr(new PrintStream(new MultiOutputStream(System.err, fout), true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //We errored out, lets just continue on and hope the rest runs fine.
        }
    }

    /**
     * Logs a severe message
     *
     * @param message Message to log
     */
    public void severe(String message) {
        log(SEVERE, message);
    }

    /**
     * Logs a message. Called by individual level log methods.
     *
     * @param level   Level to show in the log
     * @param message Message to log
     */
    private void log(Level level, String message) {
        String output = format.format(Calendar.getInstance().getTime()) + " " + className + " [" + level.toString() + "] " + message;
        if (level == SEVERE || level == WARNING) {
            System.err.println(output);
        } else {
            System.out.println(output);
        }
    }

    /**
     * Logs a warning message
     *
     * @param message Message to log
     */
    public void warning(String message) {
        log(WARNING, message);
    }

    /**
     * Logs an informational message
     *
     * @param message Message to log
     */
    public void info(String message) {
        log(INFO, message);
    }

    /**
     * Logs a config related message
     *
     * @param message Message to log
     */
    public void config(String message) {
        log(CONFIG, message);
    }

    /**
     * Logs a "fine" message
     *
     * @param message Message to log
     */
    public void fine(String message) {
        log(FINE, message);
    }

    /**
     * Logs a "finer" message
     *
     * @param message Message to log
     */
    public void finer(String message) {
        log(FINER, message);
    }

    /**
     * Setup the logfile. Called when {@code logToFile} is true
     *
     * @param parFile File to setup
     */

    /**
     * Logs the "finest" message
     *
     * @param message Message to log
     */
    public void finest(String message) {
        log(FINEST, message);
    }

    /**
     * Writes the beginning message to the logfile
     */
    public void startLog() {
        System.out.println("STARTING LOG FOR " + className.toUpperCase() + " ON " + Calendar.getInstance().getTime());
    }

    /**
     * Writes the ending message to the logfile
     *
     * @deprecated
     */
    public void stopLog() {
        System.out.println("STOPPING LOG FOR " + className.toUpperCase());
    }

    public void error(Exception ex) {
        System.err.println("Having issues? You really shouldn't be seeing this message, but if you do, submit an issue on GitHub. Copy and paste this log entry into the issue");
        System.err.println("================");
        System.err.println("START STACKTRACE");
        System.err.println("================");
        ex.printStackTrace();
        System.err.println("================");
        System.err.println(" END STACKTRACE ");
        System.err.println("================");
    }
}