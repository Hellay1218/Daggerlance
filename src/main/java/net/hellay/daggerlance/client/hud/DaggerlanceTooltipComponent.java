package net.hellay.daggerlance.client.hud;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.hellay.daggerlance.Daggerlance;
import net.hellay.daggerlance.init.DaggerlanceDataComponents;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.world.item.ItemStack;

public class DaggerlanceTooltipComponent implements ClientTooltipComponent {

    private final ItemStack stack;

    public DaggerlanceTooltipComponent(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public int getHeight(Font font) {
        return 0;
    }

    @Override
    public int getWidth(Font font) {
        return 0;
    }

    @Override
    public void renderImage(Font font, int i, int j, int k, int l, GuiGraphics guiGraphics) {
        ClientTooltipComponent.super.renderImage(font, i, j, k, l, guiGraphics);
        int x = i + k - 20;
        int y = j + l / 9 * 4;
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, Daggerlance.id("rune_outline"),x,y,16,16);
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, Daggerlance.id("rune_border"),x,y,16,16);
        guiGraphics.renderItem(stack.getOrDefault(DaggerlanceDataComponents.DAGGERLANCE_RUNE,ItemStack.EMPTY),x,y);
    }
}
