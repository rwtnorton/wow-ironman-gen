/*
 * Randomly generate a World of Warcraft race/class/gender combo for the
 * WoW Ironman Challenge.
 */

package wow_ironman_gen;

/*
human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
orc, troll, forsaken, tauren, blood_elf, goblin
No death knights.
warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest
*/

public class Main {
    public static void main(String[] args) {
        Toon toon = RandomToonGenerator.generate();
        ToonPresenter presenter = new ToonPresenter(toon);
        System.out.println(presenter.present());
    }
}
