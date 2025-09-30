// Contoh di file: app/src/main/java/com/nafaskarya/muslimdaily/ui/navigation/Routes.kt
object Routes {
    const val QURAN_LIST = "quran_list"
    // Tambahkan rute baru untuk detail surah dengan argumen nomor surah
    const val SURAH_DETAIL = "surah_detail/{surahNumber}"

    // Fungsi helper untuk membuat rute dengan argumen
    fun surahDetail(surahNumber: Int) = "surah_detail/$surahNumber"
}