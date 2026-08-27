namespace WowIronmanGen.Tests;

[TestClass]
public class GenderTests
{
    [TestMethod]
    public void TestToDisplayString()
    {
        Assert.AreEqual("female", Gender.Female.ToDisplayString());
        Assert.AreEqual("male", Gender.Male.ToDisplayString());
    }

    [TestMethod]
    public void TestSampler()
    {
        Random random = new(42);
        Assert.AreEqual(Gender.Male, EnumSampler<Gender>.Sample(random));
        Assert.AreEqual(Gender.Female, EnumSampler<Gender>.Sample(random));
        Assert.IsInstanceOfType<Gender>(EnumSampler<Gender>.Sample());
    }
}
