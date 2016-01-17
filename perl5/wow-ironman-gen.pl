#!/usr/bin/env perl
use strict;
use warnings;

##
## Randomly generate a World of Warcraft race/class/gender combo for the
## WoW Ironman Challenge.
##

# human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
#   orc, troll, forsaken, tauren, blood_elf, goblin
# # No death knights.
# warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest

sub sample {
    my ($lst) = @_;
    return $lst->[ rand scalar @$lst ];
}

my %classes_for_race = (
    human => [qw[warrior paladin hunter rogue monk mage warlock priest]],
    night_elf => [qw[warrior hunter rogue druid monk mage priest]],
    dwarf => [qw[warrior paladin hunter shaman rogue monk mage warlock priest]],
    gnome => [qw[warrior rogue monk mage warlock priest]],
    draenei => [qw[warrior paladin hunter shaman monk mage priest]],
    worgen => [qw[warrior hunter druid rogue mage warlock priest]],
    pandaren => [qw[warrior hunter shaman rogue monk mage priest]],
    orc => [qw[warrior hunter shaman rogue monk mage warlock]],
    troll => [qw[warrior hunter shaman druid rogue monk mage warlock priest]],
    forsaken => [qw[warrior hunter rogue monk mage warlock priest]],
    tauren => [qw[warrior paladin hunter shaman druid monk priest]],
    blood_elf => [qw[warrior paladin hunter rogue monk mage warlock priest]],
    goblin => [qw[warrior hunter shaman rogue mage warlock priest]],
    );

my $race = sample([ keys %classes_for_race ]);
my $cls = sample( $classes_for_race{$race} );
my $gender = sample([ qw[male female] ]);
my $faction = $race eq 'pandaren' ? sample([ qw[alliance horde] ]) : undef;

my $result = join ' ', grep { $_ } $gender, $faction, $race, $cls;

print $result, "\n";
