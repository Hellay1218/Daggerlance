package net.hellay.daggerlance.networking;

import net.akws.chiseled_lib.client.camera.screenflash.ColourFlash;
import net.akws.chiseled_lib.client.camera.screenflash.Flashes;
import net.hellay.daggerlance.Daggerlance;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record ParryS2CPayload(int color) implements CustomPacketPayload {
    public static final Identifier PARRY_PAYLOAD_ID = Daggerlance.id("parry");
    public static final CustomPacketPayload.Type<ParryS2CPayload> TYPE = new CustomPacketPayload.Type<>(PARRY_PAYLOAD_ID);
    public static final StreamCodec<FriendlyByteBuf,ParryS2CPayload> CODEC = StreamCodec.composite(ByteBufCodecs.INT,ParryS2CPayload::color,ParryS2CPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void receive(ParryS2CPayload context) {
        Flashes.instance().addFlash(new ColourFlash(2, context.color()));
    }
}
