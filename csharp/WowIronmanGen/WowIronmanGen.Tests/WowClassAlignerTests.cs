namespace WowIronmanGen.Tests;

[TestClass]
public class WowClassAlignerTests
{
    [TestMethod]
    public void IsValidTests()
    {
        Assert.IsTrue(WowClassAligner.IsValid(Race.Human, WowClass.Paladin));
        Assert.IsFalse(WowClassAligner.IsValid(Race.Human, WowClass.Druid));
    }

    [TestMethod]
    public void SampleByRaceTests()
    {
        Random random = new(42);
        Assert.AreEqual(WowClass.Monk, WowClassAligner.SampleByRace(Race.NightElf, random));
        Assert.AreEqual(WowClass.Warrior, WowClassAligner.SampleByRace(Race.Forsaken, random));
        Assert.IsInstanceOfType<WowClass>(WowClassAligner.SampleByRace(Race.Dwarf));
    }
}
