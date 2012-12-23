package java_wow_ironman_gen;

public class ToonPresenter {
    private Toon toon;

    public ToonPresenter(Toon t) {
        toon = t;
    }

    public Toon getToon() { return toon; }

    public String present() {
        if (toon.getRace() == Race.PANDAREN) {
            return toon.getGender() + " "
                + toon.getFaction() + " "
                + toon.getRace() + " "
                + toon.getWowClass();
        }
        return toon.getGender()
            + " " + toon.getRace()
            + " " + toon.getWowClass();
    }
}
