This is a project to see in how many different programming languages
(and hopefully in paradigms befitting said languages) I can solve the
same problem:  generating a World of Warcraft gender/race/class
combination suitable for use in the WoW Ironman Challenge.

All programs should:
1.) Write to stdout.
2.) Conform to this format (ignoring initial whitespace):
       <gender> <race> <class>
    However, pandaren may choose either faction, so:
       <gender> <faction> <race> <class>
    a.) Each token should appear in lowercase.
    b.) Compound race names like Night Elf should use underscore
        as token separator (i.e., night_elf).
