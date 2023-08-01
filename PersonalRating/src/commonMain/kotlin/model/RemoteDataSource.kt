package model

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

typealias RemoteDataMap = Map<String, RemoteExpectValue>

@JsExport
@Serializable
data class RemoteDataSource(
    val data: RemoteDataMap,
    val time: Int,
)
