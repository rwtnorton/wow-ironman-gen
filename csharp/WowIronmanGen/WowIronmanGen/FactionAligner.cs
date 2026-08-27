namespace WowIronmanGen;

public static class FactionAligner
{
    private static readonly Faction[] allianceOnly = [Faction.Alliance];
    private static readonly Faction[] hordeOnly = [Faction.Horde];
    private static readonly Faction[] eitherFaction = [Faction.Alliance, Faction.Horde];

    private static readonly Dictionary<Race, Faction[]> byRace = new()
    {
        [Race.Human] = allianceOnly,
        [Race.NightElf] = allianceOnly,
        [Race.Dwarf] = allianceOnly,
        [Race.Gnome] = allianceOnly,
        [Race.Draenei] = allianceOnly,
        [Race.Worgen] = allianceOnly,
        [Race.Pandaren] = eitherFaction,
        [Race.Orc] = hordeOnly,
        [Race.Troll] = hordeOnly,
        [Race.Forsaken] = hordeOnly,
        [Race.Tauren] = hordeOnly,
        [Race.BloodElf] = hordeOnly,
        [Race.Goblin] = hordeOnly
    };

    public static bool IsValid(Race race, Faction faction)
    {
        var allowed = byRace[race];
        return allowed.Contains(faction);
    }

    public static Faction SampleByRace(Race race, Random? random = null)
    {
        var factions = byRace[race];
        return factions.Length == 1
            ? factions[0]
            : factions[(random ?? new Random()).Next(factions.Length)];
    }
}
