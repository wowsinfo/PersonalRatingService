package model

import kotlin.js.JsExport
import kotlin.math.roundToInt

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
    val ap: Int, // Ability Point considers battle count, can be good for individual ship not overall
    val battles: Int,
) {
    val prComment: PersonalRatingComment = PersonalRatingComment(pr.toDouble())
    val apComment: PersonalRatingComment = PersonalRatingComment(ap.toDouble())
}

@JsExport
enum class PersonalRatingLanguage {
    EN, ZH;

    fun comments(): Array<String> {
        return when (this) {
            EN -> enComments
            ZH -> zhComments
        }
    }
}

private val enComments = arrayOf(
    "Unknown",
    "Should improve",
    "Below average",
    "Average",
    "Good",
    "Very good",
    "Great",
    "Unicum",
    "Super Unicum",
)

private val zhComments = arrayOf(
    "未知",
    "还需努力",
    "低于平均",
    "平均",
    "好",
    "很好",
    "非常好",
    "大佬水平",
    "神佬水平",
)

@JsExport
data class PersonalRatingComment(
    val pr: Double,
) {
    fun getComment(language: PersonalRatingLanguage = PersonalRatingLanguage.EN): String {
        val comments = language.comments()
        val pr = pr.roundToInt()
        if (pr <= 0) return comments[0]

        val index = when (pr) {
            in 1..<750 -> 1
            in 750..<1100 -> 2
            in 1100..<1350 -> 3
            in 1350..<1550 -> 4
            in 1550..<1750 -> 5
            in 1750..<2100 -> 6
            in 2100..<2450 -> 7
            else -> 8
        }

        return comments[index]
    }
}