# Examples

Calling Kotlin Multiplatform library from C++, Java and JavaScript.

Before even trying to build and run the examples, make sure you have IntelliJ IDEA installed and can build the Kotlin Multiplatform library first. Gradle commands you need are `linkReleaseSharedNative`, `jar` and `jsNodeProductionRun`. You can also try `build` or `assemble` to build all targets.

## C++

Ensure you have CMake 3.16+ and MinGW installed. Go to cpp/ and run the following commands:

```cmd
cd build
cmake .. -G "MinGW Makefiles"
```

Now, you can run `mingw32-make` to build the executable. The executable will be in the build folder. The dll will be copied over to the build folder automatically. This should probably work on Linux and macOS too, but I haven't tried it.

There are lots of manual adjustments done to [nativeMain](https://github.com/wowsinfo/PersonalRatingService/tree/master/PersonalRating/src/nativeMain/kotlin) for it to work with C++. The callback was a tough one to figure out. If you find out a better way, please let me know.

## JavaScript

Ensure you have Node.js installed. Go to js/ and run the following commands:

```cmd
npm i
node index.js
```

You should now see the output in the console. It probably works in the browser too, but I haven't tried it. [jsMain](https://github.com/wowsinfo/PersonalRatingService/tree/master/PersonalRating/src/jsMain/kotlin) is much simpler than nativeMain. Currently (Aug 2023), kotlin cannot @JsExport a class with suspend functions, so I used my own [promiseScope](https://github.com/wowsinfo/PersonalRatingService/blob/master/PersonalRating/src/jsMain/kotlin/JsCore.kt) to convert suspend fun to Promise.

## Java

To be completed. This is the easy part.
