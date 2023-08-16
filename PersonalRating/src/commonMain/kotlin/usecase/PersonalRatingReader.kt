package usecase

import kotlinx.serialization.json.Json
import model.RemoteDataSource
import model.RemoteExpectValue
import kotlin.js.JsExport
import kotlin.jvm.JvmStatic

/**
 * Read the json file from the local system
 */
expect fun readerFromFile(filePath: String): PersonalRatingReader?

@JsExport
class PersonalRatingReader private constructor(private val dataSource: RemoteDataSource) {
    companion object {
        @JvmStatic
        fun fromString(jsonString: String): PersonalRatingReader? {
            return try {
                val dataSource: RemoteDataSource = Json.decodeFromString(jsonString)
                PersonalRatingReader(dataSource)
            } catch (e: Exception) {
                null
            }
        }

        @JvmStatic
        fun fromFile(filePath: String): PersonalRatingReader? {
            return readerFromFile(filePath)
        }
    }

    fun getExpectedValue(shipId: String): RemoteExpectValue? {
        return dataSource.data[shipId]
    }

    fun getAllKeys(): Array<String> {
        return dataSource.data.keys.toTypedArray()
    }
}
