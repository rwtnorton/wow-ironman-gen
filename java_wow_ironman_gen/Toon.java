package java_wow_ironman_gen;

public class Toon {
    private Race race;
    private WowClass wowClass;
    private Gender gender;
    private Faction faction;

    public Toon(Race r, WowClass c, Gender g, Faction f) {
        race = r;
        wowClass = c;
        gender = g;
        faction = f;
    }

    public Toon() {
        this(null, null, null, null);
    }

    public Race getRace() { return race; }
    public WowClass  getWowClass() { return wowClass; }
    public Gender getGender() { return gender; }
    public Faction getFaction() { return faction; }

    public void setRace(Race r) { race = r; }
    public void setWowClass(WowClass c) { wowClass = c; }
    public void setGender(Gender g) { gender = g; }
    public void setFaction(Faction f) { faction = f; }
}
