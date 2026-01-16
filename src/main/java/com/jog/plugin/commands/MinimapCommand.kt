package com.jog.plugin.commands

import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.jog.plugin.gui.MinimapGUI
import java.util.concurrent.CompletableFuture

class MinimapCommand : AbstractAsyncCommand("minimap", "opens the minimap") {
    override fun executeAsync(context: CommandContext): CompletableFuture<Void?> {
        val sender = context.sender();
        if (sender is Player) {
            sender.worldMapTracker.tick(0f);
            val ref = sender.reference;
            if (ref == null || !ref.isValid) {
                return CompletableFuture.completedFuture(null);
            }

            val store = ref.store;
            val world = store.externalData.world;

            return CompletableFuture.runAsync({
                val playerRef = store.getComponent<PlayerRef>(ref, PlayerRef.getComponentType());
                if (playerRef != null) {
                    sender.hudManager.setCustomHud(playerRef, MinimapGUI(playerRef))
                }
            }, world)
        }

        return CompletableFuture.completedFuture(null);
    }
}