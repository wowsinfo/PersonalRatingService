package usecase

import kotlinx.serialization.json.Json
import model.RemoteDataSource
import model.RemoteExpectValue
import service.RemoteDataService

/**
 * Read the json file from the local system
 */
expect fun PersonalRatingReader.Companion.fromFile(filePath: String): PersonalRatingReader?

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

        suspend fun fromRemote(): PersonalRatingReader? {
            val service = RemoteDataService()
            return fromString(service.getRemoteString())
        }
    }

    fun getExpectedValue(shipId: String): RemoteExpectValue? {
        return dataSource.data[shipId]
    }
}
