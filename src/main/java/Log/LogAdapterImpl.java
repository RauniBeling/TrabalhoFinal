package Log;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class LogAdapterImpl implements LogAdapter {
    private String logFilePath;
    private LogFormat logFormat;

    public LogAdapterImpl(String logFilePath, LogFormat logFormat) {
        this.logFilePath = logFilePath;
        this.logFormat = logFormat;
    }

    public void setLogFormat(LogFormat logFormat) {
        this.logFormat = logFormat;
    }

    @Override
    public void writeLog(String operation, String target, String user) {
        String formattedLog = logFormat.formatLog(operation, target, user);
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(logFilePath, true), "UTF-8"))) {
            writer.println(formattedLog);
        } catch (IOException e) {
            String errorMessage = "Ocorreu a falha " + e.getMessage() + " ao realizar a \"" + operation + "\".";
            System.err.println(errorMessage);
        }
    }
}
