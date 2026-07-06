package net.hellay.daggerlance.client.hud.screenflash;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Environment(EnvType.CLIENT)
public class Flashes {
    private final List<Flash> flashList = new ArrayList<>();
    private static final Flashes INSTANCE = new Flashes();

    public void addFlash(Flash flash) {
        flashList.add(flash);
    }

    public static Flashes instance() {
        return INSTANCE;
    }

    public void tick() {
        List<Flash> removeQueue = new ArrayList<>();
        flashList.forEach(flash -> {
            flash.tick();
            if (flash.isRemoved()) {removeQueue.add(flash);}
        });
        flashList.removeAll(removeQueue);
    }

    public void render(GuiGraphics context, DeltaTracker delta) {
        for (Flash flash : flashList) {
            flash.render(context,delta);
        }
    }
}
