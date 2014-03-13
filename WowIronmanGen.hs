module WowIronmanGen
where

import System.Random
import qualified Data.Map as Map
import qualified Data.Set as Set
import qualified Data.Char as Char
import qualified Data.List as List

rollDie :: (Num a, Random a) => a -> IO a
--rollDie sides = getStdRandom $ randomR (1, sides)
rollDie sides = randomRIO (1, sides)

rollD6 :: IO Int
rollD6 = rollDie (6 :: Int)

randomIndex :: [a] -> IO Int
randomIndex xs = do
  r <- rollDie $ length xs
  return $ r - 1

--randomElement :: [a] -> Maybe IO a
--randomElement [] = Nothing
randomElement xs = do
  i <- randomIndex xs
  return $ xs !! i

data Race = Human
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
          deriving (Eq, Show, Read, Enum, Bounded, Ord)

-- No death knights.
data Class = Warrior
           | Paladin
           | Hunter
           | Shaman
           | Druid
           | Rogue
           | Monk
           | Mage
           | Warlock
           | Priest
           deriving (Eq, Show, Read, Enum, Bounded, Ord)

data Gender = Female | Male
            deriving (Eq, Show, Read, Enum, Bounded)

data Faction = Horde | Alliance
             deriving (Eq, Show, Read, Enum, Bounded)

-- type RaceClass = (Race, Class)

data Toon = Toon Gender Race Faction Class
          deriving (Eq, Show, Read)

-- data HumanClass =
--   Warrior | Paladin | Hunter | Rogue | Monk | Mage | Warlock | Priest
--   deriving (Eq, Show, Read, Enum, Bounded)
-- data NightElfClass =
--   Warrior | Hunter | Rogue | Druid | Monk | Mage | Priest
--   deriving (Eq, Show, Read, Enum, Bounded)
-- data DwarfClass =
--   Warrior | Paladin | Hunter | Shaman | Rogue | Monk | Mage | Warlock | Priest
--   deriving (Eq, Show, Read, Enum, Bounded)
-- data GnomeClass =
--   Warrior | Rogue | Monk | Mage | Warlock | Priest
--   deriving (Eq, Show, Read, Enum, Bounded)
-- data DraeneiClass =
--   Warrior | Paladin | Hunter | Shaman | Monk | Mage | Priest
--   deriving (Eq, Show, Read, Enum, Bounded)

humanClasses    = Set.fromList [Warrior, Paladin, Hunter, Rogue, Monk, Mage,
                                Warlock, Priest]
nightElfClasses = Set.fromList [Warrior, Hunter, Rogue, Druid, Monk, Mage,
                                Priest]
dwarfClasses    = Set.fromList [Warrior, Paladin, Hunter, Shaman, Rogue, Monk,
                                Mage, Warlock, Priest]
gnomeClasses    = Set.fromList [Warrior, Rogue, Monk, Mage, Warlock, Priest]
draeneiClasses  = Set.fromList [Warrior, Paladin, Hunter, Shaman, Monk, Mage,
                                Priest]
worgenClasses   = Set.fromList [Warrior, Hunter, Druid, Rogue, Mage, Warlock,
                                Priest]
pandarenClasses = Set.fromList [Warrior, Hunter, Shaman, Rogue, Monk, Mage,
                                Priest]
orcClasses      = Set.fromList [Warrior, Hunter, Shaman, Rogue, Monk, Mage,
                                Warlock]
trollClasses    = Set.fromList [Warrior, Hunter, Shaman, Druid, Rogue, Monk,
                                Mage, Warlock, Priest]
forsakenClasses = Set.fromList [Warrior, Hunter, Rogue, Monk, Mage, Warlock,
                                Priest]
taurenClasses   = Set.fromList [Warrior, Paladin, Hunter, Shaman, Druid, Monk,
                                Priest]
bloodElfClasses = Set.fromList [Warrior, Paladin, Hunter, Rogue, Monk, Mage,
                                Warlock, Priest]
goblinClasses   = Set.fromList [Warrior, Hunter, Shaman, Rogue, Mage, Warlock,
                                Priest]
classesForRace = Map.insert Human    humanClasses
               . Map.insert NightElf nightElfClasses
               . Map.insert Dwarf    dwarfClasses
               . Map.insert Gnome    gnomeClasses
               . Map.insert Draenei  draeneiClasses
               . Map.insert Worgen   worgenClasses
               . Map.insert Pandaren pandarenClasses
               . Map.insert Orc      orcClasses
               . Map.insert Troll    trollClasses
               . Map.insert Forsaken forsakenClasses
               . Map.insert Tauren   taurenClasses
               . Map.insert BloodElf bloodElfClasses
               . Map.insert Goblin   goblinClasses
               $ Map.empty

chooseRace = randomElement [minBound :: Race .. maxBound :: Race]

chooseClass race = randomElement cs
  where cs = Set.toList $ classesForRace Map.! race

chooseGender = randomElement [minBound :: Gender .. maxBound :: Gender]

chooseFaction = randomElement [minBound :: Faction .. maxBound :: Faction]

racialFaction race =
  case race of
    Human    -> Just Alliance
    NightElf -> Just Alliance
    Dwarf    -> Just Alliance
    Gnome    -> Just Alliance
    Draenei  -> Just Alliance
    Worgen   -> Just Alliance
    Orc      -> Just Horde
    Troll    -> Just Horde
    Forsaken -> Just Horde
    Tauren   -> Just Horde
    BloodElf -> Just Horde
    Goblin   -> Just Horde
    Pandaren -> Nothing

chooseToon = do
  r <- chooseRace
  c <- chooseClass r
  g <- chooseGender
  f <- case racialFaction r of
         (Just rf) -> return rf
         Nothing   -> chooseFaction
  return $ Toon g r f c

downCase s = map Char.toLower s

presentClass :: Class -> String
presentClass c = downCase $ show c

presentFaction :: Faction -> String
presentFaction f = downCase $ show f

presentGender :: Gender -> String
presentGender g = downCase $ show g

presentRace :: Race -> String
presentRace r = case r of
                NightElf  -> "night_elf"
                BloodElf  -> "blood_elf"
                otherwise -> downCase $ show r

presentToon :: Toon -> String
presentToon (Toon g r f c) = List.intercalate " " strs
  where g' = presentGender g
        r' = presentRace r
        f' = presentFaction f
        c' = presentClass c
        strs = case r of
               Pandaren  -> [g', r', f', c']
               otherwise -> [g', r',     c']

main :: IO ()
main = do
  toon <- chooseToon
  putStrLn $ presentToon toon
