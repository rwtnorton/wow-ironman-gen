#include <iostream>
#include <unordered_map>
#include <vector>
#include <random>
#include <string>
using namespace std;

namespace wow {

  enum class Gender { female, male };
  const vector<Gender> genders = { Gender::female, Gender::male };
  const string to_string(const Gender& g) {
    switch (g) {
    case Gender::female: return "female";
    case Gender::male:   return "male";
    default:             return "dunno";
    }
  }
  ostream& operator<<(ostream& o, const Gender& g) {
    return o << to_string(g);
  }

};

int main() {
  cout << wow::Gender::female << endl;
  cout << wow::Gender::male << endl;
  for (int i = 0; i < wow::genders.size(); i++) {
    cout << i << ": " << wow::genders[i] << endl;
  }
  return 0;
}
