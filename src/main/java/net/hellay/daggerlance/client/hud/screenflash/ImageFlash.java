package net.hellay.daggerlance.client.hud.screenflash;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
public class ImageFlash extends Flash {

    protected Identifier sprite;
    public ImageFlash(int duration, Identifier sprite) {
        super(duration);
        this.sprite = sprite;
        this.duration =duration;
        this.removed = false;
    }

    @Override
    public void remove() {
        super.remove();
        System.out.println("fuck");
    }

    public void render(GuiGraphics context, DeltaTracker tickCounter) {
        Minecraft client = Minecraft.getInstance();
        context.blit(RenderPipelines.GUI_TEXTURED, sprite, 0, 0, client.getWindow().getGuiScaledWidth(), client.getWindow().getGuiScaledHeight(), client.getWindow().getGuiScaledWidth(), client.getWindow().getGuiScaledHeight(), client.getWindow().getGuiScaledWidth(), client.getWindow().getGuiScaledHeight());
    }
}
