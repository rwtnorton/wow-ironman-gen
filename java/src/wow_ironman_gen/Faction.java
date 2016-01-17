package wow_ironman_gen;

public enum Faction {
    HORDE, ALLIANCE;

    public String toString() {
        return super.toString().toLowerCase();
    }
}
