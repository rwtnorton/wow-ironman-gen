namespace WowIronmanGen;

public static class WowClassAligner
{
    private static readonly Dictionary<Race, WowClass[]> byRace = new()
    {
        [Race.Human] =
        [
            WowClass.Warrior, WowClass.Paladin, WowClass.Hunter, WowClass.Rogue,
            WowClass.Monk, WowClass.Mage, WowClass.Warlock, WowClass.Priest
        ],
        [Race.NightElf] =
        [
            WowClass.Warrior, WowClass.Hunter, WowClass.Rogue, WowClass.Druid,
            WowClass.Monk, WowClass.Mage, WowClass.Priest
        ],
        [Race.Dwarf] =
        [
            WowClass.Warrior, WowClass.Paladin, WowClass.Hunter, WowClass.Shaman,
            WowClass.Rogue, WowClass.Monk, WowClass.Mage, WowClass.Warlock,
            WowClass.Priest
        ],
        [Race.Gnome] =
        [
            WowClass.Warrior, WowClass.Rogue, WowClass.Monk, WowClass.Mage,
            WowClass.Warlock, WowClass.Priest
        ],
        [Race.Draenei] =
        [
            WowClass.Warrior, WowClass.Paladin, WowClass.Hunter, WowClass.Shaman,
            WowClass.Monk, WowClass.Mage, WowClass.Priest
        ],
        [Race.Worgen] =
        [
            WowClass.Warrior, WowClass.Hunter, WowClass.Druid, WowClass.Rogue,
            WowClass.Mage, WowClass.Warlock, WowClass.Priest
        ],
        [Race.Pandaren] =
        [
            WowClass.Warrior, WowClass.Hunter, WowClass.Shaman, WowClass.Rogue,
            WowClass.Monk, WowClass.Mage, WowClass.Priest
        ],
        [Race.Orc] =
        [
            WowClass.Warrior, WowClass.Hunter, WowClass.Shaman, WowClass.Rogue,
            WowClass.Monk, WowClass.Mage, WowClass.Warlock
        ],
        [Race.Troll] =
        [
            WowClass.Warrior, WowClass.Hunter, WowClass.Shaman, WowClass.Druid,
            WowClass.Rogue, WowClass.Monk, WowClass.Mage, WowClass.Warlock,
            WowClass.Priest
        ],
        [Race.Forsaken] =
        [
            WowClass.Warrior, WowClass.Hunter, WowClass.Rogue, WowClass.Monk,
            WowClass.Mage, WowClass.Warlock, WowClass.Priest
        ],
        [Race.Tauren] =
        [
            WowClass.Warrior, WowClass.Paladin, WowClass.Hunter, WowClass.Shaman,
            WowClass.Druid, WowClass.Monk, WowClass.Priest
        ],
        [Race.BloodElf] =
        [
            WowClass.Warrior, WowClass.Paladin, WowClass.Hunter, WowClass.Rogue,
            WowClass.Monk, WowClass.Mage, WowClass.Warlock, WowClass.Priest
        ],
        [Race.Goblin] =
        [
            WowClass.Warrior, WowClass.Hunter, WowClass.Shaman, WowClass.Rogue,
            WowClass.Mage, WowClass.Warlock, WowClass.Priest
        ]
    };

    public static bool IsValid(Race race, WowClass wowClass)
    {
        var allowed = byRace[race];
        return allowed.Contains(wowClass);
    }

    public static WowClass SampleByRace(Race race, Random? random = null)
    {
        var classes = byRace[race];
        var index = (random ?? new Random()).Next(classes.Length);
        return classes[index];
    }
}
