const std = @import("std");
const ArrayList = std.ArrayList;

pub fn main() !void {
    const stdout_file = std.io.getStdOut().writer();
    var bw = std.io.bufferedWriter(stdout_file);
    const stdout = bw.writer();

    errdefer {
        bw.flush() catch |err| std.debug.print("flush failed: {any}\n", .{err});
    }

    var debug: bool = getDebugEnvVar();
    // std.debug.print("debug: {any}\n", .{debug});

    var gpa = std.heap.GeneralPurposeAllocator(.{}){};
    const allocator = gpa.allocator();
    var seed: u64 = @intCast(std.time.microTimestamp());
    if (debug) {
        std.debug.print("seed: {d}\n", .{seed});
    }
    var rnd_gen = std.rand.DefaultPrng.init(seed);
    var r: std.rand.Random = rnd_gen.random();

    var combos: []Combination = try pandariaCombinations(allocator);
    var builder: ToonBuilder = ToonBuilder{ .combos = combos };
    var t: Toon = try builder.build(r);
    if (debug) {
        std.debug.print("rand toon: {any}\n", .{t});
    }
    try stdout.print("{s}\n", .{try t.toString(allocator)});
    try bw.flush();
}

// test "simple test" {
//     var list = std.ArrayList(i32).init(std.testing.allocator);
//     defer list.deinit(); // try commenting this out and see if zig detects the memory leak!
//     try list.append(42);
//     try std.testing.expectEqual(@as(i32, 42), list.pop());
// }

fn getDebugEnvVar() bool {
    if (std.os.getenv("debug")) |s| {
        if (s.len == 0) {
            return false;
        } else {
            switch (s[0]) {
                'y', 'Y', '1', 'T', 't' => return true,
                'n', 'N', '0', 'F', 'f' => return false,
                else => return false,
            }
        }
    } else {
        return false;
    }
}

const Race = enum {
    blood_elf,
    dranei,
    dwarf,
    forsaken,
    gnome,
    goblin,
    human,
    night_elf,
    orc,
    pandaren,
    tauren,
    troll,
    worgen,

    pub fn toString(self: Race) []const u8 {
        return @tagName(self);
    }
};
pub fn randomRace(r: std.rand.Random) Race {
    var n: usize = @typeInfo(Race).Enum.fields.len;
    const i = r.uintLessThan(usize, n);
    return @enumFromInt(i);
}

// No death knights allowed for the challenge.
const Class = enum {
    druid,
    hunter,
    mage,
    monk,
    paladin,
    priest,
    rogue,
    shaman,
    warlock,
    warrior,

    pub fn toString(self: Class) []const u8 {
        return @tagName(self);
    }
};

pub fn randomClass(r: std.rand.Random) Class {
    var n: usize = @typeInfo(Class).Enum.fields.len;
    const i = r.uintLessThan(usize, n);
    return @enumFromInt(i);
}

const Faction = enum {
    alliance,
    horde,

    pub fn toString(self: Faction) []const u8 {
        return @tagName(self);
    }
};

pub fn randomFaction(r: std.rand.Random) Faction {
    var n: usize = @typeInfo(Faction).Enum.fields.len;
    const i = r.uintLessThan(usize, n);
    return @enumFromInt(i);
}

const Gender = enum {
    female,
    male,

    pub fn toString(self: Gender) []const u8 {
        return @tagName(self);
    }
};

const Combination = struct {
    race: Race,
    classes: []Class,
    factions: []Faction,
    genders: []Gender,
};

fn pandariaBloodElfCombination(allocator: std.mem.Allocator) !Combination {
    var classes = ArrayList(Class).init(allocator);
    try classes.append(Class.hunter);
    try classes.append(Class.mage);
    try classes.append(Class.monk);
    try classes.append(Class.paladin);
    try classes.append(Class.priest);
    try classes.append(Class.rogue);
    try classes.append(Class.warlock);
    try classes.append(Class.warrior);
    var factions = ArrayList(Faction).init(allocator);
    try factions.append(Faction.horde);
    var genders = ArrayList(Gender).init(allocator);
    try genders.append(Gender.female);
    try genders.append(Gender.male);
    var result: Combination = Combination{
        .race = Race.blood_elf,
        .classes = classes.items,
        .factions = factions.items,
        .genders = genders.items,
    };
    return result;
}

fn pandariaDraneiCombination(allocator: std.mem.Allocator) !Combination {
    var classes = ArrayList(Class).init(allocator);
    try classes.append(Class.hunter);
    try classes.append(Class.mage);
    try classes.append(Class.monk);
    try classes.append(Class.paladin);
    try classes.append(Class.priest);
    try classes.append(Class.shaman);
    try classes.append(Class.warrior);
    var factions = ArrayList(Faction).init(allocator);
    try factions.append(Faction.alliance);
    var genders = ArrayList(Gender).init(allocator);
    try genders.append(Gender.female);
    try genders.append(Gender.male);
    var result: Combination = Combination{
        .race = Race.dranei,
        .classes = classes.items,
        .factions = factions.items,
        .genders = genders.items,
    };
    return result;
}

fn pandariaDwarfCombination(allocator: std.mem.Allocator) !Combination {
    var classes = ArrayList(Class).init(allocator);
    try classes.append(Class.hunter);
    try classes.append(Class.mage);
    try classes.append(Class.monk);
    try classes.append(Class.paladin);
    try classes.append(Class.priest);
    try classes.append(Class.rogue);
    try classes.append(Class.shaman);
    try classes.append(Class.warlock);
    try classes.append(Class.warrior);
    var factions = ArrayList(Faction).init(allocator);
    try factions.append(Faction.alliance);
    var genders = ArrayList(Gender).init(allocator);
    try genders.append(Gender.female);
    try genders.append(Gender.male);
    var result: Combination = Combination{
        .race = Race.dwarf,
        .classes = classes.items,
        .factions = factions.items,
        .genders = genders.items,
    };
    return result;
}

fn pandariaForsakenCombination(allocator: std.mem.Allocator) !Combination {
    var classes = ArrayList(Class).init(allocator);
    try classes.append(Class.hunter);
    try classes.append(Class.mage);
    try classes.append(Class.monk);
    try classes.append(Class.priest);
    try classes.append(Class.rogue);
    try classes.append(Class.warlock);
    try classes.append(Class.warrior);
    var factions = ArrayList(Faction).init(allocator);
    try factions.append(Faction.horde);
    var genders = ArrayList(Gender).init(allocator);
    try genders.append(Gender.female);
    try genders.append(Gender.male);
    var result: Combination = Combination{
        .race = Race.forsaken,
        .classes = classes.items,
        .factions = factions.items,
        .genders = genders.items,
    };
    return result;
}

fn pandariaGnomeCombination(allocator: std.mem.Allocator) !Combination {
    var classes = ArrayList(Class).init(allocator);
    try classes.append(Class.mage);
    try classes.append(Class.monk);
    try classes.append(Class.priest);
    try classes.append(Class.rogue);
    try classes.append(Class.warlock);
    try classes.append(Class.warrior);
    var factions = ArrayList(Faction).init(allocator);
    try factions.append(Faction.alliance);
    var genders = ArrayList(Gender).init(allocator);
    try genders.append(Gender.female);
    try genders.append(Gender.male);
    var result: Combination = Combination{
        .race = Race.gnome,
        .classes = classes.items,
        .factions = factions.items,
        .genders = genders.items,
    };
    return result;
}

fn pandariaGoblinCombination(allocator: std.mem.Allocator) !Combination {
    var classes = ArrayList(Class).init(allocator);
    try classes.append(Class.hunter);
    try classes.append(Class.mage);
    try classes.append(Class.priest);
    try classes.append(Class.rogue);
    try classes.append(Class.shaman);
    try classes.append(Class.warlock);
    try classes.append(Class.warrior);
    var factions = ArrayList(Faction).init(allocator);
    try factions.append(Faction.horde);
    var genders = ArrayList(Gender).init(allocator);
    try genders.append(Gender.female);
    try genders.append(Gender.male);
    var result: Combination = Combination{
        .race = Race.goblin,
        .classes = classes.items,
        .factions = factions.items,
        .genders = genders.items,
    };
    return result;
}

fn pandariaHumanCombination(allocator: std.mem.Allocator) !Combination {
    var classes = ArrayList(Class).init(allocator);
    try classes.append(Class.hunter);
    try classes.append(Class.mage);
    try classes.append(Class.monk);
    try classes.append(Class.paladin);
    try classes.append(Class.priest);
    try classes.append(Class.rogue);
    try classes.append(Class.warlock);
    try classes.append(Class.warrior);
    var factions = ArrayList(Faction).init(allocator);
    try factions.append(Faction.alliance);
    var genders = ArrayList(Gender).init(allocator);
    try genders.append(Gender.female);
    try genders.append(Gender.male);
    var result: Combination = Combination{
        .race = Race.human,
        .classes = classes.items,
        .factions = factions.items,
        .genders = genders.items,
    };
    return result;
}

fn pandariaNightElfCombination(allocator: std.mem.Allocator) !Combination {
    var classes = ArrayList(Class).init(allocator);
    try classes.append(Class.druid);
    try classes.append(Class.hunter);
    try classes.append(Class.mage);
    try classes.append(Class.monk);
    try classes.append(Class.priest);
    try classes.append(Class.rogue);
    try classes.append(Class.warrior);
    var factions = ArrayList(Faction).init(allocator);
    try factions.append(Faction.alliance);
    var genders = ArrayList(Gender).init(allocator);
    try genders.append(Gender.female);
    try genders.append(Gender.male);
    var result: Combination = Combination{
        .race = Race.night_elf,
        .classes = classes.items,
        .factions = factions.items,
        .genders = genders.items,
    };
    return result;
}

fn pandariaOrcCombination(allocator: std.mem.Allocator) !Combination {
    var classes = ArrayList(Class).init(allocator);
    try classes.append(Class.hunter);
    try classes.append(Class.mage);
    try classes.append(Class.monk);
    try classes.append(Class.rogue);
    try classes.append(Class.shaman);
    try classes.append(Class.warlock);
    try classes.append(Class.warrior);
    var factions = ArrayList(Faction).init(allocator);
    try factions.append(Faction.horde);
    var genders = ArrayList(Gender).init(allocator);
    try genders.append(Gender.female);
    try genders.append(Gender.male);
    var result: Combination = Combination{
        .race = Race.orc,
        .classes = classes.items,
        .factions = factions.items,
        .genders = genders.items,
    };
    return result;
}

fn pandariaPandarenCombination(allocator: std.mem.Allocator) !Combination {
    var classes = ArrayList(Class).init(allocator);
    try classes.append(Class.hunter);
    try classes.append(Class.mage);
    try classes.append(Class.monk);
    try classes.append(Class.priest);
    try classes.append(Class.rogue);
    try classes.append(Class.shaman);
    try classes.append(Class.warrior);
    var factions = ArrayList(Faction).init(allocator);
    try factions.append(Faction.alliance);
    try factions.append(Faction.horde);
    var genders = ArrayList(Gender).init(allocator);
    try genders.append(Gender.female);
    try genders.append(Gender.male);
    var result: Combination = Combination{
        .race = Race.pandaren,
        .classes = classes.items,
        .factions = factions.items,
        .genders = genders.items,
    };
    return result;
}

fn pandariaTaurenCombination(allocator: std.mem.Allocator) !Combination {
    var classes = ArrayList(Class).init(allocator);
    try classes.append(Class.druid);
    try classes.append(Class.hunter);
    try classes.append(Class.monk);
    try classes.append(Class.paladin);
    try classes.append(Class.priest);
    try classes.append(Class.shaman);
    try classes.append(Class.warrior);
    var factions = ArrayList(Faction).init(allocator);
    try factions.append(Faction.horde);
    var genders = ArrayList(Gender).init(allocator);
    try genders.append(Gender.female);
    try genders.append(Gender.male);
    var result: Combination = Combination{
        .race = Race.tauren,
        .classes = classes.items,
        .factions = factions.items,
        .genders = genders.items,
    };
    return result;
}

fn pandariaTrollCombination(allocator: std.mem.Allocator) !Combination {
    var classes = ArrayList(Class).init(allocator);
    try classes.append(Class.druid);
    try classes.append(Class.hunter);
    try classes.append(Class.mage);
    try classes.append(Class.monk);
    try classes.append(Class.priest);
    try classes.append(Class.rogue);
    try classes.append(Class.shaman);
    try classes.append(Class.warlock);
    try classes.append(Class.warrior);
    var factions = ArrayList(Faction).init(allocator);
    try factions.append(Faction.horde);
    var genders = ArrayList(Gender).init(allocator);
    try genders.append(Gender.female);
    try genders.append(Gender.male);
    var result: Combination = Combination{
        .race = Race.troll,
        .classes = classes.items,
        .factions = factions.items,
        .genders = genders.items,
    };
    return result;
}

fn pandariaWorgenCombination(allocator: std.mem.Allocator) !Combination {
    var classes = ArrayList(Class).init(allocator);
    try classes.append(Class.druid);
    try classes.append(Class.hunter);
    try classes.append(Class.mage);
    try classes.append(Class.priest);
    try classes.append(Class.rogue);
    try classes.append(Class.warlock);
    try classes.append(Class.warrior);
    var factions = ArrayList(Faction).init(allocator);
    try factions.append(Faction.alliance);
    var genders = ArrayList(Gender).init(allocator);
    try genders.append(Gender.female);
    try genders.append(Gender.male);
    var result: Combination = Combination{
        .race = Race.worgen,
        .classes = classes.items,
        .factions = factions.items,
        .genders = genders.items,
    };
    return result;
}

fn pandariaCombinations(allocator: std.mem.Allocator) ![]Combination {
    var combinations = ArrayList(Combination).init(allocator);
    try combinations.append(try pandariaBloodElfCombination(allocator));
    try combinations.append(try pandariaDraneiCombination(allocator));
    try combinations.append(try pandariaDwarfCombination(allocator));
    try combinations.append(try pandariaForsakenCombination(allocator));
    try combinations.append(try pandariaGnomeCombination(allocator));
    try combinations.append(try pandariaGoblinCombination(allocator));
    try combinations.append(try pandariaHumanCombination(allocator));
    try combinations.append(try pandariaNightElfCombination(allocator));
    try combinations.append(try pandariaOrcCombination(allocator));
    try combinations.append(try pandariaPandarenCombination(allocator));
    try combinations.append(try pandariaTaurenCombination(allocator));
    try combinations.append(try pandariaTrollCombination(allocator));
    try combinations.append(try pandariaWorgenCombination(allocator));
    return combinations.items;
}

pub fn choose(comptime T: type, vs: []T, rng: std.rand.Random) ?T {
    if (vs.len == 0) {
        return null;
    }
    return vs[rng.uintLessThan(usize, vs.len)];
}

const Toon = struct {
    race: Race,
    class: Class,
    faction: Faction,
    gender: Gender,

    pub fn toString(self: Toon, allocator: std.mem.Allocator) ![]const u8 {
        var bleh = [_][]const u8{
            self.gender.toString(),
            self.faction.toString(),
            self.race.toString(),
            self.class.toString(),
        };
        var things: []const []const u8 = undefined;
        things = bleh[0..];
        return try std.mem.join(allocator, " ", things);
    }
};

const ToonError = error{
    InvalidRace,
    InvalidClass,
    InvalidFaction,
    InvalidGender,
};

const ToonBuilder = struct {
    combos: []Combination,

    fn _getComboForRace(self: ToonBuilder, needle: Race) ?Combination {
        for (self.combos) |c| {
            if (c.race == needle) {
                return c;
            }
        }
        return null;
    }

    pub fn build(self: ToonBuilder, rng: std.rand.Random) ToonError!Toon {
        var race: Race = randomRace(rng);
        var combo: Combination = self._getComboForRace(race) orelse return ToonError.InvalidRace;
        var class: Class = if (choose(Class, combo.classes, rng)) |c| c else return ToonError.InvalidClass;
        var faction: Faction = if (choose(Faction, combo.factions, rng)) |f| f else return ToonError.InvalidFaction;
        var gender: Gender = if (choose(Gender, combo.genders, rng)) |g| g else return ToonError.InvalidGender;
        return Toon{
            .race = race,
            .class = class,
            .faction = faction,
            .gender = gender,
        };
    }
};
