package java_wow_ironman_gen;

import java.util.Random;

public abstract class AbstractRandomChooser<T>
    implements RandomChooser<T> {

    private Random rand;

    public AbstractRandomChooser() {
        rand = new Random();
    }

    public abstract T[] getChoices();

    public T choose() {
        T[] choices = getChoices();
        int which = ModHelper.mod(rand.nextInt(), choices.length);
        return choices[which];
    }
}
