package dev.loki.securekb.setting

sealed class KeypadButton {

    sealed class KeypadContentButton : KeypadButton() {
        data class KeypadItemButton(val value: String) : KeypadContentButton()
        object KeypadBlankButton : KeypadContentButton()
    }

    sealed class KeypadUtilButton : KeypadButton() {
        object KeypadShuffleButton : KeypadUtilButton()
        object KeypadDeleteButton : KeypadUtilButton()
        object KeypadConfirmButton : KeypadUtilButton()
    }
}