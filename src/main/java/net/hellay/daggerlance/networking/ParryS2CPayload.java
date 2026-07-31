package net.hellay.daggerlance.networking;

import net.akws.chiseled_lib.client.camera.screenflash.ColourFlash;
import net.akws.chiseled_lib.client.camera.screenflash.Flashes;
import net.hellay.daggerlance.Daggerlance;
import net.hellay.daggerlance.config.DaggerlanceConfigMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;

public record ParryS2CPayload() implements CustomPacketPayload {
    public static final Identifier PARRY_PAYLOAD_ID = Daggerlance.id("parry");
    public static final CustomPacketPayload.Type<ParryS2CPayload> TYPE = new CustomPacketPayload.Type<>(PARRY_PAYLOAD_ID);
    public static final StreamCodec<FriendlyByteBuf,ParryS2CPayload> CODEC = StreamCodec.unit(new ParryS2CPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void receive(ParryS2CPayload context) {
        if (DaggerlanceConfigMenu.render_parry_flash) {
            int colour = ARGB.color(55,Integer.parseInt(DaggerlanceConfigMenu.parry_flash_colour.replace('#',' ').strip(),16));
            Flashes.instance().addFlash(new ColourFlash(2, colour));
        }
    }
}
