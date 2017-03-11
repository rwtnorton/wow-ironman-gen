defmodule WowIronmanGen do
  @moduledoc """
  Randomly generate a World of Warcraft race/class/gender combo for the
  WoW Ironman Challenge.
  """

  def races do
    [:human, :night_elf, :dwarf, :gnome, :draenei, :worgen, :pandaren,
     :orc, :troll, :forsaken, :tauren, :blood_elf, :goblin]
  end

  # No death knights.
  def classes do
    [:warrior, :paladin, :hunter, :shaman, :druid, :rogue,
     :monk, :mage, :warlock, :priest]
  end

  def classes_for_race do
    %{human: [:warrior, :paladin, :hunter, :rogue, :monk, :mage, :warlock, :priest],
      night_elf: [:warrior, :hunter, :rogue, :druid, :monk, :mage, :priest],
      dwarf: [:warrior, :paladin, :hunter, :shaman, :rogue, :monk, :mage, :warlock,
              :priest],
      gnome: [:warrior, :rogue, :monk, :mage, :warlock, :priest],
      draenei: [:warrior, :paladin, :hunter, :shaman, :monk, :mage, :priest],
      worgen: [:warrior, :hunter, :druid, :rogue, :mage, :warlock, :priest],
      pandaren: [:warrior, :hunter, :shaman, :rogue, :monk, :mage, :priest],
      orc: [:warrior, :hunter, :shaman, :rogue, :monk, :mage, :warlock],
      troll: [:warrior, :hunter, :shaman, :druid, :rogue, :monk, :mage, :warlock,
              :priest],
      forsaken: [:warrior, :hunter, :rogue, :monk, :mage, :warlock, :priest],
      tauren: [:warrior, :paladin, :hunter, :shaman, :druid, :monk, :priest],
      blood_elf: [:warrior, :paladin, :hunter, :rogue, :monk, :mage, :warlock,
                  :priest],
      goblin: [:warrior, :hunter, :shaman, :rogue, :mage, :warlock, :priest],
     }
  end

  def genders, do: [:female, :male]

  def factions, do: [:alliance, :horde]

  def factions_for_race do
    [
     generate_race_faction_map(
       ~W[human night_elf dwarf gnome draenei worgen]a, [:alliance]
     ),
     generate_race_faction_map(
       ~W[orc troll forsaken tauren blood_elf goblin]a, [:horde]
     ),
     generate_race_faction_map(
       ~W[pandaren]a, ~W[alliance horde]a
     ),
    ]
    |> Enum.reduce(%{}, &Map.merge/2)
  end

  defp generate_race_faction_map(races, factions) do
    races
    |> Enum.zip(Stream.repeatedly(fn -> factions end))
    |> Enum.into(%{})
  end

  def random_race do
    races() |> Enum.random
  end

  def random_class(race) do
    classes_for_race()[race] |> Enum.random
  end

  def random_gender do
    genders() |> Enum.random
  end

  def random_faction(race) do
    factions_for_race()[race] |> Enum.random
  end

  def random_toon do
    race = random_race()
    {random_gender(), random_faction(race), race, random_class(race)}
  end

  def stringify_toon({g, f, r = :pandaren, c}) do
    "#{g} #{f} #{r} #{c}"
  end
  def stringify_toon({g, _f, r, c}) do
    "#{g} #{r} #{c}"
  end

  @doc """
  """
  def main(_argv) do
    random_toon()
    |> stringify_toon()
    |> IO.puts()
  end
end
