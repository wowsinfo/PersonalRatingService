cmake_minimum_required(VERSION 3.16)
project(cpp-example)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

set(KOTLIN_DIR "${CMAKE_CURRENT_SOURCE_DIR}/../../PersonalRating/build/bin/native")
set(KOTLIN_SHARED_DIR "${KOTLIN_DIR}/releaseShared")

add_library(personalrating-lib SHARED IMPORTED)
set_target_properties(personalrating-lib PROPERTIES
    IMPORTED_IMPLIB "${KOTLIN_SHARED_DIR}/personalrating.dll"
    IMPORTED_LOCATION "${KOTLIN_SHARED_DIR}/personalrating.dll"
    INTERFACE_INCLUDE_DIRECTORIES "${KOTLIN_SHARED_DIR}"
)

set(SRC main.cpp)
add_executable(main ${SRC})
target_link_libraries(main PRIVATE personalrating-lib)
# copy the dll to build directory post build
add_custom_command(TARGET main POST_BUILD
    COMMAND ${CMAKE_COMMAND} -E copy_if_different
        "${KOTLIN_SHARED_DIR}/personalrating.dll"
        $<TARGET_FILE_DIR:main>
)
