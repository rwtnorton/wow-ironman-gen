using WowIronmanGen;

var wowClass = EnumSampler<WowClass>.Sample();
Console.WriteLine($"class: {wowClass.ToClassString()}");
var race = EnumSampler<Race>.Sample();
Console.WriteLine($"race: {race.ToRaceString()}");
