package com.github.spyunderscore04.muteguard.util

fun readResourceFile(filename: String) =
    FileLib
        .read("muteguard", filename)
        ?: reportMissingFile(filename)

private fun reportMissingFile(filename: String): Nothing {
    displayError("Failed to load $filename")
    throw IllegalStateException("Failed to load $filename")
}
