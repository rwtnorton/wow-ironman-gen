#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * Randomly generate a World of Warcraft race/class/gender combo for the
 * WoW Ironman Challenge.
 */

/*
human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
orc, troll, forsaken, tauren, blood_elf, goblin
No death knights.
warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest
*/

struct RaceClass {
	const char *race;
	const char *cls;
};
const struct RaceClass combos[] = {
	{ "human", "warrior" },
	{ "human", "paladin" },
	{ "human", "hunter" },
	{ "human", "rogue" },
	{ "human", "monk" },
	{ "human", "mage" },
	{ "human", "warlock" },
	{ "human", "priest" },
	{ "night_elf", "warrior" },
	{ "night_elf", "hunter" },
	{ "night_elf", "rogue" },
	{ "night_elf", "druid" },
	{ "night_elf", "monk" },
	{ "night_elf", "mage" },
	{ "night_elf", "priest" },
	{ "dwarf", "warrior" },
	{ "dwarf", "paladin" },
	{ "dwarf", "hunter" },
	{ "dwarf", "shaman" },
	{ "dwarf", "rogue" },
	{ "dwarf", "monk" },
	{ "dwarf", "mage" },
	{ "dwarf", "warlock" },
	{ "dwarf", "priest" },
	{ "gnome", "warrior" },
	{ "gnome", "rogue" },
	{ "gnome", "monk" },
	{ "gnome", "mage" },
	{ "gnome", "warlock" },
	{ "gnome", "priest" },
	{ "draenei", "warrior" },
	{ "draenei", "paladin" },
	{ "draenei", "hunter" },
	{ "draenei", "shaman" },
	{ "draenei", "monk" },
	{ "draenei", "mage" },
	{ "draenei", "priest" },
	{ "worgen", "warrior" },
	{ "worgen", "hunter" },
	{ "worgen", "druid" },
	{ "worgen", "rogue" },
	{ "worgen", "mage" },
	{ "worgen", "warlock" },
	{ "worgen", "priest" },
	{ "pandaren", "warrior" },
	{ "pandaren", "hunter" },
	{ "pandaren", "shaman" },
	{ "pandaren", "rogue" },
	{ "pandaren", "monk" },
	{ "pandaren", "mage" },
	{ "pandaren", "priest" },
	{ "orc", "warrior" },
	{ "orc", "hunter" },
	{ "orc", "shaman" },
	{ "orc", "rogue" },
	{ "orc", "monk" },
	{ "orc", "mage" },
	{ "orc", "warlock" },
	{ "troll", "warrior" },
	{ "troll", "hunter" },
	{ "troll", "shaman" },
	{ "troll", "druid" },
	{ "troll", "rogue" },
	{ "troll", "monk" },
	{ "troll", "mage" },
	{ "troll", "warlock" },
	{ "troll", "priest" },
	{ "forsaken", "warrior" },
	{ "forsaken", "hunter" },
	{ "forsaken", "rogue" },
	{ "forsaken", "monk" },
	{ "forsaken", "mage" },
	{ "forsaken", "warlock" },
	{ "forsaken", "priest" },
	{ "tauren", "warrior" },
	{ "tauren", "paladin" },
	{ "tauren", "hunter" },
	{ "tauren", "shaman" },
	{ "tauren", "druid" },
	{ "tauren", "monk" },
	{ "tauren", "priest" },
	{ "blood_elf", "warrior" },
	{ "blood_elf", "paladin" },
	{ "blood_elf", "hunter" },
	{ "blood_elf", "rogue" },
	{ "blood_elf", "monk" },
	{ "blood_elf", "mage" },
	{ "blood_elf", "warlock" },
	{ "blood_elf", "priest" },
	{ "goblin", "warrior" },
	{ "goblin", "hunter" },
	{ "goblin", "shaman" },
	{ "goblin", "rogue" },
	{ "goblin", "mage" },
	{ "goblin", "warlock" },
	{ "goblin", "priest" },
};
const size_t combos_len = sizeof(combos) / sizeof(combos[0]);

const char *genders[] = { "male", "female" };
size_t genders_len = sizeof(genders) / sizeof(genders[0]);

const char *factions[] = { "alliance", "horde" };
size_t factions_len = sizeof(factions) / sizeof(factions[0]);

void seed_srandom()
{
	FILE *f;
	int buf;
	f = fopen("/dev/random", "r");
	if (!f) {
		perror("Unable to open /dev/random");
		exit(1);
	}
	if (!fread(&buf, 4, 1, f)) {
		perror("Unable to read /dev/random");
		exit(1);
	}
	srandom(buf);
	if (fclose(f)) {
		perror("Unable to close /dev/random");
		exit(1);
	}
}

const char *sample(const char *choices[], size_t len)
{
	int which = random() % len;
	return choices[which];
}
const struct RaceClass race_class_sample(const struct RaceClass choices[],
					size_t len)
{
	int which = random() % len;
	return choices[which];
}

int main(void)
{
	struct RaceClass combo;
	const char *race, *cls, *gender;

	seed_srandom();

	combo = race_class_sample(combos, combos_len);
	gender = sample(genders, genders_len);

	if (strcmp(combo.race, "pandaren") == 0) {
		const char *faction = sample(factions, factions_len);
		printf("%s %s %s %s\n",
			gender, faction, combo.race, combo.cls);
	} else {
		printf("%s %s %s\n", gender, combo.race, combo.cls);
	}

	return 0;
}
