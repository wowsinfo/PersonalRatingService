package usecase

actual fun readerFromFile(filePath: String): PersonalRatingReader? {
    // This works in node.js but will try a warning in browser
    val fs = js("try { return require('fs') } catch (e) { return null }") ?: return null
    val exists = fs.existsSync(filePath) as Boolean
    if (!exists) return null

    val fileContent = fs.readFileSync(filePath, "utf8") as String
    return PersonalRatingReader.fromString(fileContent)
}
