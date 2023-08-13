#pragma once
#include <functional>
#include <iostream>
#include <memory>
#include <string>

#include "kotlin.h"

namespace examples {
class Reader {
    KN_PersonalRatingReader_Ref reader;

    // No equal
    Reader &operator=(const Reader &) = delete;

    std::function<void(Reader *)> onReady = nullptr;
    static void on_remote_callback(const char *remote_string, Reader *target) {
        target->reader = KN_PersonalRatingReader.Companion.fromString(
            KN_PersonalRatingReader_Companion(), remote_string);
        target->onReady(target);
    }

public:
    static std::unique_ptr<Reader> from_file(const std::string &path) {
        auto temp = std::make_unique<Reader>();
        temp->reader = KN_PersonalRatingReader.Companion.fromFile(
            KN_PersonalRatingReader_Companion(), path.c_str());
        return temp;
    }

    static std::unique_ptr<Reader> from_string(const std::string &str) {
        auto temp = std::make_unique<Reader>();
        temp->reader = KN_PersonalRatingReader.Companion.fromString(
            KN_PersonalRatingReader_Companion(), str.c_str());
        return temp;
    }

    static std::unique_ptr<Reader> from_remote(
        std::function<void(Reader *)> onReady) {
        auto temp = std::make_unique<Reader>();
        temp->onReady = onReady;
        return temp;
    }

    void load_from_remote() {
        if (onReady == nullptr) {
            throw std::logic_error(
                "onReady must be set before calling load_from_remote");
        }

        auto service = KN_RemoteDataService.RemoteDataServiceNative();
        KN_RemoteDataService.getRemoteString(service,
                                             (void *)on_remote_callback, this);
    }

    const personalrating_kref_model_RemoteExpectValue get_value(
        const char *key) {
        return KN_PersonalRatingReader.getExpectedValue(reader, key);
    }
};  // namespace examples
}  // namespace examples
