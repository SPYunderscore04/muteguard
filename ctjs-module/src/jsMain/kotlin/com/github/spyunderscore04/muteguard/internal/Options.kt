package com.github.spyunderscore04.muteguard.internal

import kotlinx.serialization.Serializable

@Serializable
data class Options(
    var mode: Mode = Mode.BLOCK
)
