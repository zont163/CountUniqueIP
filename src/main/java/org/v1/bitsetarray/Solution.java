package org.v1.bitsetarray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Solution {
    /*
    реобразовать в лонг
        Создать массив битов
        Лонгчисло в такой же индекс массива
        Посчитать кол-во 1

        Преобразовать в лонг
        Создать bitset
        Лонгчисло в такой же индекс массива
        Cardinality

        Преобразовать в лонг
        Использовать binarysearch
    */
    public static final Long MAX_SIZE = 4294967295L;

    public static void main(String[] args) throws IOException {
        //v1.Utils u = new v1.Utils();
        //u.getP();
        //TODO защита от мусорных строк
        //TODO параллельность потоков

        //String path = "D:\\YandexDisk\\EcwidtestfileIp\\50m";
        String path = "D:\\ip_addresses";
        Path pathFile = Paths.get(path);
        //Path pathFile = Paths.get(args[0]);

        if (!pathFile.toFile().isFile()) {
            System.out.println("Specified file " + pathFile.toFile().toString() + " is not a file or does not exist.");
            System.exit(-1);
        }
        var startTime = System.currentTimeMillis();

        MyBitSet myBitSet = new MyBitSet(MAX_SIZE);
        Files.newBufferedReader(pathFile)
                .lines()
                //.parallel()
                .forEach(l -> {
                            try {
                                IP ip = new IP(l);
                                myBitSet.set(ip.toLong());
                            } catch (Exception e) {
                                System.out.println(l + " is not v1.IP");
                            }
                        }
                );
        var uniq = myBitSet.countUniqueIps();
        //var uniq = myBitSet.cardinality();

        double fileSizeGb = (double) (pathFile.toFile().length() / 1024) / 1024 / 1024;
        var executionTimeMin = (System.currentTimeMillis() - startTime) * 1.0 / 1000 / 60;

        var estimated = (executionTimeMin * (120 / fileSizeGb));
        System.out.printf("End. file processed: %d unique ips, execution time - %f min. Size - %f Gb, estimated time for 120Gb -  %f min", uniq, executionTimeMin, fileSizeGb, estimated);


    }
}
