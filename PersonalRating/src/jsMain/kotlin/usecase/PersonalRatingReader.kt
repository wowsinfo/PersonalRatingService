package usecase

actual fun readerFromFile(filePath: String): PersonalRatingReader? {
    val canAccessFile = js("typeof require == 'function' && require('fs') != null") as Boolean
    if (!canAccessFile) return null

    val fs = js("require('fs')")
    val exists = fs.existsSync(filePath) as Boolean
    if (!exists) return null

    val fileContent = fs.readFileSync(filePath, "utf8") as String
    return PersonalRatingReader.fromString(fileContent)
}
