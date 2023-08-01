package usecase

import kotlinx.serialization.json.Json
import model.RemoteDataSource
import model.RemoteExpectValue
import kotlin.js.JsExport

/**
 * Read the json file from the local system
 */
expect fun PersonalRatingReader.Companion.fromFile(filePath: String): PersonalRatingReader?

@JsExport
class PersonalRatingReader private constructor(private val dataSource: RemoteDataSource) {
    companion object {
        fun fromString(jsonString: String): PersonalRatingReader? {
            return try {
                val dataSource: RemoteDataSource = Json.decodeFromString(jsonString)
                PersonalRatingReader(dataSource)
            } catch (e: Exception) {
                null
            }
        }
    }

    fun getExpectedValue(shipId: String): RemoteExpectValue? {
        return dataSource.data[shipId]
    }
}
