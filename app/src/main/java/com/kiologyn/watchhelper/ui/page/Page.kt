package com.kiologyn.watchhelper.ui.page

import androidx.compose.runtime.Composable
import com.kiologyn.watchhelper.ui.page.rotary.RotaryPage


enum class ApplicationPage(
    val content: @Composable () -> Unit,
) {
    SCROLL(
        content = { RotaryPage() }
    ),
}