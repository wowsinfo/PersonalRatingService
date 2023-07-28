package model

import kotlinx.serialization.Serializable

/**
 * Raw ship values including damage, win rate and frags
 */
@Serializable
data class ShipRawValue(
    val battleCount: Int, // Only used to calculate the Ability Point
    val damage: Double,
    val winRate: Double,
    val frags: Double,
) {
    /**
     * Convert [ShipRawValue] to [ShipAverageValue].
     */
    fun toAverage(): ShipAverageValue? {
        if (battleCount <= 0) return null

        return ShipAverageValue(
            battleCount = battleCount,
            averageDamage = damage / battleCount,
            averageWinRate = winRate / battleCount,
            averageFrags = frags / battleCount
        )
    }
}

/**
 * Average ship values for Personal Rating processing
 */
@Serializable
data class ShipAverageValue(
    val battleCount: Int?,
    val averageDamage: Double,
    val averageWinRate: Double,
    val averageFrags: Double,
)
