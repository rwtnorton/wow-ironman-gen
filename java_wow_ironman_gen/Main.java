/*
 * Randomly generate a World of Warcraft race/class/gender combo for the
 * WoW Ironman Challenge.
 */

package java_wow_ironman_gen;

/*
human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
orc, troll, forsaken, tauren, blood_elf, goblin
No death knights.
warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest
*/

/*
  $ cd path/to/wow_ironman_gen/dir
  $ javac java_wow_ironman_gen/*.java
  $ java java_wow_ironman_gen.Main
*/

public class Main {
    public static void main(String[] args) {
        Toon toon = RandomToonGenerator.generate();
        ToonPresenter presenter = new ToonPresenter(toon);
        System.out.println(presenter.present());
    }
}
