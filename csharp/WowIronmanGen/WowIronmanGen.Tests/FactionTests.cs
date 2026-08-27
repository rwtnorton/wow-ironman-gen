namespace WowIronmanGen.Tests;

[TestClass]
public class FactionTests
{
    [TestMethod]
    public void TestToFactionString()
    {
        Assert.AreEqual("alliance", Faction.Alliance.ToFactionString());
        Assert.AreEqual("horde", Faction.Horde.ToFactionString());
    }

    [TestMethod]
    public void TestSampler()
    {
        Random random = new(42);
        Assert.AreEqual(Faction.Horde, EnumSampler<Faction>.Sample(random));
        Assert.AreEqual(Faction.Alliance, EnumSampler<Faction>.Sample(random));
        Assert.IsInstanceOfType<Faction>(EnumSampler<Faction>.Sample());
    }
}
