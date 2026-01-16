package com.jog.plugin.gui

import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.jog.plugin.utils.ServerUtil
import java.io.File
import java.util.concurrent.atomic.AtomicBoolean


class MinimapGUI(playerRef: PlayerRef) : CustomUIHud(playerRef) {
    @Volatile
    private var pendingTexturePath: String? = null
    private val requestInFlight = AtomicBoolean(false)

    override fun build(commandBuilder: UICommandBuilder) {
        commandBuilder.append("Minimap.ui");
    }

    fun updateMinimap(){
        val builder = UICommandBuilder()

        pendingTexturePath?.let { path ->
            pendingTexturePath = null
            builder.set("@MyTex.TexturePath", path)
        }

        if (!requestInFlight.compareAndSet(false, true)) return

        val ref = playerRef.reference ?: return
        val store = ref.store
        val world = store.externalData.world
        val mapManager = world.worldMapManager
        val pos = playerRef.transform.position
        builder.set("#X.TextSpans", Message.raw(pos.x.toString()))
        builder.set("#Y.TextSpans", Message.raw(pos.y.toString()))

        mapManager.getImageAsync(pos.x.toInt(), pos.y.toInt())
            .thenAccept { image ->
                val data = image.data ?: return@thenAccept

                val outFile = File("resources/Common.UI.Custom/map.png")
                ServerUtil.writeArgbPng(data, image.width, image.height, outFile)

                pendingTexturePath = "map.png"
            }
            .whenComplete { _, _ ->
                requestInFlight.set(false)
            }

        update(false, builder);
    }
}
