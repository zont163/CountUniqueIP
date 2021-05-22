package org.v6_main.intarray;

public class MyArray {
    private int array[]  =  new int[1 << 27];
    private long counter = 0;
//* приводим к 5 байтам, потому что этого достаточнодля хранения числа 255_255_255_255
    private void registerLongValue(long longValue) {
        int index = (int) (longValue >> 5);
        int bit = 1 << (longValue & 31);
        if ((array[index] & bit) == 0) {
            counter++;
            array[index] |= bit;
        }
    }
    private void check(int wordIndex) {
        int wordsRequired = wordIndex + 1;
        if (array.length < wordsRequired) {
            throw new IndexOutOfBoundsException(String.format("array capacity is not enough: words.length()=%d, wordsRequired=%d", array.length, wordsRequired));
        }
    }
    public void set(long longValue){
        registerLongValue(longValue);
    }
    public long getCounter() {
        return this.counter;
    }
}
