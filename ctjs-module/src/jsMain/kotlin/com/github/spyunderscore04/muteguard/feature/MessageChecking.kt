package com.github.spyunderscore04.muteguard.feature

import com.github.spyunderscore04.muteguard.internal.Mode
import com.github.spyunderscore04.muteguard.util.*

private val channelPrefixes = listOf("/ac", "/pc", "/gc", "/oc", "/r")
private val privatePrefixes = listOf("/w", "/msg", "/tell", "/whisper")

private val goodWords = loadWordList("good-words.txt")
private val badWords = loadWordList("bad-words.txt")

private val badWordComponentCounts = getLineComponentCounts(badWords)

private var skipNextMessage = false

fun onAttemptSendMessage(message: String, event: Any) {
    if (skipNextMessage) {
        skipNextMessage = false
        return
    }

    val messagePrefix = parseMessagePrefix(message)
        ?: return

    val actionNeeded by lazy { containsBadWords(message) }

    when (Config.options.mode) {
        Mode.OFF -> return
        Mode.REPLACE -> if (actionNeeded) replaceMessage(messagePrefix, event)
        Mode.BLOCK -> if (actionNeeded) blockMessage(event)
    }
}

private fun parseMessagePrefix(message: String): String? {
    if (!message.startsWith("/"))
        return "" // For current channel

    val (prefix, rest) = message.split(' ', limit = 2)

    if (channelPrefixes.contains(prefix))
        return prefix

    if (privatePrefixes.contains(prefix)) {
        val recipient = rest.split(' ', limit = 1).firstOrNull()
        return "$prefix $recipient"
    }

    return null // Not a chat message
}

private fun containsBadWords(message: String): Boolean {
    val words = message
        .lowercase()
//        .split("\\s+".toRegex()) // SyntaxError: Invalid flag after regular expression
        .split(' ')
        .filter { it.isNotBlank() }

    return badWordComponentCounts.any { size ->
        words
            .windowed(size) { it.joinToString(" ") }
            .any { it in badWords }
    }
}

private fun replaceMessage(prefix: String, event: Any) {
    cancelEvent(event)

    val replacement = goodWords.random()

    skipNextMessage = true
    ChatLib.say("$prefix $replacement")

    displayInfo("Replaced that message.")
}

private fun blockMessage(event: Any) {
    cancelEvent(event)
    displayInfo("Blocked that message.")
}

private fun loadWordList(fileName: String) =
    readResourceFile(fileName)
        .trim()
        .split("\n")

private fun getLineComponentCounts(lines: List<String>) =
    lines
        .map { it.split(' ').size }
        .distinct()
