namespace WowIronmanGen.Tests;

[TestClass]
public sealed class RaceTests
{
    [TestMethod]
    public void TestToDisplayString()
    {
        Assert.AreEqual("human", Race.Human.ToDisplayString());
        Assert.AreEqual("night_elf", Race.NightElf.ToDisplayString());
        Assert.AreEqual("blood_elf", Race.BloodElf.ToDisplayString());
    }

    [TestMethod]
    public void TestSampler()
    {
        Random random = new(42);
        Assert.AreEqual(Race.Troll, EnumSampler<Race>.Sample(random));
        Assert.AreEqual(Race.NightElf, EnumSampler<Race>.Sample(random));
        Assert.IsInstanceOfType<Race>(EnumSampler<Race>.Sample());
    }
}
