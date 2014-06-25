package containers;

import generics.Generator;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by wu on 2014/6/24.
 */

class Government implements Generator<String> {

    String[] foundation = ("strange women lying in ponds " +
            "distrbuting swords is no basis for a system of " +
            "government").split(" ");

    private int index = 0;

    @Override
    public String next() {
        return foundation[index++];
    }
}

public class CollectionDataTest {
    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<String>(new CollectionData<String>(new Government(), 15));
        set.addAll(CollectionData.list(new Government(), 15));
        System.out.println(set);
    }
}
