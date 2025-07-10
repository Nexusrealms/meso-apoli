package io.github.eggohito.neo_apoli.util.context;

import io.github.eggohito.neo_apoli.NeoApoli;
import io.github.eggohito.neo_apoli.util.PowerReference;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public final class ContextParameters {

	public static final LootContextParameter<PowerReference> POWER_REFERENCE = new LootContextParameter<>(NeoApoli.id("power_reference"));
	public static final LootContextParameter<Entity> THIS_ENTITY = new LootContextParameter<>(NeoApoli.id("this_entity"));
	public static final LootContextParameter<Vec3d> POSITION = new LootContextParameter<>(NeoApoli.id("position"));

	public static final LootContextParameter<Entity> ACTOR = new LootContextParameter<>(NeoApoli.id("actor"));
	public static final LootContextParameter<Entity> TARGET = new LootContextParameter<>(NeoApoli.id("target"));

	public static final LootContextParameter<BlockState> BLOCK_STATE = new LootContextParameter<>(NeoApoli.id("block_state"));
	public static final LootContextParameter<BlockEntity> BLOCK_ENTITY = new LootContextParameter<>(NeoApoli.id("block_entity"));
	public static final LootContextParameter<Direction> DIRECTION = new LootContextParameter<>(NeoApoli.id("direction"));

	public static final LootContextParameter<StackReference> STACK_REFERENCE = new LootContextParameter<>(NeoApoli.id("stack_reference"));
	public static final LootContextParameter<ItemStack> ITEM_STACK = new LootContextParameter<>(NeoApoli.id("item_stack"));
	public static final LootContextParameter<Hand> HAND = new LootContextParameter<>(NeoApoli.id("hand"));

}
