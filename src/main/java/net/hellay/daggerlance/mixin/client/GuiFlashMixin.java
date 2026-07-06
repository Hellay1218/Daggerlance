package net.hellay.daggerlance.mixin.client;

import net.hellay.daggerlance.client.hud.screenflash.Flashes;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
        Gui.class
)
public class GuiFlashMixin {

    @Inject(method = "renderCameraOverlays", at = @At("HEAD"))
    private void daggerlance$renderFlashOverlay(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Flashes.instance().render(guiGraphics,deltaTracker);
    }


}
