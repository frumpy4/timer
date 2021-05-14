package frumpy4.timer.client;

import com.mojang.blaze3d.systems.RenderSystem;
import frumpy4.timer.mixin.BossBarHudAccessor;
import frumpy4.timer.mixin.ServerPlayerAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ClientBossBar;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Map;
import java.util.UUID;

public class TimerHud {
    private final MinecraftClient client;

    public TimerHud(MinecraftClient client) {
        this.client = client;
    }

    public String formatTime(long ticks) {
        long seconds = ticks / 20;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        return String.format("%02d:%02d:%02d.%03d",
                hours, minutes % 60, seconds % 60, (ticks % 20) * 50);
    }

    public void render(MatrixStack matrixStack) {
        if (client.world == null) return;
        MinecraftServer server = client.getServer();
        if (server == null) return;

        ClientPlayerEntity cPlayer = client.player;
        if (cPlayer == null) return;
        ServerPlayerEntity sPlayer = server.getPlayerManager().getPlayer(cPlayer.getUuid());

        int color;
        long time = client.world.getLevelProperties().getTime();;

        if (((ServerPlayerAccessor)sPlayer).getSeenCredits()) {
            long pt = ((TimerServerPlayerEntity)sPlayer).getSeenCreditsTime();
            if (pt == 0) {
                ((TimerServerPlayerEntity)sPlayer).setSeenCreditsTime(time);
            } else {
                time = pt;
            }

            color = 0x00BFFF;
        } else {
            color = 0x00FF6F;
        }

        String text = formatTime(time);

//        int x = 3, y = 2;
        int x = (client.getWindow().getScaledWidth() / 2) - (client.textRenderer.getWidth(text) / 2);
        int y = 3;

        //        if (client.options.debugEnabled) {
//            x = (client.getWindow().getScaledWidth() / 2) - (client.textRenderer.getWidth(text) / 2);
//        }

        Map<UUID, ClientBossBar> bossBars = ((BossBarHudAccessor)client.inGameHud.getBossBarHud()).getBossBars();
        if (!bossBars.isEmpty()) {
            y = 20;
        }

        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();

        client.textRenderer.drawWithShadow(matrixStack, text, x, y, color);

        RenderSystem.disableBlend();
        RenderSystem.popMatrix();
    }
}
