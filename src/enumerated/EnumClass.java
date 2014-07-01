package enumerated;

/**
 * Created by IORI on 2014/6/26.
 */

enum Shrubbery { GROUND, CRAWLING, HANDLING }

public class EnumClass {

    public static void main(String[] args) {
        for (Shrubbery s : Shrubbery.values()) {
            System.out.println(s + "ordinal: " + s.ordinal());
            System.out.println(s == Shrubbery.CRAWLING);
            System.out.println(s.getDeclaringClass());
            System.out.println(s.name());
            System.out.println("------------------");
        }



        for (String s : "HANDLING CRAWLING GROUND".split(" ")) {
            Shrubbery shrub = Enum.valueOf(Shrubbery.class, s);
            System.out.println(shrub);
        }
    }
}