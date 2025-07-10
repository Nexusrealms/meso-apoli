package io.github.eggohito.neo_apoli.mixin.action.meta;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.eggohito.neo_apoli.action.meta.ExplodeMetaAction;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(Explosion.class)
public abstract class ExplodeMetaActionMixin {

	@Shadow
	@Final
	private ExplosionBehavior behavior;

	@Shadow @Final private World world;

	@WrapOperation(method = "collectBlocksAndDamageEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getOtherEntities(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/Box;)Ljava/util/List;"))
	private List<Entity> getAllEntitiesIncludingSelf(World instance, Entity entity, Box box, Operation<List<Entity>> original) {

		if (this.behavior instanceof ExplodeMetaAction.CustomExplosionBehavior) {
			return world.getNonSpectatingEntities(Entity.class, box);
		}

		else {
			return original.call(world, entity, box);
		}

	}

}
