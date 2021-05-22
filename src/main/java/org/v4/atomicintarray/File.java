package org.v4.atomicintarray;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class File {
    public void getReader(String path) throws IOException {
        Logger logger = Logger.getLogger(Solution.class.getName());
        Path pathFile = Paths.get(path);

        if (!pathFile.toFile().isFile()) {
            logger.log(Level.WARNING, String.format("Specified file %s is not a file or does not exist.",pathFile.toFile().toString()));
            System.exit(-1);
        }

    }
}
