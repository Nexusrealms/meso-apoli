package io.github.eggohito.neo_apoli.provider.custom.number;

import com.mojang.serialization.MapCodec;
import io.github.eggohito.neo_apoli.provider.NumberProvider;
import io.github.eggohito.neo_apoli.provider.type.number.NumberProviderType;
import io.github.eggohito.neo_apoli.provider.type.number.NumberProviderTypes;
import io.github.eggohito.neo_apoli.util.context.Context;
import io.github.eggohito.neo_apoli.util.context.ContextParameters;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.world.World;

import java.util.Set;

@EqualsAndHashCode
@Data
public final class FuelAmountNumberProvider extends NumberProvider {

	public static final MapCodec<FuelAmountNumberProvider> CODEC = MapCodec.unit(FuelAmountNumberProvider::new);
	public static final PacketCodec<RegistryByteBuf, FuelAmountNumberProvider> PACKET_CODEC = PacketCodec.unit(new FuelAmountNumberProvider());

	public FuelAmountNumberProvider() {

	}

	@Override
	public NumberProviderType<?> getType() {
		return NumberProviderTypes.FUEL_AMOUNT;
	}
	//TODO Why mojang did u do it this way
	@Override
	protected Number impl(Context context) {

		World world = context.getWorld();
		ItemStack stack = context.required(ContextParameters.ITEM_STACK);

		return AbstractFurnaceBlockEntity.createFuelTimeMap().get(stack.getItem());

	}

	@Override
	public Set<LootContextParameter<?>> getAllowedParameters() {
		return Set.of(ContextParameters.ITEM_STACK);
	}

}
