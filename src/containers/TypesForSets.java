package containers;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by IORI on 2014/6/25.
 */

class SetType {
    int i;
    public SetType(int n) { i = n; }
    public boolean equals(Object o) {
        return o instanceof SetType && (i == ((SetType)o).i);
    }

    @Override
    public String toString() {
        return Integer.toString(i);
    }
}

class HashType extends SetType {
    public HashType(int i) { super(i);}

    @Override
    public int hashCode() { return i;}
}

class TreeType extends SetType implements Comparable<TreeType> {

    public TreeType(int i) { super(i);}

    @Override
    public int compareTo(TreeType o) {
        return (o.i < i ? -1 : (o.i == i ? 0 : 1));
    }
}

public class TypesForSets {
    static <T> Set<T> fill(Set<T> set, Class<T> type) {
        try {
            for(int i = 0; i < 10; i++)
                set.add(type.getConstructor(int.class).newInstance(i));
        } catch (Exception e) {
//            throw new RuntimeException();
            System.out.println(e.getMessage());
        }

        return set;
    }

    static <T> void test(Set<T> set, Class<T> type) {
        fill(set, type);
        fill(set, type);
        fill(set, type);
        System.out.println(set);

    }

    public static void main(String[] args) {
//        test(new HashSet<HashType>(), HashType.class);
//        test(new LinkedHashSet<HashType>(), HashType.class);

        try {
            test(new TreeSet<SetType>(), SetType.class);
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        HashSet<String> tt = new HashSet<String>();

        tt.add("sfjdj");
        tt.add("nsfoenf");
        tt.add("neovn");

//        for(int i = 10; i > 0; i--)
//            tt.add(i);


        System.out.println(tt);
    }
}
