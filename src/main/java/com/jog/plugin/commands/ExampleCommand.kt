package com.jog.plugin.commands

import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase
import javax.annotation.Nonnull

class ExampleCommand : CommandBase("example", "An example command") {
    override fun executeSync(context: CommandContext) {
        context.sendMessage(Message.raw("Hello world from kotlin!"))
    }
}
