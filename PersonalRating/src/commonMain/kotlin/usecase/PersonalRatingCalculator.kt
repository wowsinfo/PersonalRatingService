package usecase

import model.PersonalRatingValue

class PersonalRatingCalculator {
    private val shipList: MutableSet<PersonalRatingValue> = mutableSetOf()

    fun addItem(value: PersonalRatingValue): PersonalRatingCalculator {
        shipList.add(value)
        return this
    }

    fun calculate() {
        var actualDamage: Double = 0.0
        val expectedDamage: Double = 0.0
    }
}
