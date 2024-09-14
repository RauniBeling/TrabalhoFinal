/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.prefs.Preferences;

import View.ConfiguracaoView;

/**
 *
 * @author ruanr
 */
public class LogManager {
    private static LogManager instance;
    private LogAdapter logAdapter;
    private Properties config;
    private static final String CSV_LOG_FILE = "system_log.csv";
    private static final String JSON_LOG_FILE = "system_log.json";
    private Preferences prefs;

    private LogManager() {
        config = new Properties();
        prefs = Preferences.userNodeForPackage(ConfiguracaoView.class);
        loadConfig();
        initializeLogAdapter();
    }

    public static synchronized LogManager getInstance() {
        if (instance == null) {
            instance = new LogManager();
        }
        return instance;
    }

    private void loadConfig() {
        String formatoSalvo = prefs.get("formatoLog", "CSV");
        config.setProperty("log_format", formatoSalvo);
    }

    private void saveConfig() {
        String format = config.getProperty("log_format", "CSV");
        prefs.put("formatoLog", format);
    }

    private void initializeLogAdapter() {
        String format = config.getProperty("log_format", "CSV");
        if (format.equalsIgnoreCase("JSON")) {
            LogFormat logFormat = new JSONLogFormat();
            logAdapter = new LogAdapterImpl(JSON_LOG_FILE, logFormat);
        } else {
            LogFormat logFormat = new CSVLogFormat();
            logAdapter = new LogAdapterImpl(CSV_LOG_FILE, logFormat);
        }
    }

    public void setLogFormat(String format) {
        config.setProperty("log_format", format.toUpperCase());
        saveConfig();
        initializeLogAdapter();
    }

    public void log(String operation, String target, String user) {
        logAdapter.writeLog(operation, target, user);
    }
}
