package wow_ironman_gen;

public class RandomGenderChooser
    extends AbstractRandomChooser<Gender>
    implements RandomChooser<Gender> {

    public RandomGenderChooser() {
        super();
    }

    public Gender[] getChoices() {
        return Gender.values();
    }
}
