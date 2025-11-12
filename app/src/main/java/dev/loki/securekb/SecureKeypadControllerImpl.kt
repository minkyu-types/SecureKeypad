package dev.loki.securekb

import dev.loki.securekb.handler.ButtonPressHandler
import dev.loki.securekb.listener.SecureKeypadController
import dev.loki.securekb.manager.KeypadShuffleManager
import dev.loki.securekb.setting.KeypadButton
import dev.loki.securekb.setting.KeypadState
import dev.loki.securekb.strategy.KeypadInitializationStrategy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SecureKeypadControllerImpl(
    private val shuffleManager: KeypadShuffleManager,
    private val initializationStrategy: KeypadInitializationStrategy,
    private val buttonPressHandler: ButtonPressHandler
) : SecureKeypadController {
    private val _state = MutableStateFlow(KeypadState())
    override val state get() = _state.asStateFlow()

    init {
        initialize()
    }

    override fun initialize() {
        initializationStrategy.initializeManagers()
        val initialButtons = initializationStrategy.createInitialButtons()
        _state.value = _state.value.copy(buttons = initialButtons)
        shuffleButtons()
    }

    override fun handleButtonPress(button: KeypadButton) {
        buttonPressHandler.handle(button)
    }

    override fun shuffleButtons() {
        val buttons = state.value.buttons
        val shuffledButtons = shuffleManager.shuffle(buttons)
        _state.value = _state.value.copy(buttons = shuffledButtons)
    }

    override fun reset() {
        _state.value = KeypadState()
    }
}