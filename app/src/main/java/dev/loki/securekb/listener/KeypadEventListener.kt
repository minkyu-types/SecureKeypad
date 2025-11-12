package dev.loki.securekb.listener

import dev.loki.securekb.setting.KeypadButton

fun interface KeypadEventListener {
    fun onButtonClick(button: KeypadButton)
}