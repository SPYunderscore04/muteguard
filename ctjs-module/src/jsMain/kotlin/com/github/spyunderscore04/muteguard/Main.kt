package com.github.spyunderscore04.muteguard

import com.github.spyunderscore04.muteguard.feature.Config
import com.github.spyunderscore04.muteguard.feature.onAttemptSendMessage
import com.github.spyunderscore04.muteguard.feature.onCommandReceived
import com.github.spyunderscore04.muteguard.util.displayInfo
import com.github.spyunderscore04.muteguard.util.registerCommand
import com.github.spyunderscore04.muteguard.util.registerMessageSent
import com.github.spyunderscore04.muteguard.util.registerStep

fun main() {
    displayInfo("Loading ...")

    registerMessageSent(::onAttemptSendMessage)
    registerCommand(name = "muteguard", aliases = arrayOf("mg"), callback = ::onCommandReceived)
    registerStep(1, Config::writeIfChanged)

    displayInfo("Loaded.")
}
