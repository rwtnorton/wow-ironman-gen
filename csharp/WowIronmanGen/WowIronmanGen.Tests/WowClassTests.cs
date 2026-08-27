namespace WowIronmanGen.Tests;

[TestClass]
public sealed class WowClassTests
{
    [TestMethod]
    public void TestToDisplayString()
    {
        Assert.AreEqual("warrior", WowClass.Warrior.ToDisplayString());
        Assert.AreEqual("mage", WowClass.Mage.ToDisplayString());
    }

    [TestMethod]
    public void TestSampler()
    {
        Random random = new(42);
        var got = EnumSampler<WowClass>.Sample(random);
        Assert.AreEqual(WowClass.Monk, got);
        Assert.AreEqual(WowClass.Paladin, EnumSampler<WowClass>.Sample(random));
        Assert.IsInstanceOfType<WowClass>(EnumSampler<WowClass>.Sample());
    }
}
