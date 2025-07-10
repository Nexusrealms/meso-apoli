package io.github.eggohito.neo_apoli.util.context;

import com.google.common.collect.Sets;
import io.github.eggohito.neo_apoli.util.MiscUtil;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.context.LootContextType;


import java.util.Arrays;
import java.util.Set;

public class ContextTypes {

	public static final LootContextType GENERIC = new LootContextType.Builder()
		.require(ContextParameters.THIS_ENTITY)
		.require(ContextParameters.POSITION)
		.allow(ContextParameters.POWER_REFERENCE)
		.build();

	public static final LootContextType BIENTITY = new LootContextType.Builder()
		.allow(ContextParameters.ACTOR)
		.allow(ContextParameters.TARGET)
		.allow(ContextParameters.POWER_REFERENCE)
		.build();

	public static final LootContextType ITEM = new LootContextType.Builder()
		.require(ContextParameters.STACK_REFERENCE)
		.require(ContextParameters.ITEM_STACK)
		.allow(ContextParameters.POWER_REFERENCE)
		.allow(ContextParameters.HAND)
		.build();

	public static final LootContextType BLOCK = new LootContextType.Builder()
		.require(ContextParameters.POSITION)
		.require(ContextParameters.BLOCK_STATE)
		.allow(ContextParameters.BLOCK_ENTITY)
		.allow(ContextParameters.DIRECTION)
		.build();

	/**
	 * 	<p>Merge the required/allowed LootContext parameters of two LootContext types. Generally useful in cases where a type
	 * 	may not inherently support the LootContext parameters of another type, but can provide said LootContext parameters.</p>
	 *
	 * 	<p>Examples:</p>
	 * 	<ul>
	 * 	    <li>An entity action that can execute and provide the LootContext parameters required by an item action (entity
	 * 	    actions do not inherently support the LootContext parameters of an item action.)</li>
	 * 	    <li>A number provider that can test bi-entity conditions for counting how many entities fulfill it and providing
	 * 	    the count.</li>
	 * 	</ul>
	 *
	 * 	@param first the first LootContext type
	 * 	@param second the second LootContext type
	 *	@return a new LootContext type instance with the required/allowed LootContext parameters of the first and second LootContext types
	 */
	public static LootContextType merge(LootContextType first, LootContextType second) {

		LootContextType.Builder builder = new LootContextType.Builder();

		Set<LootContextParameter<?>> requiredParameters = Sets.union(first.getRequired(), second.getRequired());
		Set<LootContextParameter<?>> allowedParameters = Sets.union(first.getAllowed(), second.getAllowed());

		requiredParameters.forEach(parameter -> MiscUtil.tryCatch(() -> builder.require(parameter), e -> {}));
		allowedParameters.forEach(parameter -> MiscUtil.tryCatch(() -> builder.allow(parameter), e -> {}));

		return builder.build();

	}

	/**
	 * 	<p>Merge the required/allowed LootContext parameters of two LootContext types. Generally useful in cases where a type
	 * 	may not inherently support the LootContext parameters of another type, but can provide said LootContext parameters.</p>
	 *
	 * 	<p>Examples:</p>
	 * 	<ul>
	 * 	    <li>An entity action that can execute and provide the LootContext parameters required by an item action (entity
	 * 	    actions do not inherently support the LootContext parameters of an item action.)</li>
	 * 	    <li>A number provider that can test bi-entity conditions for counting how many entities fulfill it and providing
	 * 	    the count.</li>
	 * 	</ul>
	 *
	 * 	@param LootContextTypes the LootContext types to merge the required/allowed LootContext parameters of
	 * 	@return a new LootContext type instance with the required/allowed LootContext parameters of all the passed LootContext types
	 */
	public static LootContextType merge(LootContextType... LootContextTypes) {
		return Arrays.stream(LootContextTypes)
			.reduce(ContextTypes::merge)
			.orElseThrow(() -> new IllegalArgumentException("Couldn't merge LootContext parameters without LootContext types!"));
	}

}
