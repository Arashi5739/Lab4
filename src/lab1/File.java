package lab1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class File {
    InputStream fs = null;
    File() { }
    File(final String filename) throws FileNotFoundException {
        fs = new FileInputStream(filename);
    }
    void start(final String filename) throws FileNotFoundException {
        fs = new FileInputStream(filename);
    }
    void end() throws IOException {
        fs.close();
    }
    String getsingalword() throws IOException {
        StringBuilder re = new StringBuilder();
        int c;
        while ((c = fs.read()) != -1) {
            if (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
                break;
            }
        }
        if (c == -1) {
            return null;
        }
        re.append((char) c);
        while ((c = fs.read()) != -1) {
            if (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
                re.append((char) c);
            } else {
                break;
            }
        }
        return re.toString().toLowerCase();
    }
}
