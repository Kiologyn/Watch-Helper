package com.kiologyn.watchhelper.ui.page.rotary

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kiologyn.watchhelper.Config
import com.kiologyn.watchhelper.component.WebSocketConnection

class RotaryPageViewModel : ViewModel() {
    var value by mutableFloatStateOf(0f)
        private set

    private var wsConnection: WebSocketConnection = WebSocketConnection(
        host = Config.Scroll.HOST,
        path = Config.Scroll.PATH,
        onMessage = { message ->
            try {
                value = message.toFloat()
            } catch (_: Exception) { }
        },
    )

    init {
        wsConnection.connect()
    }

    fun shiftValue(shift: Float) {
        val shiftedValue = shift * Config.Scroll.SENSITIVITY
        wsConnection.send(shiftedValue.toString())
    }
}