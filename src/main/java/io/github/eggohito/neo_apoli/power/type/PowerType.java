package io.github.eggohito.neo_apoli.power.type;

import com.mojang.serialization.MapCodec;
import io.github.eggohito.neo_apoli.power.Power;
import io.github.eggohito.neo_apoli.util.context.Context;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;

public record PowerType<P extends Power>(LootContextType contextType, MapCodec<P> mapCodec,
										 PacketCodec<RegistryByteBuf, P> packetCodec) {

	public Context.Builder contextBuilder() {
		return Context.builder(this.contextType());
	}

}
