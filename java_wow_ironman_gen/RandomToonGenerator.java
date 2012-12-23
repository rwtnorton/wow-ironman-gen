package java_wow_ironman_gen;

public class RandomToonGenerator {
    public static Toon generate() {
        Toon result = new Toon();

        RandomRaceChooser raceChooser = new RandomRaceChooser();
        Race race = raceChooser.choose();
        result.setRace(race);

        RandomWowClassChooser classChooser = new RandomWowClassChooser(race);
        WowClass wowClass = classChooser.choose();
        result.setWowClass(wowClass);

        RandomGenderChooser genderChooser = new RandomGenderChooser();
        Gender gender = genderChooser.choose();
        result.setGender(gender);

        RandomFactionChooser factionChooser = new RandomFactionChooser();
        Faction faction = factionChooser.choose();
        result.setFaction(faction);

        return result;
    }
}
