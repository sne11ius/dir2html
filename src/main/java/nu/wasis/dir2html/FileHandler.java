package nu.wasis.dir2html;

import java.io.File;

public interface FileHandler {

    void start();

    void stop();

    void handleFile(File file);

    void handleDirectory(File directory);

    void beforeDirectory(File directory);

    void afterDirectory(File directory);

}
