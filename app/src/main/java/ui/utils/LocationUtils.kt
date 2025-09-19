// Lokasi File: .../ui/utils/LocationUtils.kt
package com.nafaskarya.muslimdaily.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.tasks.await
import java.util.Locale

@SuppressLint("MissingPermission")
suspend fun getCurrentLocation(context: Context): Location? {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    return try {
        fusedLocationClient.getCurrentLocation(
            com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY,
            CancellationTokenSource().token
        ).await()
    } catch (e: Exception) {
        null
    }
}

/**
 * FUNGSI BARU: Mengubah objek Location (lat/long) menjadi nama kota.
 * Mengembalikan nama kota atau "Lokasi tidak ditemukan" jika gagal.
 */
fun getCityNameFromCoordinates(context: Context, location: Location): String {
    // Geocoder adalah kelas bawaan Android untuk mengubah koordinat menjadi alamat.
    val geocoder = Geocoder(context, Locale.getDefault())
    return try {
        // Mengambil 1 alamat teratas dari koordinat.
        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        if (addresses != null && addresses.isNotEmpty()) {
            // "subAdminArea" biasanya berisi nama Kota atau Kabupaten di Indonesia.
            // Jika kosong, kita coba "locality".
            addresses[0].subAdminArea ?: addresses[0].locality ?: "Lokasi tidak diketahui"
        } else {
            "Lokasi tidak ditemukan"
        }
    } catch (e: Exception) {
        // Tangani error, misal jika tidak ada koneksi internet.
        e.printStackTrace()
        "Gagal mendapatkan nama lokasi"
    }
}