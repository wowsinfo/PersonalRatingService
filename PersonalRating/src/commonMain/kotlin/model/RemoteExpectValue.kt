package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteExpectValue(
    @SerialName("average_damage_dealt")
    val averageDamageDealt: Double,
    @SerialName("average_frags")
    val averageFrags: Double,
    @SerialName("win_rate")
    val averageWinRate: Double,
)
