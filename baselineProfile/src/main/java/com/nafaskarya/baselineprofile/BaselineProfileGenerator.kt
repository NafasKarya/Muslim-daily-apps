package com.nafaskarya.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {

    @get:Rule
    val rule = BaselineProfileRule()

    @Test
    fun generate() {
        rule.collect(
            packageName = InstrumentationRegistry.getArguments().getString("targetAppId")
                ?: throw Exception("targetAppId not passed as instrumentation runner arg"),
            includeInStartupProfile = true
        ) {
            pressHome()
            startActivityAndWait()

            val contentList = device.wait(Until.findObject(By.scrollable(true)), 5000)

            if (contentList != null) {
                contentList.setGestureMargin(device.displayWidth / 10)
                contentList.fling(Direction.DOWN)
                contentList.fling(Direction.UP)
            }

            val sliders = device.findObjects(By.scrollable(true))
            sliders.forEach { slider ->
                slider.setGestureMargin(device.displayWidth / 10)
                slider.scroll(Direction.RIGHT, 1.0f)
                slider.scroll(Direction.LEFT, 1.0f)
            }
        }
    }
}