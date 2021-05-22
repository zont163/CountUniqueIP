package org.v4.atomicintarray;

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
    public static void main(String[] args) throws IOException {

        //TODO защита от мусорных строк
        //TODO параллельность потоков

        String path = "D:\\YandexDisk\\EcwidtestfileIp\\50m";
        String path2 = "D:\\ip_addresses";

        Path pathFile = Paths.get(path2);
        //Path pathFile = Paths.get(args[0]);

        if (!pathFile.toFile().isFile()) {
            System.out.println("Specified file " + pathFile.toFile().toString() + " is not a file or does not exist.");
            System.exit(-1);
        }
        var startTime = System.currentTimeMillis();

        MyArray myArray = new MyArray();
        Files.newBufferedReader(pathFile)
                .lines()
                .parallel()
                .forEach(l -> {
                            try {
                                //IP ip = new IP(l);
                                myArray.set(IP.toLongValue(l));
                            } catch (Exception e) {
                                System.out.printf(e.toString()+"%n"+ l + " is not IP%n");
                            }
                        }
                );

        var timeBeforeCardinality=System.currentTimeMillis();
        var uniq = myArray.getCounter();
//        var uniq = myBitSet.cardinality();
        var execTimeCardinality=(System.currentTimeMillis() - timeBeforeCardinality) * 1.0 / 1000;

        double fileSizeGb = (double) (pathFile.toFile().length() / 1024) / 1024 / 1024;
        var executionTimeMin = (System.currentTimeMillis() - startTime) * 1.0 / 1000 / 60;

        var estimated = (executionTimeMin * (120 / fileSizeGb));
        System.out.printf("End. file processed: %d unique ips, execution time - %f min. Size - %f Gb, estimated time for 120Gb -  %f min", uniq, executionTimeMin, fileSizeGb, estimated);


    }
}
