package model

import kotlin.js.JsExport

@JsExport
data class PersonalRatingValue(
    val shipValue: ShipRawValue,
    val expectValue: RemoteExpectValue,
) {
    val battleCount = shipValue.battleCount

    /**
     * If the battle count is 0, it won't be counted into Personal Rating
     */
    val shouldIgnore = battleCount <= 0

    val actualDamage = shipValue.damage
    val expectedDamage = expectValue.averageDamageDealt * battleCount

    val actualWins = shipValue.wins
    val expectedWins = expectValue.averageWinRate * battleCount / 100

    val actualFrags = shipValue.frags
    val expectedFrags = expectValue.averageFrags * battleCount
}

@JsExport
data class PersonalRatingResult(
    val pr: Int,
    val ap: Int, // Ability Point considers battle count
    val comment: String,
)
