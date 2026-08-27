namespace WowIronmanGen.Tests;

[TestClass]
public class FactionAlignerTests
{
    [TestMethod]
    public void TestIsAligned()
    {
        Assert.IsTrue(FactionAligner.IsAligned(Race.Human, Faction.Alliance));
        Assert.IsFalse(FactionAligner.IsAligned(Race.Orc, Faction.Alliance));
        Assert.IsTrue(FactionAligner.IsAligned(Race.Orc, Faction.Horde));
        Assert.IsTrue(FactionAligner.IsAligned(Race.Pandaren, Faction.Alliance));
        Assert.IsTrue(FactionAligner.IsAligned(Race.Pandaren, Faction.Horde));
    }

    [TestMethod]
    public void TestSampleByRace()
    {
        Assert.AreEqual(Faction.Alliance, FactionAligner.SampleByRace(Race.Human));
        Assert.AreEqual(Faction.Horde, FactionAligner.SampleByRace(Race.Orc));
        Random random = new(42);
        Assert.IsInstanceOfType<Faction>(FactionAligner.SampleByRace(Race.Pandaren, random), "pandaren sample 1");
        Assert.IsInstanceOfType<Faction>(FactionAligner.SampleByRace(Race.Pandaren, random), "pandaren sample 2");
    }
}
