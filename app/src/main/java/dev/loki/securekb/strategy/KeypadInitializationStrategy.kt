package dev.loki.securekb.strategy

import dev.loki.securekb.factory.KeypadButtonFactory
import dev.loki.securekb.manager.KeypadConfigManager
import dev.loki.securekb.manager.KeypadShuffleManager
import dev.loki.securekb.setting.KeypadButton
import dev.loki.securekb.setting.KeypadConfig
import dev.loki.securekb.setting.ShuffleAlgorithm

class KeypadInitializationStrategy(
    private val buttonFactory: KeypadButtonFactory,
    private val shuffleManager: KeypadShuffleManager,
    private val configManager: KeypadConfigManager
) {
    fun initializeManagers() {
        shuffleManager.setShuffleAlgorithm(ShuffleAlgorithm.RANDOM)
        configManager.updateConfig(KeypadConfig())
    }

    fun createInitialButtons(): List<KeypadButton> {
        val numberButtons = buttonFactory.createNumberButtons()
        val blankButtons = buttonFactory.createBlankButtons()
        return numberButtons + blankButtons
    }
}