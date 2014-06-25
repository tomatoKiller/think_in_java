package containers;

/**
 * Created by IORI on 2014/6/25.
 */
public class Groundhog {

    protected int number;
    public Groundhog(int n) { number = n;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Groundhog groundhog = (Groundhog) o;

        if (number != groundhog.number) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return number;
    }

    public String toString() {
        return "Groundhog #" + number;
    }
}
