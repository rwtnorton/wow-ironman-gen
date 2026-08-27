namespace WowIronmanGen;

public enum Faction
{
    Alliance,
    Horde
}

public static class FactionExtensions
{
    public static string ToDisplayString(this Faction faction)
    {
        return faction.ToString().ToLower();
    }
}
