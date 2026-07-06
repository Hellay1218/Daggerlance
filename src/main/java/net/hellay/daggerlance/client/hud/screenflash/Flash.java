package net.hellay.daggerlance.client.hud.screenflash;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;

public class Flash {

    protected int duration;
    protected boolean removed;

    public Flash(int duration) {
        this.duration =duration;
        this.removed = false;
    }

    public void tick() {
        if (duration <= 0) {
            this.remove();
        }
        duration--;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void remove() {
        this.removed =true;
    }

    public void render(GuiGraphics context, DeltaTracker tickCounter) {}
}
