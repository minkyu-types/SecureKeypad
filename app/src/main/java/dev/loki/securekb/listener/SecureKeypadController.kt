package dev.loki.securekb.listener

import dev.loki.securekb.setting.KeypadButton
import dev.loki.securekb.setting.KeypadState
import kotlinx.coroutines.flow.Flow

interface SecureKeypadController {
    val state: Flow<KeypadState>
    fun initialize()
    fun handleButtonPress(button: KeypadButton)
    fun shuffleButtons()
    fun reset()
}