using WowIronmanGen;

Random GetRandom()
{
    var seedStr = Environment.GetEnvironmentVariable("wowironmangen_seed") ?? "";
    return int.TryParse(seedStr, out var seed) ? new Random(seed) : new Random();
}

var random = GetRandom();

var wowClass = EnumSampler<WowClass>.Sample(random);
Console.WriteLine($"class: {wowClass.ToClassString()}");
var race = EnumSampler<Race>.Sample(random);
Console.WriteLine($"race: {race.ToRaceString()}");
var gender = EnumSampler<Gender>.Sample(random);
Console.WriteLine($"gender: {gender.ToGenderString()}");
var faction = EnumSampler<Faction>.Sample(random);
Console.WriteLine($"faction: {faction.ToFactionString()}");

var pandaFaction = FactionAligner.SampleByRace(Race.Pandaren, random);
Console.WriteLine($"pandaren faction 1: {pandaFaction.ToFactionString()}");
Console.WriteLine($"pandaren faction 2: {FactionAligner.SampleByRace(Race.Pandaren, random).ToFactionString()}");
Console.WriteLine($"pandaren faction 3: {FactionAligner.SampleByRace(Race.Pandaren, random).ToFactionString()}");

Console.WriteLine($"tauren class 1: {WowClassAligner.SampleByRace(Race.Tauren, random)}");
Console.WriteLine($"tauren class 2: {WowClassAligner.SampleByRace(Race.Tauren, random)}");
Console.WriteLine($"tauren class 3: {WowClassAligner.SampleByRace(Race.Tauren, random)}");
