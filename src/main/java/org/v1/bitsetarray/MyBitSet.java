package org.v1.bitsetarray;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class MyBitSet {
    private final int MAX_BITSET_SIZE = Integer.MAX_VALUE;
    private List<BitSet> bitSetList = new ArrayList<>();
    private long counter = 0;

        //TODO может надо создавать битсет по мере необходимости, в сете, если видим, что не умешается в текущий сет
    public MyBitSet(long longValue) {
        int i = (int) (longValue / MAX_BITSET_SIZE);               //получаем индекс битсета, в который надо положить число
        int intValue = (int) (longValue % MAX_BITSET_SIZE);       //получаем остаточный индекс (либо 0, если он умещается в текущий arr, либо другой
        int indexArray = i + (intValue == 0 ? 0 : 1);
        int currentValue = (int) (longValue - i * MAX_BITSET_SIZE);
        for (int j = 1; j <= indexArray; j++) {
            int sizeArray = (j == indexArray ? currentValue : MAX_BITSET_SIZE);
            bitSetList.add(new BitSet(sizeArray));
        }
    }

    public void set(long index) {
        int indexArray = (int) (index / MAX_BITSET_SIZE);
        int currentValue = (int) (index - indexArray * MAX_BITSET_SIZE);

        //v1
//        if (!bitSetList.get(indexArray).get(currentValue)) {
//            counter++;
//            bitSetList.get(indexArray).set(currentValue);
//        }

        //v2
        bitSetList.get(indexArray).set(currentValue);
    }

    public long countUniqueIps() {
        long l=bitSetList.stream().mapToLong(BitSet::cardinality).sum();
//        long l2=bitSetList.stream().mapToLong(BitSet::cardinality).reduce((acc, x) -> acc + x).getAsLong();
        return l;
        //return counter;
    }

    public long cardinality() {
        long sum = 0;
        for (int j = 0; j < bitSetList.size(); j++) {
            int i = bitSetList.get(j).cardinality();
            sum += i;
        }
        return sum;
    }
}
