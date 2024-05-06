package io.github.yanggx98.kaleido.trigger;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

import static net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents.ALLOW_DAMAGE;

public class KaleidoTriggerHelper {
    public static void register() {
        ALLOW_DAMAGE.register(new ServerLivingEntityEvents.AllowDamage() {
            @Override
            public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {

                return false;
            }
        });
    }
}
