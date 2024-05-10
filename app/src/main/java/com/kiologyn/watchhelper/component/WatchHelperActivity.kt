package com.kiologyn.watchhelper.component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.kiologyn.watchhelper.theme.WatchHelperTheme

abstract class WatchHelperActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WatchHelperTheme {
                Layout()
            }
        }
    }

    @Composable
    protected abstract fun Layout()
}