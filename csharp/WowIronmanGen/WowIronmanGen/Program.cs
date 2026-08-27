using WowIronmanGen;

var wowClass = EnumSampler<WowClass>.Sample();
Console.WriteLine($"class: {wowClass.ToClassString()}");
var race = EnumSampler<Race>.Sample();
Console.WriteLine($"race: {race.ToRaceString()}");
var gender = EnumSampler<Gender>.Sample();
Console.WriteLine($"gender: {gender.ToGenderString()}");
var faction = EnumSampler<Faction>.Sample();
Console.WriteLine($"faction: {faction.ToFactionString()}");
