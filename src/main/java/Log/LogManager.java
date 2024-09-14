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

/**
 *
 * @author ruanr
 */
public class LogManager {
    private static LogManager instance;
    private LogAdapter logAdapter;
    private Properties config;
    private static final String CONFIG_FILE = "log_config.properties";
    private static final String CSV_LOG_FILE = "system_log.csv";
    private static final String JSON_LOG_FILE = "system_log.json";

    private LogManager() {
        config = new Properties();
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
        try (FileInputStream in = new FileInputStream(CONFIG_FILE)) {
            config.load(in);
        } catch (IOException e) {
            System.err.println("Erro ao carregar configuração: " + e.getMessage());
            config.setProperty("log_format", "CSV"); // Formato padrão
        }
    }

    private void saveConfig() {
        try (FileOutputStream out = new FileOutputStream(CONFIG_FILE)) {
            config.store(out, "Log Configuration");
        } catch (IOException e) {
            System.err.println("Erro ao salvar configuração: " + e.getMessage());
        }
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
