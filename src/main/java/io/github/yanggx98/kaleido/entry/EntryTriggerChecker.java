package io.github.yanggx98.kaleido.entry;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class EntryTriggerChecker {
    public static class Attacker {
        // 是否触发暴击伤害
        public static boolean isCriticalAttack(LivingEntity entity, DamageSource source, float amount) {
            return false;
        }

        public static float getCriticalAttackChance(LivingEntity entity, DamageSource source, float amount) {
            return 0;
        }

        public static float getCriticalAttackIncrease(LivingEntity entity, DamageSource source, float amount) {
            return 1;
        }
    }
}
