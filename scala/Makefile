SC = scalac
SCFLAGS = -d classes
.SUFFIXES: .scala .class
.scala.class:
	$(SC) $(SCFLAGS) $*.scala

CLASSES = \
	src/wow_ironman_gen/Main.scala

default: classes run

classes: $(CLASSES:.scala=.class)

run: classes
	scala -cp classes wow_ironman_gen.Main

just-run:
	scala -cp classes wow_ironman_gen.Main

clean:
	$(RM) classes/wow_ironman_gen/*.class

