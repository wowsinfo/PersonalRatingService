#pragma once
#include <functional>
#include <iostream>
#include <memory>
#include <string>

#include "personalrating_api.h"

#define reader_companion \
    personalrating_kref_usecase_PersonalRatingReader_Companion
#define reader_object personalrating_kref_usecase_PersonalRatingReader
#define kt_lib personalrating_symbols()->kotlin.root
#define kt_lib_reader kt_lib.usecase.PersonalRatingReader

namespace examples {
class Reader {
    reader_object reader;

    // No equal
    Reader &operator=(const Reader &) = delete;

    std::function<void(Reader *)> onReady = nullptr;
    static void on_remote_callback(const char *remote_string, Reader *target) {
        target->reader = kt_lib_reader.Companion.fromString(reader_companion(),
                                                            remote_string);
        target->onReady(target);
    }

public:
    static std::unique_ptr<Reader> from_file(const std::string &path) {
        auto temp = std::make_unique<Reader>();
        temp->reader =
            kt_lib_reader.Companion.fromFile(reader_companion(), path.c_str());
        return temp;
    }
    static std::unique_ptr<Reader> from_string(const std::string &str) {
        auto temp = std::make_unique<Reader>();
        temp->reader =
            kt_lib_reader.Companion.fromString(reader_companion(), str.c_str());
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

        auto service =
            kt_lib.service.RemoteDataServiceNative.RemoteDataServiceNative();
        kt_lib.service.RemoteDataServiceNative.getRemoteString(
            service, (void *)on_remote_callback, this);
    }

    const personalrating_kref_model_RemoteExpectValue get_value(
        const char *key) {
        return kt_lib_reader.getExpectedValue(reader, key);
    }
};  // namespace examples
}  // namespace examples
