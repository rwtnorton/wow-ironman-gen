namespace WowIronmanGen.Tests

open System
open Microsoft.VisualStudio.TestTools.UnitTesting
open Domain

[<TestClass>]
type ToonTests() =

    [<TestMethod>]
    member this.TestInvalidToon() =
        let toon: Toon =
            {
                Race = Tauren
                WowClass = Hunter
                Faction = Alliance
                Gender = Male
            }

        Assert.IsFalse(toon.isValid ())

    [<TestMethod>]
    member this.TestIsValidToon() =
        let toon: Toon =
            {
                Race = Tauren
                WowClass = Hunter
                Faction = Horde
                Gender = Male
            }

        Assert.IsTrue(toon.isValid ())

    [<TestMethod>]
    member this.TestPandarenToString() =
        let toon: Toon =
            {
                Race = Pandaren
                WowClass = Shaman
                Faction = Alliance
                Gender = Female
            }

        Assert.AreEqual("female alliance pandaren shaman", toon.ToString())

    [<TestMethod>]
    member this.TestNonPandarenToString() =
        let toon: Toon =
            {
                Race = Forsaken
                WowClass = Rogue
                Faction = Horde
                Gender = Female
            }

        Assert.AreEqual("female forsaken rogue", toon.ToString())

    [<TestMethod>]
    member this.TestGeneratedToonIsValid() =
        let rand = System.Random()
        let toon = Toon.Generate rand
        Assert.IsTrue(toon.isValid (), $"generated toon is valid: {toon}")
