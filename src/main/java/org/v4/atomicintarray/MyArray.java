package org.v4.atomicintarray;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;

public class MyArray {
    private AtomicIntegerArray array = new AtomicIntegerArray(1 << 27);
    private AtomicLong counter = new AtomicLong(0);

    public void registerLongValue(long longValue) {
        if (longValue < 0) throw new IndexOutOfBoundsException(String.format("longValue < 0: %d", longValue));
        int index = toIndex(longValue);
        check(index);
        int currentValue = array.get(index);
        int bit = 1 << (longValue & 31);

        if ((currentValue & bit) == 0) {
            counter.incrementAndGet();
            array.set(index, currentValue | bit);
            //array.compareAndSet(index, currentValue, bit);
        }
    }

    public void set(long longValue) {
        registerLongValue(longValue);
    }

    private int toIndex(long longValue) {
        return (int) (longValue >> 5);
    }

    private void check(int wordIndex) {
        int wordsRequired = wordIndex + 1;
        if (array.length() < wordsRequired) {
            throw new IndexOutOfBoundsException(String.format("array capacity is not enough: words.length()=%d, wordsRequired=%d", array.length(), wordsRequired));
        }
    }

    public long getCounter() {
        return counter.get();
    }
}
