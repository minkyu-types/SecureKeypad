package dev.loki.securekb.setting

data class KeypadState(
    val buttons: List<KeypadButton> = emptyList(),
    val inputValue: String = "",
    val maxLength: Int = 7,
    val isComplete: Boolean = false,
    val shuffleCount: Int = 0,
    val inputStartTime: Long = 0L,
    val inputLastTime: Long = 0L
) {
    val contentButtons: List<KeypadButton.KeypadContentButton>
        get() = buttons.filterIsInstance<KeypadButton.KeypadContentButton>()

    val currentLength: Int
        get() = inputValue.length
    val remainingLength: Int
        get() = maxLength - currentLength
    val canInput: Boolean
        get() = !isComplete && (currentLength < maxLength)
}