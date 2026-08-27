namespace WowIronmanGen.Tests;

[TestClass]
public class WowClassAlignerTests
{
    [TestMethod]
    public void TestIsAligned()
    {
        Assert.IsTrue(WowClassAligner.IsAligned(Race.Human, WowClass.Paladin));
        Assert.IsFalse(WowClassAligner.IsAligned(Race.Human, WowClass.Druid));
    }

    [TestMethod]
    public void TestSampleByRace()
    {
        Random random = new(42);
        Assert.AreEqual(WowClass.Monk, WowClassAligner.SampleByRace(Race.NightElf, random));
        Assert.AreEqual(WowClass.Warrior, WowClassAligner.SampleByRace(Race.Forsaken, random));
        Assert.IsInstanceOfType<WowClass>(WowClassAligner.SampleByRace(Race.Dwarf));
    }
}
