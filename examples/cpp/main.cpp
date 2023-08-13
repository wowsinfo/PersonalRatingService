#include "reader.h"
#include <iostream>

int main(int argc, char **argv) {
  if (argc != 2) {
    std::cout << "Usage: main.exe <key>" << std::endl;
    return 1;
  }

  const auto reader = examples::Reader::from_file("personal_rating.json");
  auto key = argv[1];
  auto result = reader->get_value(key);
  if (result.pinned == nullptr) {
    std::cout << "No such key" << std::endl;
  } else {
    const auto &result_string = kt_lib.model.RemoteExpectValue.toString(result);
    std::cout << result_string << std::endl;
  }

  return 0;
}
