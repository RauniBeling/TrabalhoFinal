package Log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogAdapterImpl implements LogAdapter {
    private String logFilePath;

    public LogAdapterImpl(String logFilePath) {
        this.logFilePath = logFilePath;
    }
    @Override
    public void writeLog(String log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
            writer.println(log);
        } catch (IOException e) {
            System.out.println("Ocorreu uma falha ao escrever o log: " + e.getMessage());
        }
    }
//    @Override
//    public void writeLog(String log) {
//        try {
//            FileWriter writer = new FileWriter(logFilePath, true);
//            writer.write(log + "\n");
//            writer.close();
//        } catch (IOException e) {
//            System.err.println("Ocorreu um erro ao escrever o log no arquivo.");
//        }
//    }
}
