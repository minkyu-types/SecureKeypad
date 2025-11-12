package dev.loki.securekb.setting

data class KeypadConfig(
    val maxInputLength: Int = 7,
    val shuffleAlgorithm: ShuffleAlgorithm = ShuffleAlgorithm.RANDOM,
    val shuffleOnStart: Boolean = true,
    val shuffleOnEachInput: Boolean = false,
    val showDeleteButton: Boolean = true,
    val maskInput: Boolean = true,
    val touchEffect: FeedbackType = FeedbackType.VIBRATION,
    val inputTimeoutSeconds: Long = 60_000L,
    val encryptInput: Boolean = true,
    val preventScreenshot: Boolean = true
)
