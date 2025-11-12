package dev.loki.securekb.handler

import dev.loki.securekb.listener.KeypadEventListener
import dev.loki.securekb.manager.KeypadConfigManager
import dev.loki.securekb.setting.KeypadButton

class ButtonPressHandler(
    private val configManager: KeypadConfigManager,
    private val listener: KeypadEventListener
) {
    var onShuffle: (() -> Unit)? = null

    fun handle(button: KeypadButton) {
        when (button) {
            is KeypadButton.KeypadUtilButton.KeypadShuffleButton -> {
                onShuffle?.invoke()
            }
            is KeypadButton.KeypadUtilButton.KeypadDeleteButton -> {
                listener.onButtonClick(button)
            }
            is KeypadButton.KeypadUtilButton.KeypadConfirmButton -> {
                listener.onButtonClick(button)
            }
            is KeypadButton.KeypadContentButton.KeypadItemButton -> {
                listener.onButtonClick(button)
                if (shouldShuffleOnInput()) {
                    onShuffle?.invoke()
                }
            }
            is KeypadButton.KeypadContentButton.KeypadBlankButton -> {
                // Do nothing for blank buttons
            }
        }
    }

    private fun shouldShuffleOnInput(): Boolean {
        return configManager.config.shuffleOnEachInput
    }
}