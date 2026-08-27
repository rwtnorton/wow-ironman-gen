namespace WowIronmanGen.Tests;

[TestClass]
public sealed class WowClassTests
{
    [TestMethod]
    public void TestToClassString()
    {
        Assert.AreEqual("warrior", WowClass.Warrior.ToClassString());
        Assert.AreEqual("mage", WowClass.Mage.ToClassString());
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
