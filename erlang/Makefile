.SUFFIXES: .erl .beam

.erl.beam:
	erlc -W $<

ERL = erl -boot start_clean

MODS = sample genders races classes

current_dir = $(shell pwd)

all: compile
	${ERL} -pa ${current_dir}

compile: ${MODS:%=%.beam}

clean:
	rm -rf *.beam erl_crash.dump
