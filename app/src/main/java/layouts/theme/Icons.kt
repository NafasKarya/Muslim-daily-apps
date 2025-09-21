package com.nafaskarya.muslimdaily.layouts.theme

import androidx.annotation.DrawableRes
import com.nafaskarya.muslimdaily.R

/**
 * Object ini berisi referensi ID ke semua ikon aplikasi yang ada di folder res/drawable.
 * Dikelompokkan berdasarkan fungsi untuk kemudahan akses.
 */
object AppIcons {

    // --- Ikon dengan satu state (tidak punya versi aktif/inaktif) ---
    @DrawableRes val Notification: Int = R.drawable.ic_notification
    @DrawableRes val Menu: Int = R.drawable.ic_menu
    @DrawableRes val ChevronRight: Int = R.drawable.ic_chevron_right
    @DrawableRes val Add: Int = R.drawable.ic_add

    // --- Ikon yang memiliki state aktif dan inaktif ---

    object Home {
        @DrawableRes val active: Int = R.drawable.ic_home_filled
        @DrawableRes val inactive: Int = R.drawable.ic_home_outlined
    }

    object Search {
        @DrawableRes val active: Int = R.drawable.ic_search_filled
        @DrawableRes val inactive: Int = R.drawable.ic_search_outlined
    }

    object Mosque {
        @DrawableRes val active: Int = R.drawable.ic_mosque_filled
        @DrawableRes val inactive: Int = R.drawable.ic_mosque_outlined
    }

    object Favorite {
        @DrawableRes val active: Int = R.drawable.ic_favorite_filled
        @DrawableRes val inactive: Int = R.drawable.ic_favorite_outlined
    }

    object Profile {
        @DrawableRes val active: Int = R.drawable.ic_profile_filled
        @DrawableRes val inactive: Int = R.drawable.ic_profile_outlined
    }
}