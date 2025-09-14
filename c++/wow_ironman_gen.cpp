#include <iostream>
#include <unordered_map>
#include <vector>
#include <random>
#include <string>
#include <print>
#include <format>
#include <array>
#include <ranges>

// gender

namespace gender {
  enum class Gender { female, male, };

  const auto genders { std::to_array({ Gender::female, Gender::male }) };

  constexpr std::string to_string(const Gender& g) {
    switch (g) {
    case Gender::female: return "female";
    case Gender::male:   return "male";
    }
  }

  std::ostream& operator<<(std::ostream& o, const Gender& g) {
    return o << to_string(g);
  }
};

namespace std {
  using gender::Gender;
  using gender::to_string;

  template <>
  struct std::formatter<Gender> {
    constexpr auto parse(std::format_parse_context& ctx) {
      return ctx.begin();
    }

    template <typename FormatContext>
    auto format(const Gender& g, FormatContext& ctx) const {
      return std::format_to(ctx.out(), "{}", to_string(g));
    }
  };
};

// faction

namespace faction {
  enum class Faction { alliance, horde, };

  constexpr auto factions { std::to_array({ Faction::alliance, Faction::horde }) };

  const std::string to_string(const Faction &f) {
    switch (f) {
    case Faction::alliance: return "alliance";
    case Faction::horde:    return "horde";
    }
  }

  std::ostream& operator<<(std::ostream& o, const Faction& f) {
    return o << to_string(f);
  }
};

namespace std {
  using faction::Faction;
  using faction::to_string;

  template <>
  struct std::formatter<Faction> {
    constexpr auto parse(std::format_parse_context& ctx) {
      return ctx.begin();
    }

    template <typename FormatContext>
    auto format(const Faction& f, FormatContext& ctx) const {
      return std::format_to(ctx.out(), "{}", to_string(f));
    }
  };
};

// race

namespace race {
  enum class Race {
    human, night_elf, dwarf, gnome, draenei, worgen,
    pandaren,
    orc, troll, forsaken, tauren, blood_elf, goblin,
  };

  constexpr auto races { std::to_array({
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
  }) };

  const std::string to_string(const Race &r) {
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
    }
  }

  std::ostream& operator<<(std::ostream& o, const Race& r) {
    return o << to_string(r);
  }
};

namespace std {
  using race::Race;
  using race::to_string;

  template <>
  struct std::formatter<Race> {
    constexpr auto parse(std::format_parse_context& ctx) {
      return ctx.begin();
    }

    template <typename FormatContext>
    auto format(const Race& r, FormatContext& ctx) const {
      return std::format_to(ctx.out(), "{}", to_string(r));
    }
  };
};

// wowclass

namespace wowclass {
  enum class WowClass {
    warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest,
  };

  constexpr auto wowclasses { std::to_array({
    WowClass::warrior,
    WowClass::paladin,
    WowClass::hunter,
    WowClass::shaman,
    WowClass::druid,
    WowClass::rogue,
    WowClass::monk,
    WowClass::mage,
    WowClass::warlock,
    WowClass::priest,
  }) };

  const std::string to_string(const WowClass& c) {
    switch (c) {
    case WowClass::warrior: return "warrior";
    case WowClass::paladin: return "paladin";
    case WowClass::hunter:  return "hunter";
    case WowClass::shaman:  return "shaman";
    case WowClass::druid:   return "druid";
    case WowClass::rogue:   return "rogue";
    case WowClass::monk:    return "monk";
    case WowClass::mage:    return "mage";
    case WowClass::warlock: return "warlock";
    case WowClass::priest:  return "priest";
    }
  }

  std::ostream& operator<<(std::ostream& o, const WowClass& c) {
    return o << to_string(c);
  }
};

namespace std {
  using wowclass::WowClass;
  using wowclass::to_string;

  template <>
  struct std::formatter<WowClass> {
    constexpr auto parse(std::format_parse_context& ctx) {
      return ctx.begin();
    }

    template <typename FormatContext>
    auto format(const WowClass& w, FormatContext& ctx) const {
      return std::format_to(ctx.out(), "{}", to_string(w));
    }
  };
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

template <std::ranges::random_access_range R>
const
std::unordered_map<
  race::Race, R
> wowclasses_for_race {
   {race::Race::human, std::to_array({ wowclass::WowClass::warrior })},
};

constexpr auto by_race(race::Race r) {
  using enum race::Race;
  using enum wowclass::WowClass;
  switch (r) {
  case human:     return std::vector { warrior, paladin, hunter, rogue, monk, mage, warlock, priest, };
  case night_elf: return std::vector { warrior, hunter, rogue, druid, monk, mage, priest, };
  case dwarf:     return std::vector { warrior, paladin, hunter, shaman, rogue, monk, mage, warlock, priest, };
  case gnome:     return std::vector { warrior, rogue, monk, mage, warlock, priest, };
  case draenei:   return std::vector { warrior, paladin, hunter, shaman, monk, mage, priest, };
  case worgen:    return std::vector { warrior, hunter, druid, rogue, mage, warlock, priest, };
  case pandaren:  return std::vector { warrior, hunter, shaman, rogue, monk, mage, priest, };
  case orc:       return std::vector { warrior, hunter, shaman, rogue, monk, mage, warlock, };
  case troll:     return std::vector { warrior, hunter, shaman, druid, rogue, monk, mage, warlock, priest, };
  case forsaken:  return std::vector { warrior, hunter, rogue, monk, mage, warlock, priest, };
  case tauren:    return std::vector { warrior, paladin, hunter, shaman, druid, monk, priest, };
  case blood_elf: return std::vector { warrior, paladin, hunter, rogue, monk, mage, warlock, priest, };
  case goblin:    return std::vector { warrior, hunter, shaman, rogue, mage, warlock, priest, };
  }
}

int main() {
  using gender::Gender;
  std::println("{}", gender::to_string(Gender::female));
  std::println("{}", Gender::female);
  // std::println(Gender::female);

  std::println("== Genders:");

  std::println("{}", Gender::female);
  std::println("{}", Gender::male);
  for (int i = 0; i < gender::genders.size(); i++) {
    std::println("{}: {}", i, gender::genders[i]);
  }

  using faction::Faction;
  std::println("== Factions:");
  std::println("{}", Faction::alliance);
  std::println("{}", Faction::horde);
  for (auto& f : faction::factions) {
    std::println("{}", f);
  }

  std::println("== Races:");
  for (auto& r : race::races) {
    std::println("{}", r);
  }

  std::println("== WowClasses:");
  for (auto& c : wowclass::wowclasses) {
    std::println("{}", c);
  }

  using race::Race;
  std::println("wowclasses for humans:");
  // for (auto& c : wowclasses_for_race<std::ranges::random_access_range<Race::human>>[Race::human]) {
  //   println("{}", c);
  // }
  for (auto& wc : by_race(Race::human)) {
    std::println("- {}", wc);
  }

  return 0;
}
