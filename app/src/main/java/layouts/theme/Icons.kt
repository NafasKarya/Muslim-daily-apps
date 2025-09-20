package com.nafaskarya.muslimdaily.layouts.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

object AppIcons {

    val Notification: ImageVector
        get() {
            if (_notification != null) {
                return _notification!!
            }
            _notification = ImageVector.Builder(
                name = "Notification",
                defaultWidth = 24.0.dp,
                defaultHeight = 24.0.dp,
                viewportWidth = 24.0f,
                viewportHeight = 24.0f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    stroke = null,
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(12.0f, 22.0f)
                    curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f)
                    horizontalLineToRelative(-4.0f)
                    curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f)
                    close()
                    moveTo(18.0f, 16.0f)
                    verticalLineToRelative(-5.0f)
                    curveToRelative(0.0f, -3.07f, -1.63f, -5.64f, -4.5f, -6.32f)
                    lineTo(13.5f, 4.0f)
                    curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f)
                    reflectiveCurveToRelative(-1.5f, 0.67f, -1.5f, 1.5f)
                    verticalLineToRelative(0.68f)
                    curveTo(7.63f, 5.36f, 6.0f, 7.92f, 6.0f, 11.0f)
                    verticalLineToRelative(5.0f)
                    lineToRelative(-2.0f, 2.0f)
                    verticalLineToRelative(1.0f)
                    horizontalLineToRelative(16.0f)
                    verticalLineToRelative(-1.0f)
                    lineToRelative(-2.0f, -2.0f)
                    close()
                }
            }.build()
            return _notification!!
        }

    private var _notification: ImageVector? = null


    val Menu: ImageVector
        get() {
            if (_menu != null) {
                return _menu!!
            }
            _menu = ImageVector.Builder(
                name = "Menu",
                defaultWidth = 24.0.dp,
                defaultHeight = 24.0.dp,
                viewportWidth = 24.0f,
                viewportHeight = 24.0f
            ).apply {
                path(
                    fill = SolidColor(Color.Black)
                ) {
                    moveTo(3.0f, 18.0f)
                    horizontalLineToRelative(18.0f)
                    verticalLineToRelative(-2.0f)
                    horizontalLineTo(3.0f)
                    verticalLineToRelative(2.0f)
                    close()
                    moveTo(3.0f, 13.0f)
                    horizontalLineToRelative(18.0f)
                    verticalLineToRelative(-2.0f)
                    horizontalLineTo(3.0f)
                    verticalLineToRelative(2.0f)
                    close()
                    moveTo(3.0f, 6.0f)
                    verticalLineToRelative(2.0f)
                    horizontalLineToRelative(18.0f)
                    verticalLineTo(6.0f)
                    horizontalLineTo(3.0f)
                    close()
                }
            }.build()
            return _menu!!
        }
    private var _menu: ImageVector? = null

    // --- TAMBAHAN Mosque & ChevronRight ---
    val Mosque: ImageVector
        get() {
            if (_mosque != null) {
                return _mosque!!
            }
            _mosque = ImageVector.Builder(
                name = "Mosque",
                defaultWidth = 24.0.dp,
                defaultHeight = 24.0.dp,
                viewportWidth = 24.0f,
                viewportHeight = 24.0f
            ).apply {
                path(
                    fill = SolidColor(Color.Black)
                ) {
                    moveTo(12.0f, 3.0f)
                    lineTo(4.0f, 9.0f)
                    verticalLineToRelative(2.0f)
                    horizontalLineToRelative(16.0f)
                    verticalLineTo(9.0f)
                    lineTo(12.0f, 3.0f)
                    close()
                    moveTo(16.5f, 12.0f)
                    horizontalLineTo(7.5f)
                    curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f)
                    verticalLineToRelative(6.0f)
                    horizontalLineToRelative(1.5f)
                    verticalLineToRelative(-2.5f)
                    curveToRelative(0.0f, -0.83f, 0.67f, -1.5f, 1.5f, -1.5f)
                    reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f)
                    verticalLineTo(20.0f)
                    horizontalLineTo(14.0f)
                    verticalLineToRelative(-2.5f)
                    curveToRelative(0.0f, -0.83f, 0.67f, -1.5f, 1.5f, -1.5f)
                    reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f)
                    verticalLineTo(20.0f)
                    horizontalLineToRelative(1.5f)
                    verticalLineToRelative(-6.0f)
                    curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f)
                    close()
                    moveTo(8.0f, 12.5f)
                    curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f)
                    verticalLineTo(13.0f)
                    curveToRelative(0.0f, 0.28f, -0.22f, 0.5f, -0.5f, 0.5f)
                    reflectiveCurveToRelative(-0.5f, -0.22f, -0.5f, -0.5f)
                    verticalLineToRelative(-1.0f)
                    curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f)
                    close()
                    moveTo(13.0f, 12.5f)
                    curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f)
                    verticalLineTo(13.0f)
                    curveToRelative(0.0f, 0.28f, -0.22f, 0.5f, -0.5f, 0.5f)
                    reflectiveCurveToRelative(-0.5f, -0.22f, -0.5f, -0.5f)
                    verticalLineToRelative(-1.0f)
                    curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f)
                    close()
                }
            }.build()
            return _mosque!!
        }
    private var _mosque: ImageVector? = null

    val ChevronRight: ImageVector
        get() {
            if (_chevronRight != null) {
                return _chevronRight!!
            }
            _chevronRight = ImageVector.Builder(
                name = "ChevronRight",
                defaultWidth = 24.0.dp,
                defaultHeight = 24.0.dp,
                viewportWidth = 24.0f,
                viewportHeight = 24.0f
            ).apply {
                path(
                    fill = SolidColor(Color.Black)
                ) {
                    moveTo(10.0f, 6.0f)
                    lineTo(8.59f, 7.41f)
                    lineTo(13.17f, 12.0f)
                    lineToRelative(-4.58f, 4.59f)
                    lineTo(10.0f, 18.0f)
                    lineToRelative(6.0f, -6.0f)
                    close()
                }
            }.build()
            return _chevronRight!!
        }
    private var _chevronRight: ImageVector? = null

    // --- ✅ IKON BARU DITAMBAHKAN DI BAWAH INI ---

    val HomeFilled: ImageVector
        get() {
            if (_homeFilled != null) return _homeFilled!!
            _homeFilled = ImageVector.Builder(name = "HomeFilled", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f).apply {
                path(fill = SolidColor(Color.Black)) {
                    moveTo(10f, 20f)
                    verticalLineToRelative(-6f)
                    horizontalLineToRelative(4f)
                    verticalLineToRelative(6f)
                    horizontalLineToRelative(5f)
                    verticalLineToRelative(-8f)
                    horizontalLineToRelative(3f)
                    lineTo(12f, 3f)
                    lineTo(2f, 12f)
                    horizontalLineToRelative(3f)
                    verticalLineToRelative(8f)
                    close()
                }
            }.build()
            return _homeFilled!!
        }
    private var _homeFilled: ImageVector? = null

    val HomeOutlined: ImageVector
        get() {
            if (_homeOutlined != null) return _homeOutlined!!
            _homeOutlined = ImageVector.Builder(name = "HomeOutlined", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f).apply {
                path(fill = SolidColor(Color.Black)) {
                    moveTo(12f, 5.69f)
                    lineTo(5f, 4.5f)
                    verticalLineTo(18f)
                    horizontalLineToRelative(-2f)
                    verticalLineToRelative(-6f)
                    horizontalLineTo(9f)
                    verticalLineToRelative(6f)
                    horizontalLineTo(7f)
                    verticalLineToRelative(-7.81f)
                    lineTo(5f, -4.5f)
                    moveTo(12f, 3f)
                    lineTo(2f, 12f)
                    horizontalLineToRelative(3f)
                    verticalLineToRelative(8f)
                    horizontalLineToRelative(6f)
                    verticalLineToRelative(-6f)
                    horizontalLineToRelative(2f)
                    verticalLineToRelative(6f)
                    horizontalLineToRelative(6f)
                    verticalLineToRelative(-8f)
                    horizontalLineToRelative(3f)
                    lineTo(12f, 3f)
                    close()
                }
            }.build()
            return _homeOutlined!!
        }
    private var _homeOutlined: ImageVector? = null

    val ExploreOutlined: ImageVector
        get() {
            if (_exploreOutlined != null) return _exploreOutlined!!
            _exploreOutlined = ImageVector.Builder(name = "ExploreOutlined", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f).apply {
                path(fill = SolidColor(Color.Black)) {
                    moveTo(12f, 2f)
                    curveTo(6.48f, 2f, 2f, 6.48f, 2f, 12f)
                    reflectiveCurveToRelative(4.48f, 10f, 10f, 10f)
                    reflectiveCurveToRelative(10f, -4.48f, 10f, -10f)
                    reflectiveCurveTo(17.52f, 2f, 12f, 2f)
                    close()
                    moveTo(12f, 20f)
                    curveToRelative(-4.41f, 0f, -8f, -3.59f, -8f, -8f)
                    reflectiveCurveToRelative(3.59f, -8f, 8f, -8f)
                    reflectiveCurveToRelative(8f, 3.59f, 8f, 8f)
                    reflectiveCurveToRelative(-3.59f, 8f, -8f, 8f)
                    close()
                    moveTo(14.19f, 14.19f)
                    lineTo(6f, 18f)
                    lineToRelative(3.81f, -8.19f)
                    lineTo(18f, 6f)
                    lineToRelative(-3.81f, 8.19f)
                    close()
                }
            }.build()
            return _exploreOutlined!!
        }
    private var _exploreOutlined: ImageVector? = null

    val NotificationOutlined: ImageVector
        get() {
            if (_notificationOutlined != null) return _notificationOutlined!!
            _notificationOutlined = ImageVector.Builder(name = "NotificationOutlined", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f).apply {
                path(fill = SolidColor(Color.Black)) {
                    moveTo(12f, 22f)
                    curveToRelative(1.1f, 0f, 2f, -0.9f, 2f, -2f)
                    horizontalLineToRelative(-4f)
                    curveToRelative(0f, 1.1f, 0.9f, 2f, 2f, 2f)
                    close()
                    moveTo(18f, 16f)
                    verticalLineToRelative(-5f)
                    curveToRelative(0f, -3.07f, -1.63f, -5.64f, -4.5f, -6.32f)
                    verticalLineTo(4f)
                    curveToRelative(0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f)
                    reflectiveCurveToRelative(-1.5f, 0.67f, -1.5f, 1.5f)
                    verticalLineToRelative(0.68f)
                    curveTo(7.64f, 5.36f, 6f, 7.92f, 6f, 11f)
                    verticalLineToRelative(5f)
                    lineToRelative(-2f, 2f)
                    verticalLineToRelative(1f)
                    horizontalLineToRelative(16f)
                    verticalLineToRelative(-1f)
                    lineToRelative(-2f, -2f)
                    close()
                    moveTo(16f, 17f)
                    horizontalLineTo(8f)
                    verticalLineToRelative(-5f)
                    curveToRelative(0f, -2.48f, 1.51f, -4.5f, 4f, -4.5f)
                    reflectiveCurveToRelative(4f, 2.02f, 4f, 4.5f)
                    verticalLineToRelative(5f)
                    close()
                }
            }.build()
            return _notificationOutlined!!
        }
    private var _notificationOutlined: ImageVector? = null

    val ProfileFilled: ImageVector
        get() {
            if (_profileFilled != null) return _profileFilled!!
            _profileFilled = ImageVector.Builder(name = "ProfileFilled", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f).apply {
                path(fill = SolidColor(Color.Black)) {
                    moveTo(12f, 12f)
                    curveToRelative(2.21f, 0f, 4f, -1.79f, 4f, -4f)
                    reflectiveCurveToRelative(-1.79f, -4f, -4f, -4f)
                    reflectiveCurveToRelative(-4f, 1.79f, -4f, 4f)
                    reflectiveCurveToRelative(1.79f, 4f, 4f, 4f)
                    close()
                    moveTo(12f, 14f)
                    curveToRelative(-2.67f, 0f, -8f, 1.34f, -8f, 4f)
                    verticalLineToRelative(2f)
                    horizontalLineToRelative(16f)
                    verticalLineToRelative(-2f)
                    curveToRelative(0f, -2.66f, -5.33f, -4f, -8f, -4f)
                    close()
                }
            }.build()
            return _profileFilled!!
        }
    private var _profileFilled: ImageVector? = null

    val ProfileOutlined: ImageVector
        get() {
            if (_profileOutlined != null) return _profileOutlined!!
            _profileOutlined = ImageVector.Builder(name = "ProfileOutlined", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f).apply {
                path(fill = SolidColor(Color.Black)) {
                    moveTo(12f, 6f)
                    curveToRelative(1.1f, 0f, 2f, 0.9f, 2f, 2f)
                    reflectiveCurveToRelative(-0.9f, 2f, -2f, 2f)
                    reflectiveCurveToRelative(-2f, -0.9f, -2f, -2f)
                    reflectiveCurveToRelative(0.9f, -2f, 2f, -2f)
                    moveTo(12f, 16f)
                    curveToRelative(2.7f, 0f, 5.8f, 1.29f, 6f, 2f)
                    horizontalLineTo(6f)
                    curveToRelative(0.23f, -0.72f, 3.31f, -2f, 6f, -2f)
                    moveTo(12f, 4f)
                    curveToRelative(-2.21f, 0f, -4f, 1.79f, -4f, 4f)
                    reflectiveCurveToRelative(1.79f, 4f, 4f, 4f)
                    reflectiveCurveToRelative(4f, -1.79f, 4f, -4f)
                    reflectiveCurveToRelative(-1.79f, -4f, -4f, -4f)
                    close()
                    moveTo(12f, 14f)
                    curveToRelative(-2.67f, 0f, -8f, 1.34f, -8f, 4f)
                    verticalLineToRelative(2f)
                    horizontalLineToRelative(16f)
                    verticalLineToRelative(-2f)
                    curveToRelative(0f, -2.66f, -5.33f, -4f, -8f, -4f)
                    close()
                }
            }.build()
            return _profileOutlined!!
        }
    private var _profileOutlined: ImageVector? = null

    val Add: ImageVector
        get() {
            if (_add != null) return _add!!
            _add = ImageVector.Builder(name = "Add", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f).apply {
                path(fill = SolidColor(Color.Black)) {
                    moveTo(19f, 13f)
                    horizontalLineToRelative(-6f)
                    verticalLineToRelative(6f)
                    horizontalLineToRelative(-2f)
                    verticalLineToRelative(-6f)
                    horizontalLineTo(5f)
                    verticalLineToRelative(-2f)
                    horizontalLineToRelative(6f)
                    verticalLineTo(5f)
                    horizontalLineToRelative(2f)
                    verticalLineToRelative(6f)
                    horizontalLineToRelative(6f)
                    verticalLineToRelative(2f)
                    close()
                }
            }.build()
            return _add!!
        }
    private var _add: ImageVector? = null
}