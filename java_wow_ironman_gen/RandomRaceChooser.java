package java_wow_ironman_gen;

public class RandomRaceChooser
    extends AbstractRandomChooser<Race>
    implements RandomChooser<Race> {

    public RandomRaceChooser() {
        super();
    }

    public Race[] getChoices() {
        return Race.values();
    }
}
