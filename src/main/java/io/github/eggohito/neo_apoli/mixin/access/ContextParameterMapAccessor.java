package io.github.eggohito.neo_apoli.mixin.access;

import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.context.LootContextParameterSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(LootContextParameterSet.class)
public interface ContextParameterMapAccessor {

	@Accessor
	Map<LootContextParameter<?>, Object> getParameters();

	@Mixin(LootContextParameterSet.Builder.class)
	interface BuilderAccessor {

		@Accessor
		Map<LootContextParameter<?>, Object> getParameters();

	}

}
