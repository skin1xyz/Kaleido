package io.github.yanggx98.kaleido.mixin;

import io.github.yanggx98.kaleido.attribute.api.ILivingEntityAttributeAddition;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "createLivingAttributes", at = @At("RETURN"))
    private static void injectCreateLivingAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        DefaultAttributeContainer.Builder builder = cir.getReturnValue();
        ILivingEntityAttributeAddition.EVENT.invoker().addAttribute(builder);
    }
}
