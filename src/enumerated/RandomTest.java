package enumerated;

/**
 * Created by IORI on 2014/6/27.
 */

enum Activity { SITTING, LYING, STANDING, HOPPING, RUNNING, DODGING, JUMPING, FALLING, FLYING }

public class RandomTest {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++){
            System.out.println(Enums.random(Activity.class));
        }
    }
}
