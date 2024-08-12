package com.github.spyunderscore04.muteguard.feature

import com.github.spyunderscore04.muteguard.internal.Options
import com.github.spyunderscore04.muteguard.util.FileLib
import com.github.spyunderscore04.muteguard.util.displayError
import kotlinx.serialization.json.Json

object Config {
    private const val CONFIG_PATH = "./config/muteguard/options.json"
    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        isLenient = true
        prettyPrint = true
        coerceInputValues = true
        allowSpecialFloatingPointValues = true
    }

    val options: Options = getInitialOptions()
    private var lastHash = options.hashCode()

    fun writeIfChanged() {
        val currentHash = options.hashCode()
        if (currentHash != lastHash) {
            lastHash = currentHash
            writeToFile()
        }
    }

    private fun writeToFile() {
        val contents = json.encodeToString(Options.serializer(), options)
        FileLib.write(CONFIG_PATH, contents, true)
    }

    private fun getInitialOptions(): Options {
        val contents = FileLib.read(CONFIG_PATH)
            ?: return Options()

        return runCatching {
            json.decodeFromString(Options.serializer(), contents)
        }.getOrElse {
            displayError("Failed to load config: $it -- loading defaults")
            Options()
        }
    }
}
