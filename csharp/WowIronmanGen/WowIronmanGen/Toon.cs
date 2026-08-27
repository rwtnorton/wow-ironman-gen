namespace WowIronmanGen;

public record Toon(Race Race, WowClass WowClass, Gender Gender, Faction Faction)
{
    public bool IsValid()
    {
        return FactionAligner.IsAligned(Race, Faction) && WowClassAligner.IsAligned(Race, WowClass);
    }

    public static Toon Generate(Random? random = null)
    {
        var rand = random ?? new Random();
        var race = EnumSampler<Race>.Sample(rand);
        var gender = EnumSampler<Gender>.Sample(rand);
        var faction = FactionAligner.SampleByRace(race, rand);
        var wowClass = WowClassAligner.SampleByRace(race, rand);
        return new Toon(race, wowClass, gender, faction);
    }

    public string ToDisplayString()
    {
        string[] tokens = Race == Race.Pandaren
            ? [Gender.ToDisplayString(), Faction.ToDisplayString(), Race.ToDisplayString(), WowClass.ToDisplayString()]
            : [Gender.ToDisplayString(), Race.ToDisplayString(), WowClass.ToDisplayString()];
        return string.Join(' ', tokens);
    }
}
