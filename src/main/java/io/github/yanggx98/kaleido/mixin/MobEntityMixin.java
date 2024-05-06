package io.github.yanggx98.kaleido.mixin;

import io.github.yanggx98.kaleido.attribute.api.ILivingEntityAttributeAddition;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public class MobEntityMixin {
    @Inject(method = "createMobAttributes", at = @At("RETURN"), cancellable = true)
    private static void injectCreateLivingAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        DefaultAttributeContainer.Builder builder = cir.getReturnValue();
        ILivingEntityAttributeAddition.IMobEntityAttributeAddition.EVENT.invoker().addAttribute(builder);
        cir.setReturnValue(builder);
    }
}
