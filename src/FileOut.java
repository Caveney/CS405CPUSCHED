import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileOut {

    private BufferedWriter writer;

    public void createFile(String filename) throws IOException {
        writer = new BufferedWriter(new FileWriter(filename));
    }
    //takes lines of data adds them to a file
    public void addLine(String data) throws IOException {
        writer.write(data);
        writer.newLine();
        writer.flush();
    }

    public void close() throws IOException {
        writer.close();
    }
}