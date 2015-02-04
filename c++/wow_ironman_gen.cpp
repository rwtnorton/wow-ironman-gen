#include <iostream>
#include <unordered_map>
#include <vector>
#include <random>
#include <string>
using namespace std;

namespace wow {

  struct Gender {
    enum class Kind { female, male };
    Kind kind;
    Gender() {}
    Gender(Kind k) : kind(k) {}
    const string to_s() const {
      if (this->kind == Kind::female) {
        return "female";
      }
      if (this->kind == Kind::male) {
        return "male";
      }
      return "dunno";
    }
  };
  std::ostream& operator<<(std::ostream& o, const Gender& g) {
    return o << g.to_s();
  }
  static const Gender Male = Gender(Gender::Kind::male);
  static const Gender Female = Gender(Gender::Kind::female);
};

int main() {
  cout << "ohai" << endl;
  wow::Gender dude = wow::Male;
  wow::Gender gal = wow::Female;
  cout << dude.to_s() << endl;
  cout << gal.to_s() << endl;
  cout << dude << endl;
  cout << gal << endl;
  return 0;
}
