package com.kiologyn.watchhelper.component

import android.util.Log
import io.ktor.client.*
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class WebSocketConnection(
    private var host: String,
    private var path: String,
    private var port: Int = 80,
    private val pingInterval: Long = 5_000,
    var onMessage: (message: String) -> Unit,
    var onConnect: () -> Unit = {},
    var onDisconnect: () -> Unit = {},
) {
    private val scope = CoroutineScope(Dispatchers.Default)

    private val client = HttpClient(CIO) {
        install(WebSockets) {
            pingInterval = this@WebSocketConnection.pingInterval
        }
    }
    private var session: WebSocketSession? = null

    fun connect() {
        scope.launch {
            try {
                client.webSocket(
                    host = host,
                    path = path,
                ) {
                    onConnect()
                    session = this
                    runBlocking {
                        while (true) {
                            val frame = incoming.receive()
                            val textFrame = frame as? Frame.Text ?: break

                            onMessage(textFrame.readText())
                        }
                    }
                }
            } catch (exception: Exception) {
                Log.e("WS", exception.message.toString())
            } finally {
                session = null
                onDisconnect()
            }
        }
    }

    fun disconnect() {
        scope.launch {
            session?.close()
            client.close()
        }
    }

    fun send(message: String) {
        scope.launch {
            session?.send(message)
        }
    }
}