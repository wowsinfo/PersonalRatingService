package service

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import model.RemoteDataSource

/**
 * Receive the Personal Rating from Henry's GitHub to prevent traffics to wows-numbers.com
 * Only call this method if needed, do not overuse this method.
 */
class RemoteDataService {
    private val client = HttpClient()

    suspend fun getRemoteString(): String {
        return client
            .get("https://raw.githubusercontent.com/wowsinfo/WoWs-Info-Seven/API/json/personal_rating.json")
            .bodyAsText()
    }

    suspend fun getRemoteData(): RemoteDataSource {
        return Json.decodeFromString(getRemoteString())
    }
}
