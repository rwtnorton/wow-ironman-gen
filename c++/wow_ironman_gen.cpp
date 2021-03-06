#include <iostream>
#include <unordered_map>
#include <vector>
#include <random>
#include <string>

namespace gender {
  enum class Gender { female, male, };
  const std::vector<Gender> genders = { Gender::female, Gender::male };
  const std::string to_string(const Gender& g)
  {
    switch (g) {
    case Gender::female: return "female";
    case Gender::male:   return "male";
    default:             return "dunno";
    }
  }
  std::ostream& operator<<(std::ostream& o, const Gender& g) {
    return o << to_string(g);
  }
};

namespace faction {
  enum class Faction { alliance, horde, };
  const std::vector<Faction> factions = { Faction::alliance, Faction::horde };
  const std::string to_string(const Faction &f)
  {
    switch (f) {
    case Faction::alliance: return "alliance";
    case Faction::horde:    return "horde";
    default:                return "dunno";
    }
  }
  std::ostream& operator<<(std::ostream& o, const Faction& f)
  {
    return o << to_string(f);
  }
};

namespace race {
  enum class Race {
    human, night_elf, dwarf, gnome, draenei, worgen,
    pandaren,
    orc, troll, forsaken, tauren, blood_elf, goblin,
  };
  const std::vector<Race> races = {
    Race::human,
    Race::night_elf,
    Race::dwarf,
    Race::gnome,
    Race::draenei,
    Race::worgen,
    Race::pandaren,
    Race::orc,
    Race::troll,
    Race::forsaken,
    Race::tauren,
    Race::blood_elf,
    Race::goblin,
  };
  const std::string to_string(const Race &r)
  {
    switch (r) {
    case Race::human:       return "human";
    case Race::night_elf:   return "night_elf";
    case Race::dwarf:       return "dwarf";
    case Race::gnome:       return "gnome";
    case Race::draenei:     return "draenei";
    case Race::worgen:      return "worgen";
    case Race::pandaren:    return "pandaren";
    case Race::orc:         return "orc";
    case Race::troll:       return "troll";
    case Race::forsaken:    return "forsaken";
    case Race::tauren:      return "tauren";
    case Race::blood_elf:   return "blood_elf";
    case Race::goblin:      return "goblin";
    default:                return "dunno";
    }
  }
  std::ostream& operator<<(std::ostream& o, const Race& r)
  {
    return o << to_string(r);
  }
};

namespace wowclass {
  enum class WowClass {
    warrior, hunter, shaman, druid, rogue, monk, mage, warlock, priest,
  };
  const std::vector<WowClass> wowclasses = {
    WowClass::warrior,
    WowClass::hunter,
    WowClass::shaman,
    WowClass::druid,
    WowClass::rogue,
    WowClass::monk,
    WowClass::mage,
    WowClass::warlock,
    WowClass::priest,
  };
  const std::string to_string(const WowClass& c)
  {
    switch (c) {
    case WowClass::warrior: return "warrior";
    case WowClass::hunter:  return "hunter";
    case WowClass::shaman:  return "shaman";
    case WowClass::druid:   return "druid";
    case WowClass::rogue:   return "rogue";
    case WowClass::monk:    return "monk";
    case WowClass::mage:    return "mage";
    case WowClass::warlock: return "warlock";
    case WowClass::priest:  return "priest";
    default:                return "dunno";
    }
  }
  std::ostream& operator<<(std::ostream& o, const WowClass& c)
  {
    return o << to_string(c);
  }
};

/*
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
*/

int main()
{
  std::cout << gender::Gender::female << '\n';
  std::cout << gender::Gender::male << '\n';
  for (int i = 0; i < gender::genders.size(); i++) {
    std::cout << i << ": " << gender::genders[i] << '\n';
  }
  std::cout << faction::Faction::alliance << '\n';
  std::cout << faction::Faction::horde << '\n';
  for (auto& f : faction::factions) {
    std::cout << f << '\n';
  }
  for (auto& r : race::races) {
    std::cout << r << '\n';
  }
  for (auto& c : wowclass::wowclasses) {
    std::cout << c << '\n';
  }
  return 0;
}
