package java_wow_ironman_gen;

public interface RandomChooser<T> {
    T[] getChoices();
    T choose();
}
