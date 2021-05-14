package frumpy4.timer.mixin;

import frumpy4.timer.client.TimerServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerPlayerEntity.class)
public class MixinServerPlayerEntity implements TimerServerPlayerEntity {
    long seenCreditsTime;

    @Override
    public void setSeenCreditsTime(long time) {
        this.seenCreditsTime = time;
    }

    @Override
    public long getSeenCreditsTime() {
        return this.seenCreditsTime;
    }
}
