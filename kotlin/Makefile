KC = kotlinc
JAR_FILE = wow_ironman_gen.jar
KCFLAGS = -include-runtime -d $(JAR_FILE)
MAIN_FILE = WowIronmanGen.kt

default: jar run

jar: $(MAIN_FILE)
	$(KC) $(KCFLAGS) $(MAIN_FILE)

run: jar
	java -jar $(JAR_FILE)

just-run:
	java -jar $(JAR_FILE)

clean:
	$(RM) *.jar *.class
