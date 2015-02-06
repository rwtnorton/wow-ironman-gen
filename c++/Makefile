
#CXX=clang++
CXX=g++
CXXFLAGS=-g -std=c++11 -Wall -pedantic -Werror
BIN=wow_ironman_gen

SRC=$(wildcard *.cpp)
OBJ=$(SRC:%.cpp=%.o)

all: $(OBJ)
	$(CXX) -o $(BIN) $^

%.o: %.c
	$(CXX) $@ -c $<

clean:
	rm -f *.o
	rm $(BIN)
