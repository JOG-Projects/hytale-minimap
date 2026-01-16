package com.jog.plugin.chat

import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent
import com.hypixel.hytale.server.core.universe.PlayerRef
import java.awt.Color
import javax.annotation.Nonnull

class ChatMessageFormatter : PlayerChatEvent.Formatter {
    override fun format(playerRef: PlayerRef, message: String): Message {
        return Message.join(
            Message.raw("[CHAT] ").color(Color.PINK),
            Message.raw(playerRef.username).color(Color.YELLOW),
            Message.raw(" : $message").color(Color.WHITE)
        )
    }
}
