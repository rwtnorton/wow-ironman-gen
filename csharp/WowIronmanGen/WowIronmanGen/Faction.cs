namespace WowIronmanGen;

public enum Faction
{
    Alliance,
    Horde
}

public static class FactionExtensions
{
    public static string ToFactionString(this Faction faction)
    {
        return faction.ToString().ToLower();
    }
}
