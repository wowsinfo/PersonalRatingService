#include <iostream>
#include "reader.h"

int main() {
    const auto reader = examples::Reader::from_file("personal_rating.json");
    auto result = reader->get_value("123");
    const auto& result_string = kt_lib.model.RemoteExpectValue.toString(result);
    std::cout << result_string << std::endl;
    return 0;
}
