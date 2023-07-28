package model

import kotlinx.serialization.Serializable

typealias RemoteDataMap = Map<String, RemoteExpectValue>

@Serializable
data class RemoteDataSource(
    val data: RemoteDataMap,
    val time: Int,
)
