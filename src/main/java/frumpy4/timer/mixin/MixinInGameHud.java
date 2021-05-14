package frumpy4.timer.mixin;

import frumpy4.timer.client.TimerClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinInGameHud {
    @Inject(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;F)V",
            at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/gui/hud/SubtitlesHud;render(Lnet/minecraft/client/util/math/MatrixStack;)V"
            )
    )
    private void renderTimer(MatrixStack matrixStack, float f, CallbackInfo info) {
        TimerClient.timerHud.render(matrixStack);
    }
}

