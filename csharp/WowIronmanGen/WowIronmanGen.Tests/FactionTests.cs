namespace WowIronmanGen.Tests;

[TestClass]
public class FactionTests
{
    [TestMethod]
    public void TestToDisplayString()
    {
        Assert.AreEqual("alliance", Faction.Alliance.ToDisplayString());
        Assert.AreEqual("horde", Faction.Horde.ToDisplayString());
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
