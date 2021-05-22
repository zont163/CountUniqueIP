package org.v1.bitsetarray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    public void getP() throws IOException {
        Logger logger = Logger.getLogger(Solution.class.getName());
        String path = "D:\\ip_addresses";
        Path pathFile = Paths.get(path);

        String pathWrite = "D:\\ip_addresses2";
        Path pathFileWrite = Paths.get(path);

        //BufferedWriter bWriter = Files.newBufferedWriter(pathFileWrite);

        Files.newBufferedReader(pathFile)
                .lines()
                //.parallel()
                .limit(5000)
                .forEach(x -> logger.log(Level.INFO, x)
                );

    }
}
