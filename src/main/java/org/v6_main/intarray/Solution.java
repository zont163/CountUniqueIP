package org.v6_main.intarray;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Solution {
    /*
        Преобразовать в лонг
        Создать массив битов
        Лонгчисло в такой же индекс массива
        Посчитать кол-во 1

        Преобразовать в лонг
        Создать bitset
        Лонгчисло в такой же индекс массива
        Cardinality

        Преобразовать в лонг
        Использовать binarysearch

        Использовать дерево?
    */

    public static void main(String[] args) throws IOException {

        //TODO защита от мусорных строк
        //TODO параллельность потоков

        Logger logger = Logger.getLogger(Solution.class.getName());

        //String path = "D:\\YandexDisk\\EcwidtestfileIp\\50m";
        //String path = "D:\\ip_addresses";
        String path = args[0];

        var startTime = System.currentTimeMillis();
        MyFile myFile = new MyFile(path);
        MyArray myArray = new MyArray();
        try {
            BufferedReader bufferedReader = myFile.getReader();
            bufferedReader
                    .lines()
                    //.parallel()
                    .forEach(l -> {
                                try {
                                   //myArray.set(IP.toLong(l));
                                   myArray.set(IP.toLongValue(l));
                                } catch (Exception e) {
                                    logger.log(Level.WARNING, "something goes wrong, fileline is - {0}", l);
                                }
                            }
                    );
        } catch (IOException ioException) {
            logger.log(Level.WARNING, ioException.toString());
        }
        var uniq = myArray.getCounter();

        double fileSizeGb = (double) (myFile.getPathFile().toFile().length() / 1024) / 1024 / 1024;
        var executionTimeMin = (System.currentTimeMillis() - startTime) * 1.0 / 1000 / 60;

        var estimated = (executionTimeMin * (120 / fileSizeGb));
        logger.log(Level.INFO, String.format("End. File processed: unique ips - %d , execution time - %f min. Size - %f Gb, estimated time for 120Gb -  %f min", uniq, executionTimeMin, fileSizeGb, estimated));
    }
}
