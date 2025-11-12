package dev.loki.securekb.manager

import dev.loki.securekb.setting.KeypadConfig

class KeypadConfigManager {
    private var _config: KeypadConfig = KeypadConfig()
    val config: KeypadConfig get() = _config

    fun updateConfig(config: KeypadConfig) {
        _config = config
    }
}