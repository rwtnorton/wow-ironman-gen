using WowIronmanGen;

var wowClass = EnumSampler<WowClass>.Sample();
Console.WriteLine($"class: {wowClass.ToClassString()}");
