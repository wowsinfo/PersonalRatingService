package model

import kotlinx.serialization.Serializable

/**
 * Raw ship values including damage, win rate and frags for [PersonalRating]
 */
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
 * Average ship values for [PersonalRating]
 */
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
            wins = battleCount * averageWinRate,
            frags = averageFrags * battleCount
        )
    }
}
