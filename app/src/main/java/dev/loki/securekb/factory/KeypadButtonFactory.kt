package dev.loki.securekb.factory

import dev.loki.securekb.setting.KeypadButton

class KeypadButtonFactory {
    fun createNumberButtons(): List<KeypadButton.KeypadContentButton.KeypadItemButton> {
        return (0..9).map {
            KeypadButton.KeypadContentButton.KeypadItemButton(
                value = it.toString(),
            )
        }
    }

    fun createBlankButtons(count: Int = 2): List<KeypadButton.KeypadContentButton.KeypadBlankButton> {
        return List(count) { KeypadButton.KeypadContentButton.KeypadBlankButton }
    }

    fun createUtilButtons(): List<KeypadButton.KeypadUtilButton> {
        return listOf(
            KeypadButton.KeypadUtilButton.KeypadShuffleButton,
            KeypadButton.KeypadUtilButton.KeypadDeleteButton,
            KeypadButton.KeypadUtilButton.KeypadConfirmButton
        )
    }
}