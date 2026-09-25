module Domain

type WowClass =
    | Warrior
    | Paladin
    | Hunter
    | Rogue
    | Druid
    | Monk
    | Shaman
    | Mage
    | Priest
    | Warlock

    override this.ToString() =
        match this with
        | Warrior -> "warrior"
        | Paladin -> "paladin"
        | Hunter -> "hunter"
        | Rogue -> "rogue"
        | Druid -> "druid"
        | Monk -> "monk"
        | Shaman -> "shaman"
        | Mage -> "mage"
        | Priest -> "priest"
        | Warlock -> "warlock"

type Race =
    | Human
    | NightElf
    | Dwarf
    | Gnome
    | Draenei
    | Worgen
    | Pandaren
    | Orc
    | Troll
    | Forsaken
    | Tauren
    | BloodElf
    | Goblin

    override this.ToString() : string =
        match this with
        | Human -> "human"
        | NightElf -> "night_elf"
        | Dwarf -> "dwarf"
        | Gnome -> "gnome"
        | Draenei -> "draenei"
        | Worgen -> "worgen"
        | Pandaren -> "pandaren"
        | Orc -> "orc"
        | Troll -> "troll"
        | Forsaken -> "forsaken"
        | Tauren -> "tauren"
        | BloodElf -> "blood_elf"
        | Goblin -> "goblin"

let allRaces =
    [
        Human
        NightElf
        Dwarf
        Gnome
        Draenei
        Worgen
        Pandaren
        Orc
        Troll
        Forsaken
        Tauren
        BloodElf
        Goblin
    ]

type Gender =
    | Female
    | Male

    override this.ToString() =
        match this with
        | Female -> "female"
        | Male -> "male"

type Faction =
    | Alliance
    | Horde

    override this.ToString() =
        match this with
        | Alliance -> "alliance"
        | Horde -> "horde"

let allowedFactionsByRace (r: Race) =
    match r with
    | Human -> [ Alliance ]
    | NightElf -> [ Alliance ]
    | Dwarf -> [ Alliance ]
    | Gnome -> [ Alliance ]
    | Draenei -> [ Alliance ]
    | Worgen -> [ Alliance ]
    | Pandaren -> [ Alliance; Horde ]
    | Orc -> [ Horde ]
    | Troll -> [ Horde ]
    | Forsaken -> [ Horde ]
    | Tauren -> [ Horde ]
    | BloodElf -> [ Horde ]
    | Goblin -> [ Horde ]

let allowedGendersByRace (r: Race) =
    match r with
    | Human -> [ Female; Male ]
    | NightElf -> [ Female; Male ]
    | Dwarf -> [ Female; Male ]
    | Gnome -> [ Female; Male ]
    | Draenei -> [ Female; Male ]
    | Worgen -> [ Female; Male ]
    | Pandaren -> [ Female; Male ]
    | Orc -> [ Female; Male ]
    | Troll -> [ Female; Male ]
    | Forsaken -> [ Female; Male ]
    | Tauren -> [ Female; Male ]
    | BloodElf -> [ Female; Male ]
    | Goblin -> [ Female; Male ]

let allowedWowClassesByRace (r: Race) =
    match r with
    | Human -> [ Warrior; Paladin; Hunter; Rogue; Monk; Mage; Warlock; Priest ]
    | NightElf -> [ Warrior; Hunter; Rogue; Druid; Monk; Mage; Priest ]
    | Dwarf -> [ Warrior; Paladin; Hunter; Shaman; Rogue; Monk; Mage; Warlock; Priest ]
    | Gnome -> [ Warrior; Rogue; Monk; Mage; Warlock; Priest ]
    | Draenei -> [ Warrior; Paladin; Hunter; Shaman; Monk; Mage; Priest ]
    | Worgen -> [ Warrior; Hunter; Druid; Rogue; Mage; Warlock; Priest ]
    | Pandaren -> [ Warrior; Hunter; Shaman; Rogue; Monk; Mage; Priest ]
    | Orc -> [ Warrior; Hunter; Shaman; Rogue; Monk; Mage; Warlock ]
    | Troll -> [ Warrior; Hunter; Shaman; Druid; Rogue; Monk; Mage; Warlock; Priest ]
    | Forsaken -> [ Warrior; Hunter; Rogue; Monk; Mage; Warlock; Priest ]
    | Tauren -> [ Warrior; Paladin; Hunter; Shaman; Druid; Monk; Priest ]
    | BloodElf -> [ Warrior; Paladin; Hunter; Rogue; Monk; Mage; Warlock; Priest ]
    | Goblin -> [ Warrior; Hunter; Shaman; Rogue; Mage; Warlock; Priest ]

let choose (rand: System.Random) choices = List.randomChoiceWith rand choices

let chooseWowClassByRace (rand: System.Random) (race: Race) =
    race |> allowedWowClassesByRace |> choose rand

let chooseFactionByRace (rand: System.Random) (race: Race) =
    race |> allowedFactionsByRace |> choose rand

let chooseGenderByRace (rand: System.Random) (race: Race) =
    race |> allowedGendersByRace |> choose rand

let chooseRace (rand: System.Random) = allRaces |> choose rand

type Toon =
    {
        Race: Race
        WowClass: WowClass
        Faction: Faction
        Gender: Gender
    }

    override this.ToString() =
        let factionCnt = allowedFactionsByRace this.Race |> List.length

        if factionCnt > 1 then
            $"{this.Gender} {this.Faction} {this.Race} {this.WowClass}"
        else
            $"{this.Gender} {this.Race} {this.WowClass}"

    member this.isValid() =
        let factions = allowedFactionsByRace this.Race
        let genders = allowedGendersByRace this.Race
        let classes = allowedWowClassesByRace this.Race

        List.contains this.Faction factions
        && List.contains this.Gender genders
        && List.contains this.WowClass classes

    static member Generate(rand: System.Random) =
        let race = chooseRace rand

        {
            Race = race
            WowClass = chooseWowClassByRace rand race
            Faction = chooseFactionByRace rand race
            Gender = chooseGenderByRace rand race
        }
