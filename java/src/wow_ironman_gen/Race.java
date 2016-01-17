package wow_ironman_gen;

import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;

public enum Race {
    HUMAN (Arrays.asList(
        WowClass.WARRIOR, WowClass.PALADIN, WowClass.HUNTER, WowClass.ROGUE,
        WowClass.MONK, WowClass.MAGE, WowClass.WARLOCK, WowClass.PRIEST)),
    NIGHT_ELF (Arrays.asList(
        WowClass.WARRIOR, WowClass.HUNTER, WowClass.ROGUE, WowClass.DRUID,
        WowClass.MONK, WowClass.MAGE, WowClass.PRIEST)),
    DWARF (Arrays.asList(
        WowClass.WARRIOR, WowClass.PALADIN, WowClass.HUNTER, WowClass.SHAMAN,
        WowClass.ROGUE, WowClass.MONK, WowClass.MAGE, WowClass.WARLOCK,
        WowClass.PRIEST)),
    GNOME (Arrays.asList(
        WowClass.WARRIOR, WowClass.ROGUE, WowClass.MONK, WowClass.MAGE,
        WowClass.WARLOCK, WowClass.PRIEST)),
    DRAENEI (Arrays.asList(
        WowClass.WARRIOR, WowClass.PALADIN, WowClass.HUNTER, WowClass.SHAMAN,
        WowClass.MONK, WowClass.MAGE, WowClass.PRIEST)),
    WORGEN (Arrays.asList(
        WowClass.WARRIOR, WowClass.HUNTER, WowClass.DRUID, WowClass.ROGUE,
        WowClass.MAGE, WowClass.WARLOCK, WowClass.PRIEST)),
    PANDAREN (Arrays.asList(
        WowClass.WARRIOR, WowClass.HUNTER, WowClass.SHAMAN, WowClass.ROGUE,
        WowClass.MONK, WowClass.MAGE, WowClass.PRIEST)),
    ORC (Arrays.asList(
        WowClass.WARRIOR, WowClass.HUNTER, WowClass.SHAMAN, WowClass.ROGUE,
        WowClass.MONK, WowClass.MAGE, WowClass.WARLOCK)),
    TROLL (Arrays.asList(
        WowClass.WARRIOR, WowClass.HUNTER, WowClass.SHAMAN, WowClass.DRUID,
        WowClass.ROGUE, WowClass.MONK, WowClass.MAGE, WowClass.WARLOCK,
        WowClass.PRIEST)),
    FORSAKEN (Arrays.asList(
        WowClass.WARRIOR, WowClass.HUNTER, WowClass.ROGUE, WowClass.MONK,
        WowClass.MAGE, WowClass.WARLOCK, WowClass.PRIEST)),
    TAUREN (Arrays.asList(
        WowClass.WARRIOR, WowClass.PALADIN, WowClass.HUNTER, WowClass.SHAMAN,
        WowClass.DRUID, WowClass.MONK, WowClass.PRIEST)),
    BLOOD_ELF (Arrays.asList(
        WowClass.WARRIOR, WowClass.PALADIN, WowClass.HUNTER, WowClass.ROGUE,
        WowClass.MONK, WowClass.MAGE, WowClass.WARLOCK, WowClass.PRIEST)),
    GOBLIN (Arrays.asList(
        WowClass.WARRIOR, WowClass.HUNTER, WowClass.SHAMAN, WowClass.ROGUE,
        WowClass.MAGE, WowClass.WARLOCK, WowClass.PRIEST));

    private HashSet<WowClass> classes;

    Race(Collection<WowClass> cls) {
        classes = new HashSet<WowClass>();
        classes.addAll(cls);
    }

    public Collection<WowClass> getClasses() {
        return classes;
    }

    public WowClass[] getClassesAsArray() {
        return getClasses().toArray(new WowClass[] {});
    }

    public String toString() {
        return super.toString().toLowerCase();
    }

    public String toStringWithClasses() {
        return toString() + ": " + getClasses();
    }


}
