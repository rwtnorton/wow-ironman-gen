using WowIronmanGen;

var wowClass = EnumSampler<WowClass>.Sample();
Console.WriteLine($"class: {wowClass.ToClassString()}");
var race = EnumSampler<Race>.Sample();
Console.WriteLine($"race: {race.ToRaceString()}");
var gender = EnumSampler<Gender>.Sample();
Console.WriteLine($"gender: {gender.ToGenderString()}");
var faction = EnumSampler<Faction>.Sample();
Console.WriteLine($"faction: {faction.ToFactionString()}");

var pandaFaction = FactionAligner.SampleByRace(Race.Pandaren);
Console.WriteLine($"pandaren faction 1: {pandaFaction.ToFactionString()}");
Console.WriteLine($"pandaren faction 2: {FactionAligner.SampleByRace(Race.Pandaren).ToFactionString()}");
Console.WriteLine($"pandaren faction 3: {FactionAligner.SampleByRace(Race.Pandaren).ToFactionString()}");
