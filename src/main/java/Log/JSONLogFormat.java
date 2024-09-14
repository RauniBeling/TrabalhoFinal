package Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JSONLogFormat implements LogFormat {
   
    @Override
    public String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy;HH:mm:ss");
        return dateFormat.format(new Date());
    }

    @Override
    public String formatLog(String operation, String target, String user) {
        String dateTime = getCurrentDateTime();
        String[] dateTimeParts = dateTime.split(";");
        String formattedLog = String.format("{\"OPERACAO\": \"%s\", \"ALVO\": \"%s\", \"DATA\": \"%s\", \"HORA\": \"%s\", \"USUARIO\": \"%s\"}", 
                                            operation, target, dateTimeParts[0], dateTimeParts[1], user);
        return formattedLog;
    }
}
