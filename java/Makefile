JC = javac
JCFLAGS = -d classes -sourcepath src
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JCFLAGS) $*.java

CLASSES = \
	src/wow_ironman_gen/AbstractRandomChooser.java \
	src/wow_ironman_gen/Faction.java \
	src/wow_ironman_gen/Gender.java \
	src/wow_ironman_gen/Main.java \
	src/wow_ironman_gen/ModHelper.java \
	src/wow_ironman_gen/Race.java \
	src/wow_ironman_gen/RandomChooser.java \
	src/wow_ironman_gen/RandomFactionChooser.java \
	src/wow_ironman_gen/RandomGenderChooser.java \
	src/wow_ironman_gen/RandomRaceChooser.java \
	src/wow_ironman_gen/RandomToonGenerator.java \
	src/wow_ironman_gen/RandomWowClassChooser.java \
	src/wow_ironman_gen/Toon.java \
	src/wow_ironman_gen/ToonPresenter.java \
	src/wow_ironman_gen/WowClass.java

default: classes run

classes: $(CLASSES:.java=.class)

run: classes
	java -cp classes wow_ironman_gen.Main

just-run:
	java -cp classes wow_ironman_gen.Main

clean:
	$(RM) classes/wow_ironman_gen/*.class
