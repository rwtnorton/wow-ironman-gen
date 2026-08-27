namespace WowIronmanGen;

public enum WowClass
{
    Warrior,
    Paladin,
    Hunter,
    Shaman,
    Druid,
    Rogue,
    Monk,
    Mage,
    Warlock,
    Priest
}

public static class WowClassExtensions
{
    public static string ToDisplayString(this WowClass wowClass)
    {
        return wowClass.ToString().ToLower();
    }
}
