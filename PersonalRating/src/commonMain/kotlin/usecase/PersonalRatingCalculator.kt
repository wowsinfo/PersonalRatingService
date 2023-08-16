package usecase

import model.PersonalRatingResult
import model.PersonalRatingValue
import kotlin.js.JsExport
import kotlin.math.ln
import kotlin.math.log
import kotlin.math.roundToInt

@JsExport
class PersonalRatingCalculator {
    private val shipList: MutableSet<PersonalRatingValue> = mutableSetOf()

    fun addItem(value: PersonalRatingValue): PersonalRatingCalculator {
        shipList.add(value)
        return this
    }

    fun calculate(): PersonalRatingResult {
        var actualDamage: Double = 0.0
        var expectedDamage: Double = 0.0

        var actualWins: Double = 0.0
        var expectedWins: Double = 0.0

        var actualFrags: Double = 0.0
        var expectedFrags: Double = 0.0

        var totalBattle = 0

        for (ship in shipList) {
            if (ship.shouldIgnore) continue
            totalBattle += ship.battleCount
            actualDamage += ship.actualDamage
            expectedDamage += ship.expectedDamage

            actualWins += ship.actualWins
            expectedWins += ship.expectedWins

            actualFrags += ship.actualFrags
            expectedFrags += ship.expectedFrags
        }

        // Unknown
        if (totalBattle <= 0) {
            return PersonalRatingResult(
                pr = 0,
                ap = 0,
                battles = 0
            )
        }

        val rDamage = actualDamage / expectedDamage
        val rWins = actualWins / expectedWins
        val rFrags = actualFrags / expectedFrags

        val nDamage = maxOf(0.0, (rDamage - 0.4) / (1 - 0.4))
        val nWins = maxOf(0.0, (rWins - 0.7) / (1 - 0.7))
        val nFrags = maxOf(0.0, (rFrags - 0.1) / (1 - 0.1))

        // If rating is less than 1, it will be unknown. 1 is bad.
        val personalRating = maxOf(1.0, 700 * nDamage + 300 * nWins + 150 * nFrags)
        // more points for more battles
        val offset = ln(log(totalBattle.toDouble(), 10.0))
        val abilityPoint = when {
            offset < 1.0 -> personalRating
            else -> personalRating * offset
        }

        return PersonalRatingResult(
            pr = personalRating.roundToInt(),
            ap = abilityPoint.roundToInt(),
            battles = totalBattle
        )
    }
}
