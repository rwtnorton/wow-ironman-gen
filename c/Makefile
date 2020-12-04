compile:
	$(CC) -Werror -pedantic-errors -g -o bin/wow-ironman-gen wow-ironman-gen.c

run: compile
	bin/wow-ironman-gen

clean:
	$(RM) bin/wow-ironman-gen

etags:
	etags -o TAGS *.[ch]
