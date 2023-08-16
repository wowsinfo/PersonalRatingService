package model

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

/**
 * Raw ship values including damage, win rate and frags for [PersonalRatingValue]
 */
@JsExport
@Serializable
data class ShipRawValue(
    val battleCount: Int,
    val damage: Double,
    val wins: Double,
    val frags: Double,
) {
    fun toAverage(): ShipAverageValue? {
        if (battleCount <= 0) return null

        return ShipAverageValue(
            battleCount = battleCount,
            averageDamage = damage / battleCount,
            averageWinRate = wins / battleCount,
            averageFrags = frags / battleCount
        )
    }
}

/**
 * Average ship values for [PersonalRatingValue]
 */
@JsExport
@Serializable
data class ShipAverageValue(
    val battleCount: Int,
    val averageDamage: Double,
    val averageWinRate: Double,
    val averageFrags: Double,
) {
    fun toRaw(): ShipRawValue? {
        if (battleCount <= 0) return null

        return ShipRawValue(
            battleCount = battleCount,
            damage = averageDamage * battleCount,
            // convert to actual battle count, averageWinRate is not in percentage
            wins = battleCount * averageWinRate / 100,
            frags = averageFrags * battleCount
        )
    }
}
