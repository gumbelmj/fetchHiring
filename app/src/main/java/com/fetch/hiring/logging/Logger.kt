package com.fetch.hiring.logging

import android.util.Log

class Logger {

    fun error(tag: String, message: String, throwable: Throwable? = null) {
        Log.e(tag, message, throwable)
    }

    fun debug(tag: String, message: String) {
        Log.d(tag, message)
    }
}