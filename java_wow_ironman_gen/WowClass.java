package java_wow_ironman_gen;

// No death knights.
// warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest
public enum WowClass {
    WARRIOR,
    PALADIN,
    HUNTER,
    SHAMAN,
    DRUID,
    ROGUE,
    MONK,
    MAGE,
    WARLOCK,
    PRIEST;

    public String toString() {
        return super.toString().toLowerCase();
    }
}
