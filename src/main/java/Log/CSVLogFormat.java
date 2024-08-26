package Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVLogFormat implements LogFormat {
    @Override
    public String formatLog(String operation, String name, String user) {
        String formattedLog = "\"<OPERACAO>: " + name + ", (" + getCurrentDate() + ", " + getCurrentTime() + ", " + user + ")\"";
        return formattedLog;
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(new Date());
    }

    private String getCurrentTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(new Date());
    }
}
