package usecase

import kotlinx.cinterop.*
import platform.posix.fclose
import platform.posix.fopen
import platform.posix.fread
import platform.posix.size_t

@OptIn(ExperimentalForeignApi::class)
actual fun readerFromFile(filePath: String): PersonalRatingReader? {
    val file = fopen(filePath, "r") ?: return null
    val stringBuilder = StringBuilder()
    memScoped {
        val length = 1024
        val buffer = allocArray<ByteVar>(length)
        val bufferSize = length.convert<size_t>()
        val charSize = 1.convert<size_t>()
        var bytesRead: Int
        while (true) {
            bytesRead = fread(buffer, charSize, bufferSize, file).toInt()
            if (bytesRead <= 0) break
            val text = buffer.toKString()
            stringBuilder.append(text)
        }
    }

    fclose(file)
    return PersonalRatingReader.fromString(stringBuilder.toString())
}
