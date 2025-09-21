package com.nafaskarya.muslimdaily.ui.utils

import androidx.datastore.core.Serializer
import com.nafaskarya.muslimdaily.ui.models.PrayerTimesData // <-- ALAMAT IMPORT DIPERBAIKI
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object PrayerTimesSerializer : Serializer<PrayerTimesData> {
    override val defaultValue: PrayerTimesData
        get() = PrayerTimesData()

    override suspend fun readFrom(input: InputStream): PrayerTimesData {
        return try {
            Json.decodeFromString(
                PrayerTimesData.serializer(),
                input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: PrayerTimesData, output: OutputStream) {
        output.write(
            Json.encodeToString(PrayerTimesData.serializer(), t).encodeToByteArray()
        )
    }
}