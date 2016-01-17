package wow_ironman_gen;

public class RandomWowClassChooser
    extends AbstractRandomChooser<WowClass>
    implements RandomChooser<WowClass> {

    private Race race;

    public RandomWowClassChooser(Race r) {
        super();
        race = r;
    }

    public Race getRace() {
        return race;
    }

    public WowClass[] getChoices() {
        return race.getClassesAsArray();
    }
}
