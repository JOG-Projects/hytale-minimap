package com.jog.plugin

import com.jog.plugin.commands.CameraCommand
import com.jog.plugin.commands.ExampleCommand
import com.jog.plugin.commands.TitleCommand
import com.jog.plugin.listeners.PlayerChatListener
import com.jog.plugin.listeners.PlayerReadyListener
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent
import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit
import com.jog.plugin.commands.MinimapCommand
import javax.annotation.Nonnull

class MinimapPlugin(init: JavaPluginInit) : JavaPlugin(init) {
    override fun setup() {
        // Commands
        commandRegistry.registerCommand(CameraCommand())
        commandRegistry.registerCommand(ExampleCommand())
        commandRegistry.registerCommand(TitleCommand())
        commandRegistry.registerCommand(MinimapCommand())

        // Events
        eventRegistry.registerGlobal<String?, PlayerChatEvent?>(
            PlayerChatEvent::class.java
        ) { event: PlayerChatEvent? -> PlayerChatListener.onPlayerChat(event!!) }
        eventRegistry.registerGlobal<String?, PlayerReadyEvent?>(
            PlayerReadyEvent::class.java
        ) { event: PlayerReadyEvent? -> PlayerReadyListener.onPlayerReady(event!!) }
    }
}
