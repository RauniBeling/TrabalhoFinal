package Log;

public interface LogFormat {
    String formatLog(String operation, String target, String user);
    String getCurrentDateTime();
}
