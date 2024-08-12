package com.github.spyunderscore04.muteguard.util

fun registerMessageSent(callback: (String, Any) -> Unit) {
    register("messageSent") {
        val argsArray = js("Array.prototype.slice.call(arguments)")
        val message = argsArray[0] as String
        val event = argsArray[1] as Any
        callback(message, event)
    }
}

fun registerCommand(
    name: String,
    aliases: Array<String> = arrayOf(),
    callback: (List<String>) -> Unit
) {
     register("command") {
        val argsArray = js("Array.prototype.slice.call(arguments)") as Array<String>
        val args = argsArray.toList()
        callback(args)
    }
         .setName(name)
         .setAliases(aliases)
}

fun registerStep(
    executionsPerSecond: Int,
    callback: () -> Unit
) {
    require(executionsPerSecond in 1..60)
    register("step") {
        callback()
    }
        .setFps(executionsPerSecond)
}

fun cancelEvent(event: Any) = cancel(event)

external object ChatLib {
    fun chat(message: String)
    fun say(message: String)
}

external object FileLib {
    fun exists(path: String): Boolean
    fun exists(importName: String, fileName: String): Boolean
    fun read(path: String): String?
    fun read(importName: String, fileName: String): String?
    fun write(path: String, contents: String, recursive: Boolean = definedExternally)
    fun write(importName: String, fileName: String, contents: String, recursive: Boolean = definedExternally)
}

private external fun register(eventName: String, callback: (dynamic) -> Unit): dynamic

private external fun cancel(event: dynamic)
