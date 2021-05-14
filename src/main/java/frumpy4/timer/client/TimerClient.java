package frumpy4.timer.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class TimerClient implements ClientModInitializer {
    // theres probably a better place to put this, idk i dont think it matters
    public static TimerHud timerHud;

    @Override
    public void onInitializeClient() {
        timerHud = new TimerHud(MinecraftClient.getInstance());
    }
}
