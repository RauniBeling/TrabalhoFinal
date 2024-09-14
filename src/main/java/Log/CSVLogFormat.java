package Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVLogFormat implements LogFormat {
    @Override
    public String formatLog(String operation, String target, String user) {   
        String dateTime = getCurrentDateTime();
        String[] dateTimeParts = dateTime.split(";");
        return String.format("%s: <%s>, (%s, %s, e <%s>)", 
                             operation, target, dateTimeParts[0], dateTimeParts[1], user);
    }

    @Override
    public String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy;HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
