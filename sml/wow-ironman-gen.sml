(*
 * Randomly generate a World of Warcraft race/class/gender combo for the
 * WoW Ironman Challenge.
 *)

(*
   human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
     orc, troll, forsaken, tauren, blood_elf, goblin
   No death knights.
   warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest
*)

datatype Class = Warrior | Paladin | Hunter | Shaman
                 | Druid | Rogue | Mage | Monk
                 | Warlock | Priest
datatype Race = Human | NightElf | Dwarf | Gnome | Draenei | Worgen
                | Pandaren
                | Orc | Troll | Forsaken | Tauren | BloodElf | Goblin
datatype Gender = Female | Male
datatype Faction = Horde | Alliance

fun class_for_race(r) =
    case r of
        Human => [Warrior, Paladin, Hunter, Rogue, Monk, Mage, Warlock, Priest]
      | NightElf => [Warrior, Hunter, Rogue, Druid, Monk, Mage, Priest]
      | Dwarf => [Warrior, Paladin, Hunter, Shaman, Rogue, Monk, Mage, Warlock, Priest]
      | Gnome => [Warrior, Rogue, Monk, Mage, Warlock, Priest]
      | Draenei => [Warrior, Paladin, Hunter, Shaman, Monk, Mage, Priest]
      | Worgen => [Warrior, Hunter, Druid, Rogue, Mage, Warlock, Priest]
      | Pandaren => [Warrior, Hunter, Shaman, Rogue, Monk, Mage, Priest]
      | Orc => [Warrior, Hunter, Shaman, Rogue, Monk, Mage, Warlock]
      | Troll => [Warrior, Hunter, Shaman, Druid, Rogue, Monk, Mage, Warlock, Priest]
      | Forsaken => [Warrior, Hunter, Rogue, Monk, Mage, Warlock, Priest]
      | Tauren => [Warrior, Paladin, Hunter, Shaman, Druid, Monk, Priest]
      | BloodElf => [Warrior, Paladin, Hunter, Rogue, Monk, Mage, Warlock, Priest]
      | Goblin => [Warrior, Hunter, Shaman, Rogue, Mage, Warlock, Priest]

(*
 * Not having much luck getting reliable randomly generated numbers
 * (pretty esoteric, I know).
 * There is a now() function available after `open Time;` but
 * destructuring the `usec` is not working (something about TIME not being
 * a valid constructor).
 * If I had a decent seed, then using the builtin `Random.rand` function
 * would be no problem; that, coupled with `Random.randRange` (or the like)
 * should get us to the Glorious Magic of Sampling Sequences.
 *)

(*
sub sample {
    my ($lst) = @_;
    return $lst->[ rand scalar @$lst ];
}

my $race = sample([ keys %classes_for_race ]);
my $cls = sample( $classes_for_race{$race} );
my $gender = sample([ qw[male female] ]);
my $faction = $race eq 'pandaren' ? sample([ qw[alliance horde] ]) : undef;

my $result = join ' ', grep { $_ } $gender, $faction, $race, $cls;

print $result, "\n";
*)
