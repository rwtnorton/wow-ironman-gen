namespace WowIronmanGen;

public enum Race
{
    Human,
    NightElf,
    Dwarf,
    Gnome,
    Draenei,
    Worgen,
    Pandaren,
    Orc,
    Troll,
    Forsaken,
    Tauren,
    BloodElf,
    Goblin
}

public static class RaceExtensions
{
    public static string ToRaceString(this Race race)
    {
        return race switch
        {
            Race.Human => "human",
            Race.NightElf => "night_elf",
            Race.Dwarf => "dwarf",
            Race.Gnome => "gnome",
            Race.Draenei => "draenei",
            Race.Worgen => "worgen",
            Race.Pandaren => "pandaren",
            Race.Orc => "orc",
            Race.Troll => "troll",
            Race.Forsaken => "forsaken",
            Race.Tauren => "tauren",
            Race.BloodElf => "blood_elf",
            Race.Goblin => "goblin",
            _ => throw new ArgumentOutOfRangeException(nameof(race), race, null)
        };
    }
}
