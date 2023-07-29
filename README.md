# Personal Rating Service
A multiplatform Personal Rating service in Kotlin

## Building
Under `Configurations`, add three configs for native, js and jvm.
- jsRun/compileKotlinJs
    - Output is `build\js\packages\`
- jar/compileKotlinJvm
    - Output is `build\libs\`
- nativeBinaries/compileKotlinNative
    - Speed up by using `linkReleaseSharedNative` or `linkDebugSharedNative`
    - Output is `build\bin\native\`

For this project, the jar size is around 40 KB, js files are around 5 MB and the native library is around 7 MB (on Windows).
