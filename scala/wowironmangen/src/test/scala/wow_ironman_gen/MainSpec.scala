package wow_ironman_gen

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MainSpec extends AnyFlatSpec with Matchers {
  "A non-Pandaren Toon" should "toString with no faction" in {
    val toon = Toon(Race.orc, Cls.hunter, Gender.female, Faction.horde)
    toon.toString shouldEqual "female orc hunter"
  }
  "A Pandaren Toon" should "toString with faction" in {
    val toon = Toon(Race.pandaren, Cls.monk, Gender.male, Faction.alliance)
    toon.toString shouldEqual "male alliance pandaren monk"
  }
}
