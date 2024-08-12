package com.github.spyunderscore04.muteguard.util

private const val logPrefix = "§e[muteguard]§r"

fun displayInfo(message: String) {
    ChatLib.chat("$logPrefix $message")
}

fun displayError(message: String) {
    ChatLib.chat("$logPrefix §cError! §r$message")
}
