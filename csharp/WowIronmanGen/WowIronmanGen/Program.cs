using WowIronmanGen;

Random GetRandom()
{
    var seedStr = Environment.GetEnvironmentVariable("wowironmangen_seed") ?? "";
    return int.TryParse(seedStr, out var seed) ? new Random(seed) : new Random();
}

var random = GetRandom();

void ScratchPad()
{
    var wowClass = EnumSampler<WowClass>.Sample(random);
    Console.WriteLine($"class: {wowClass.ToDisplayString()}");
    var race = EnumSampler<Race>.Sample(random);
    Console.WriteLine($"race: {race.ToDisplayString()}");
    var gender = EnumSampler<Gender>.Sample(random);
    Console.WriteLine($"gender: {gender.ToDisplayString()}");
    var faction = EnumSampler<Faction>.Sample(random);
    Console.WriteLine($"faction: {faction.ToDisplayString()}");

    var pandaFaction = FactionAligner.SampleByRace(Race.Pandaren, random);
    Console.WriteLine($"pandaren faction 1: {pandaFaction.ToDisplayString()}");
    Console.WriteLine($"pandaren faction 2: {FactionAligner.SampleByRace(Race.Pandaren, random).ToDisplayString()}");
    Console.WriteLine($"pandaren faction 3: {FactionAligner.SampleByRace(Race.Pandaren, random).ToDisplayString()}");

    Console.WriteLine($"tauren class 1: {WowClassAligner.SampleByRace(Race.Tauren, random)}");
    Console.WriteLine($"tauren class 2: {WowClassAligner.SampleByRace(Race.Tauren, random)}");
    Console.WriteLine($"tauren class 3: {WowClassAligner.SampleByRace(Race.Tauren, random)}");

    var taurenShaman = new Toon(Race.Tauren, WowClass.Shaman, Gender.Female, Faction.Horde);
    Console.WriteLine($"tauren shaman: {taurenShaman.ToDisplayString()} :: valid? {taurenShaman.IsValid()}");
    var gnomeDruid = new Toon(Race.Gnome, WowClass.Druid, Gender.Female, Faction.Alliance);
    Console.WriteLine($"gnome druid: {gnomeDruid.ToDisplayString()} :: valid? {gnomeDruid.IsValid()}");

    Console.WriteLine($"generated toon 1: {Toon.Generate(random).ToDisplayString()}");
    Console.WriteLine($"generated toon 2: {Toon.Generate(random).ToDisplayString()}");
    Console.WriteLine($"generated toon 3: {Toon.Generate(random).ToDisplayString()}");
}

ScratchPad();
