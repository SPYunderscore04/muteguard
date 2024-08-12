package com.github.spyunderscore04.muteguard.feature

import com.github.spyunderscore04.muteguard.internal.Mode
import com.github.spyunderscore04.muteguard.util.displayError
import com.github.spyunderscore04.muteguard.util.displayInfo

fun onCommandReceived(args: List<String>) {
    val mode = args.firstOrNull()?.lowercase()

    when (mode) {
        "block" -> onBlock()
        "replace" -> onReplace()
        "off" -> onOff()
        else -> onUnknown()
    }
}

val helpText = """
    Could not parse this command.
    
    Usage: /muteguard <block | replace | off>
    Alias: /mg
    
    Modes reference:
      * block -> Do not send bad messages 
      * replace -> Send funny messages instead of bad messages
      * off -> Send all messages, no filtering (use at your own risk)
""".trimIndent()

private fun onBlock() {
    displayInfo("Message filtering set to block mode")
    Config.options.mode = Mode.BLOCK
}

private fun onReplace() {
    displayInfo("Message filtering set to replace mode")
    Config.options.mode = Mode.REPLACE
}

private fun onOff() {
    displayInfo("Message filtering disabled")
    Config.options.mode = Mode.OFF
}

private fun onUnknown() {
    displayError(helpText)
}
