package com.kiologyn.watchhelper

class Config {
    class Scroll {
        companion object {
            const val HOST = "kiologyn.com"
            const val PORT = 443
            const val PATH = "/ws/watch/scroll?password=watch"
            const val SECURE = true

            const val SENSITIVITY = 0.1f
        }
    }
}