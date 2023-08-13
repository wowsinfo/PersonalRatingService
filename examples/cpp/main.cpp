#include <iostream>

#include "reader.h"

int main(int argc, char **argv) {
    if (argc != 2) {
        std::cout << "Usage: main.exe <key>" << std::endl;
        return 1;
    }

    auto key = argv[1];

    // callback version
    const auto on_data_reader = [&key](examples::Reader *reader) {
        std::cout << "Remote data loaded" << std::endl;
        auto result = reader->get_value(key);
        if (result.pinned == nullptr) {
            std::cout << "No such key" << std::endl;
        } else {
            const auto &result_string =
                kt_lib.model.RemoteExpectValue.toString(result);
            std::cout << result_string << std::endl;
        }
    };
    const auto remote_reader = examples::Reader::from_remote(on_data_reader);
    remote_reader->load_from_remote();

    const auto reader = examples::Reader::from_file("../../personal_rating.json");
    auto result = reader->get_value(key);
    if (result.pinned == nullptr) {
        std::cout << "No such key" << std::endl;
    } else {
        const auto &result_string =
            kt_lib.model.RemoteExpectValue.toString(result);
        std::cout << result_string << std::endl;
    }
    getchar();  // wait for remote call
    return 0;
}
