package usecase

import kotlin.io.path.Path
import kotlin.io.path.notExists
import kotlin.io.path.readText

actual fun PersonalRatingReader.Companion.fromFile(filePath: String): PersonalRatingReader? {
    val path = Path(filePath)
    if (path.notExists()) return null

    val jsonString = path.readText()
    return fromString(jsonString)
}
