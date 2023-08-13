#pragma once
#include "personalrating_api.h"
#include <memory>
#include <string>


#define reader_companion                                                       \
  personalrating_kref_usecase_PersonalRatingReader_Companion
#define reader_object personalrating_kref_usecase_PersonalRatingReader
#define kt_lib personalrating_symbols()->kotlin.root
#define kt_lib_reader kt_lib.usecase.PersonalRatingReader

namespace examples {
class Reader {
  reader_object reader;

  // No equal
  Reader &operator=(const Reader &) = delete;

public:
  static std::unique_ptr<Reader> from_file(const std::string &path) {
    auto temp = std::make_unique<Reader>();
    temp -> reader =
        kt_lib_reader.Companion.fromFile(reader_companion(), path.c_str());
    return temp;
  }
  static std::unique_ptr<Reader> from_string(const std::string &str) {
    auto temp = std::make_unique<Reader>();
    temp -> reader =
        kt_lib_reader.Companion.fromString(reader_companion(), str.c_str());
    return temp;
  }

  const personalrating_kref_model_RemoteExpectValue get_value(const char* key) {
    return kt_lib_reader.getExpectedValue(reader, key);
  }
};
} // namespace examples
