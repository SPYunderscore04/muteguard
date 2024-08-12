muteguard
=========

ChatTriggers module for use on Hypixel -- Reduce the odds of getting muted by blocking/replacing evil messages

Features
--------

* Prevent sending messages containing bad words/phrases (block mode)
* Replace messages containing bad words/phrases with something funny (replace mode)
* Toggle mode using `/muteguard` (alias `/mg`) between `block`, `replace` and `off`

Installation
------------

> [!IMPORTANT]
> This mod is currently in development.
> Please be patient for the first release.

Roadmap
-------

* [ ] Bundle output to ZIP file for release using gradle
    * [ ] Include license file
* [ ] Make word lists customisable
* [ ] Improve stability (JavaScript :D ...)

* * *

Background
----------

This mod was created as an experiment in using the JavaScript target of Kotlin Multiplatform[^1],
and to see whether writing ChatTriggers[^2] modules this way is more comfortable than writing Forge mods.
Ironically, ChatTriggers itself is written in Kotlin --
so this Kotlin project compiles to JavaScript for use with another Kotlin project.

All in all, the cost of not having to battle with Forge is having to battle with JavaScript
and the custom Rhino implementation used in ChatTriggers ...

[^1]: https://kotlinlang.org/docs/js-overview.html

[^2]: https://github.com/ChatTriggers/ChatTriggers
