package io.github.markassk.fishonmcextras.mixin;

import io.github.markassk.fishonmcextras.config.FishOnMCExtrasConfig;
import io.github.markassk.fishonmcextras.handler.LoadingHandler;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public class ArmorFeatureRendererMixin<S extends BipedEntityRenderState, M extends BipedEntityModel<S>, A extends BipedEntityModel<S>> {
    @Unique
    private final FishOnMCExtrasConfig config = FishOnMCExtrasConfig.getConfig();

    // 1.21.11: signature is (MatrixStack, OrderedRenderCommandQueue, ItemStack, EquipmentSlot, int, S state)
    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    public void injectRenderArmor(MatrixStack matrices, OrderedRenderCommandQueue queue, ItemStack stack, EquipmentSlot slot, int light, S state, CallbackInfo ci) {
        if((slot == EquipmentSlot.CHEST
                || slot == EquipmentSlot.LEGS
                || slot == EquipmentSlot.FEET)
                && config.fun.hideArmor
                && LoadingHandler.instance().isOnServer
        ) {
            ci.cancel();
        }
    }
}
