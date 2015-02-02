.SUFFIXES: .erl .beam

.erl.beam:
	erlc -W $<

ERL = erl -boot start_clean

MODS = sample genders races classes factions toons \
       sample_tests genders_tests

current_dir = $(shell pwd)
erl_run = ${ERL} -pa ${current_dir}

all: compile
	${erl_run}

compile: ${MODS:%=%.beam}

tests: compile
	${erl_run} -noshell -s genders_tests test -s init stop
	${erl_run} -noshell -s sample_tests test -s init stop

clean:
	rm -rf *.beam erl_crash.dump
