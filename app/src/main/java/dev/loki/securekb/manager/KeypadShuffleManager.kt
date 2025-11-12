package dev.loki.securekb.manager

import dev.loki.securekb.setting.KeypadButton
import dev.loki.securekb.setting.ShuffleAlgorithm

class KeypadShuffleManager {
    private var _shuffleAlgorithm: ShuffleAlgorithm = ShuffleAlgorithm.RANDOM
    val shuffleAlgorithm: ShuffleAlgorithm get() = _shuffleAlgorithm

    fun shuffle(buttons: List<KeypadButton>): List<KeypadButton> {
        return when (_shuffleAlgorithm) {
            ShuffleAlgorithm.RANDOM -> {
                val itemButtons =
                    buttons.filterIsInstance<KeypadButton.KeypadContentButton.KeypadItemButton>()
                val blankButtons =
                    buttons.filterIsInstance<KeypadButton.KeypadContentButton.KeypadBlankButton>()
                (itemButtons + blankButtons).shuffled()
            }

            ShuffleAlgorithm.SEQUENTIAL -> {
                val itemButtons =
                    buttons.filterIsInstance<KeypadButton.KeypadContentButton.KeypadItemButton>()
                val blankButtons =
                    buttons.filterIsInstance<KeypadButton.KeypadContentButton.KeypadBlankButton>()
                itemButtons.sortedBy { it.value } + blankButtons
            }
        }
    }

    fun setShuffleAlgorithm(algorithm: ShuffleAlgorithm) {
        _shuffleAlgorithm = algorithm
    }
}