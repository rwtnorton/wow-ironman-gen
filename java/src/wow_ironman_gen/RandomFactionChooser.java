package wow_ironman_gen;

public class RandomFactionChooser
    extends AbstractRandomChooser<Faction>
    implements RandomChooser<Faction> {

    public RandomFactionChooser() {
        super();
    }

    public Faction[] getChoices() {
        return Faction.values();
    }
}
