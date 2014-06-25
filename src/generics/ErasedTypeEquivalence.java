package generics;

import java.util.ArrayList;

/**
 * Created by wu on 2014/6/24.
 */
public class ErasedTypeEquivalence {
    public static void main(String[] args) {
        Class c1 = new ArrayList<Integer>().getClass();
        Class c2 = new ArrayList<String>().getClass();

        if(c1 == c2)
            System.out.println("equal");
    }
}
