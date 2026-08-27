namespace WowIronmanGen.Tests;

[TestClass]
public class ToonTests
{
    [TestMethod]
    public void TestIsValid()
    {
        // Incremental changes
        Assert.IsTrue(new Toon(Race.Human, WowClass.Paladin, Gender.Female, Faction.Alliance).IsValid());
        Assert.IsTrue(new Toon(Race.Human, WowClass.Paladin, Gender.Male, Faction.Alliance).IsValid());
        Assert.IsTrue(new Toon(Race.Human, WowClass.Mage, Gender.Female, Faction.Alliance).IsValid());
        Assert.IsTrue(new Toon(Race.Dwarf, WowClass.Paladin, Gender.Female, Faction.Alliance).IsValid());
        Assert.IsFalse(new Toon(Race.Human, WowClass.Paladin, Gender.Female, Faction.Horde).IsValid());
        Assert.IsFalse(new Toon(Race.Human, WowClass.Shaman, Gender.Female, Faction.Alliance).IsValid());
        Assert.IsFalse(new Toon(Race.Worgen, WowClass.Paladin, Gender.Female, Faction.Alliance).IsValid());
        // Pandaren can be Alliance or Horde
        Assert.IsTrue(new Toon(Race.Pandaren, WowClass.Monk, Gender.Female, Faction.Alliance).IsValid());
        Assert.IsTrue(new Toon(Race.Pandaren, WowClass.Monk, Gender.Female, Faction.Horde).IsValid());
    }

    [TestMethod]
    public void TestIsDisplayString()
    {
        // Non-Pandarens should not bother with faction info.
        var orcWarrior = new Toon(Race.Orc, WowClass.Warrior, Gender.Female, Faction.Horde);
        Assert.AreEqual("female orc warrior", orcWarrior.ToDisplayString());
        var humanMage = new Toon(Race.Human, WowClass.Mage, Gender.Female, Faction.Alliance);
        Assert.AreEqual("female human mage", humanMage.ToDisplayString());
        // But Pandarens must include the faction.
        var alliancePanda = new Toon(Race.Pandaren, WowClass.Shaman, Gender.Female, Faction.Alliance);
        Assert.AreEqual("female alliance pandaren shaman", alliancePanda.ToDisplayString());
        var hordePanda = new Toon(Race.Pandaren, WowClass.Shaman, Gender.Female, Faction.Horde);
        Assert.AreEqual("female horde pandaren shaman", hordePanda.ToDisplayString());
        // Elves have specially formatted race display names.
        var nightElf = new Toon(Race.NightElf, WowClass.Druid, Gender.Female, Faction.Alliance);
        Assert.AreEqual("female night_elf druid", nightElf.ToDisplayString());
        var bloodElf = new Toon(Race.BloodElf, WowClass.Hunter, Gender.Female, Faction.Horde);
        Assert.AreEqual("female blood_elf hunter", bloodElf.ToDisplayString());
    }

    [TestMethod]
    public void TestGenerate()
    {
        Random random = new(42);
        var got = Toon.Generate(random);
        Assert.IsInstanceOfType<Toon>(got);
        Assert.AreEqual(Race.Troll, got.Race);
        Assert.AreEqual(WowClass.Hunter, got.WowClass);
        Assert.AreEqual(Faction.Horde, got.Faction);
        Assert.AreEqual(Gender.Female, got.Gender);
    }
}
