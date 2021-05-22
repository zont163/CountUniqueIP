package org.v6_main.intarray;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class MyFile  {
    private final String path;
    private final Path pathFile;
    Logger logger;

    public MyFile(String path) {
        this.logger = Logger.getLogger(MyFile.class.getName());
        this.path = path;
        this.pathFile = Paths.get(this.path);
    }

    public BufferedReader getReader() throws IOException {
        if (!pathFile.toFile().isFile()) {
            throw new IOException(String.format("Specified file %s is not a file or does not exist.",path));
        }
        return Files.newBufferedReader(pathFile);
    }

    public Path getPathFile() {
        return pathFile;
    }
}
