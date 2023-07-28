package model

data class PersonalRatingValue(
    val shipAverageValue: ShipAverageValue,
    val expectValue: RemoteExpectValue,
) {
    companion object {
        fun fromRaw(rawValue: ShipRawValue, expectValue: RemoteExpectValue): PersonalRatingValue? {
            val averageValue = rawValue.toAverage() ?: return null
            return PersonalRatingValue(
                shipAverageValue = averageValue, expectValue = expectValue
            )
        }
    }
}
