package model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.double
import kotlinx.serialization.json.jsonPrimitive
import kotlin.js.JsExport

@JsExport
@Serializable(with = RemoteExpectValueSerializer::class)
data class RemoteExpectValue(
    val averageDamageDealt: Double,
    val averageFrags: Double,
    val averageWinRate: Double,
)

/**
 * A custom serializer for [RemoteExpectValue] because the json format is not consistent.
 * There are times when [] is return instead of {}.
 */
private class RemoteExpectValueSerializer : KSerializer<RemoteExpectValue?> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("RemoteExpectValue") {
        element<Double>("average_damage_dealt")
        element<Double>("average_frags")
        element<Double>("win_rate")
    }

    override fun deserialize(decoder: Decoder): RemoteExpectValue? {
        val input = decoder as? JsonDecoder ?: throw SerializationException("Expected JsonDecoder")
        val jsonElement = input.decodeJsonElement()
        return if (jsonElement is JsonObject) {
            RemoteExpectValue(
                averageDamageDealt = jsonElement["average_damage_dealt"]?.jsonPrimitive?.double ?: 0.0,
                averageFrags = jsonElement["average_frags"]?.jsonPrimitive?.double ?: 0.0,
                averageWinRate = jsonElement["win_rate"]?.jsonPrimitive?.double ?: 0.0
            )
        } else {
            null
        }
    }

    override fun serialize(encoder: Encoder, value: RemoteExpectValue?) {
        // not needed for now
    }
}
